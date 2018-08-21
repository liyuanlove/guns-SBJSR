package com.stylefeng.guns.config.web;

import com.stylefeng.guns.config.properties.GunsProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.tc.fastjson.config.DefaultWebConfig;
import org.tc.jpa.controller.GunsErrorView;

/**
 * web 配置类
 * WebMvcConfigurerAdapter已过时
 * WebMvcConfigurationSupport
 *
 * @author fengshuonan
 * @date 2016年11月12日 下午5:03:32
 */
@Configuration
public class WebConfig extends DefaultWebConfig {

    @Autowired
    private GunsProperties gunsProperties;

    @Bean("error")
    public GunsErrorView error() {
        return new GunsErrorView();
    }

    /**
     * 增加swagger的支持
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/");
        if (gunsProperties.getSwaggerOpen()) {
            registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
            registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        }
        super.addResourceHandlers(registry);
    }

}
