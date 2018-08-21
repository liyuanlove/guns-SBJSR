package org.tc.fastjson.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.tc.fastjson.exception.FastJsonException;
import org.tc.fastjson.util.DateUtil;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

public class DefaultWebConfig extends DefaultFastjsonConfig {

    @Autowired
    private RequestMappingHandlerAdapter handlerAdapter;

    /**
     * 使用阿里 FastJson 作为JSON MessageConverter
     *
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //将转换器添加到converters中
        converters.add(fastJsonHttpMessageConverter());
        // 这里必须加上加载默认转换器，不然bug玩死人，
        addDefaultHttpMessageConverters(converters);
    }

    @PostConstruct
    public void addConversionConfig() {
        ConfigurableWebBindingInitializer initializer = (ConfigurableWebBindingInitializer) handlerAdapter.getWebBindingInitializer();
        GenericConversionService genericConversionService = (GenericConversionService) initializer.getConversionService();
        genericConversionService.addConverter(new StringToDateConverter());
    }

    public class StringToDateConverter implements Converter<String, Date> {

        @Override
        public Date convert(String dateString) {

            //若为空
            if (StringUtils.isBlank(dateString)) {
                return null;
            }

            String patternDate = "\\d{4}-\\d{1,2}-\\d{1,2}";
            String patternTimeMinutes = "\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}";
            String patternTimeSeconds = "\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}";

            boolean dateFlag = Pattern.matches(patternDate, dateString);
            boolean timeMinutesFlag = Pattern.matches(patternTimeMinutes, dateString);
            boolean timeSecondsFlag = Pattern.matches(patternTimeSeconds, dateString);

            if (dateFlag) {
                return DateUtil.parseDate(dateString);
            } else if (timeMinutesFlag) {
                return DateUtil.parseTimeMinutes(dateString);
            } else if (timeSecondsFlag) {
                return DateUtil.parseTime(dateString);
            } else {
                throw new FastJsonException(400, "输入日期格式不对");
            }

        }
    }
}


