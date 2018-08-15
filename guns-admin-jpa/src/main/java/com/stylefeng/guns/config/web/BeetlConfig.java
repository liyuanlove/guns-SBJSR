package com.stylefeng.guns.config.web;

import com.stylefeng.guns.config.properties.BeetlProperties;
import com.stylefeng.guns.core.beetl.BeetlConfiguration;
import org.beetl.core.ResourceLoader;
import org.beetl.core.resource.ClasspathResourceLoader;
import org.beetl.ext.spring.BeetlSpringViewResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * web 配置类
 *
 * @author fengshuonan
 * @date 2016年11月12日 下午5:03:32
 */
@Configuration
public class BeetlConfig {

    @Autowired
    private BeetlProperties beetlProperties;

    /**
     * beetl的配置
     */
    @Bean(initMethod = "init")
    public BeetlConfiguration beetlConfiguration() {
        BeetlConfiguration beetlConfiguration = new BeetlConfiguration();

        ResourceLoader classPathLoader = new ClasspathResourceLoader(beetlProperties.getPrefix());
        beetlConfiguration.setResourceLoader(classPathLoader);
        beetlConfiguration.setConfigProperties(beetlProperties.getProperties());
        return beetlConfiguration;
    }

    /**
     * beetl的视图解析器
     */
    @Bean
    public BeetlSpringViewResolver beetlViewResolver() {
        BeetlSpringViewResolver beetlSpringViewResolver = new BeetlSpringViewResolver();
        beetlSpringViewResolver.setConfig(beetlConfiguration());
        beetlSpringViewResolver.setContentType("text/html;charset=UTF-8");
        beetlSpringViewResolver.setOrder(0);
        beetlSpringViewResolver.setCache(false);
        return beetlSpringViewResolver;
    }

    /**
     * 解析JSP页面
     */
//    @Bean
//    public InternalResourceViewResolver getJspViewResolver() {
//        InternalResourceViewResolver jspViewResolver = new InternalResourceViewResolver();
//        jspViewResolver.setPrefix("/templates/");
//        jspViewResolver.setSuffix(".jsp");
//        jspViewResolver.setViewClass(JstlView.class);
//        jspViewResolver.setOrder(10);
//        //开发时不启用缓存，改动即可生效
//        jspViewResolver.setCache(false);
//        return jspViewResolver;
//    }
}
