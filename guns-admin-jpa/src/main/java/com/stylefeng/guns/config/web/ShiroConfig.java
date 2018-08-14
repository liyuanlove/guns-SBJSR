package com.stylefeng.guns.config.web;

import com.baomidou.kaptcha.Kaptcha;
import com.baomidou.kaptcha.spring.boot.KaptchaAutoConfiguration;
import com.stylefeng.guns.config.properties.GunsProperties;
import com.stylefeng.guns.core.shiroext.cache.ShiroSpringCacheManager;
import com.stylefeng.guns.core.shiroext.filter.GunsUserFilter;
import com.stylefeng.guns.core.shiroext.filter.KaptchaFilter;
import com.stylefeng.guns.core.shiroext.filter.MyLogoutFilter;
import com.stylefeng.guns.core.shiroext.kit.ShiroKit;
import com.stylefeng.guns.core.shiroext.permission.AntPermissionResolver;
import com.stylefeng.guns.core.shiroext.realm.ShiroDbRealm;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.ServletContainerSessionManager;
import org.apache.shiro.web.session.mgt.WebSessionManager;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro权限管理的配置
 *
 * @author fengshuonan
 * @date 2016年11月14日 下午3:03:44
 */
@Configuration
@AutoConfigureAfter(KaptchaAutoConfiguration.class)
public class ShiroConfig {

    /**
     * 解决UnavailableSecurityManagerException
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean delegatingFilterProxy() {
        //过滤器注册对象
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        DelegatingFilterProxy proxy = new DelegatingFilterProxy();
        proxy.setTargetFilterLifecycle(true);
        proxy.setTargetBeanName("shiroFilter");
        registrationBean.setFilter(proxy);
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(-1);
        return registrationBean;
    }

    /**
     * Shiro的过滤器链
     */
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager securityManager,
                                              Kaptcha kaptcha, GunsProperties gunsProperties) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);

        String loginUrl = "/login";
        shiroFilter.setLoginUrl(loginUrl);
        shiroFilter.setSuccessUrl("/");
        shiroFilter.setUnauthorizedUrl("/global/error");

        /**
         * 覆盖默认的user拦截器(默认拦截器解决不了ajax请求 session超时的问题,若有更好的办法请及时反馈作者)
         */
        HashMap<String, Filter> myFilters = new HashMap<>();
        myFilters.put("gunsFilter", new GunsUserFilter(loginUrl));
        myFilters.put("kaptchaFilter", new KaptchaFilter(kaptcha, gunsProperties.getKaptchaInvalidateTime()));
        myFilters.put("logout", new MyLogoutFilter());
        shiroFilter.setFilters(myFilters);

        /**
         * 配置shiro拦截器链
         *
         * anon  不需要认证
         * authc 需要认证
         * gunsFilter  验证通过或RememberMe登录的都可以
         * 当应用开启了rememberMe时,用户下次访问时可以是一个user,但不会是authc,因为authc是需要重新认证的
         * 顺序从上到下,优先级依次降低
         *
         */
        Map<String, String> hashMap = new LinkedHashMap<>();
        hashMap.put("/static/**", "anon");
        hashMap.put("/global/sessionError", "anon");
        hashMap.put("/kaptcha", "anon");
        hashMap.put("/coupon/reception/**", "anon");
        hashMap.put("/coupon/mylist", "anon");
        hashMap.put("/logout", "logout");

        hashMap.put("/login", "kaptchaFilter");
        hashMap.put("/**", "gunsFilter");

//        hashMap.put("/**", "anon");

        shiroFilter.setFilterChainDefinitionMap(hashMap);
        return shiroFilter;
    }

    /**
     * 安全管理器
     */
    @Bean
    public DefaultWebSecurityManager securityManager(ShiroSpringCacheManager cacheManager,
                                                     WebSessionManager sessionManager,
                                                     GunsProperties gunsProperties) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(this.shiroDbRealm());
        securityManager.setCacheManager(cacheManager);
        if (gunsProperties.getRememberMeOpen()) {
//            securityManager.setRememberMeManager(rememberMeManager());
        }
        securityManager.setSessionManager(sessionManager);
        return securityManager;
    }

    /**
     * 项目自定义的Realm
     */
    @Bean
    public ShiroDbRealm shiroDbRealm() {
        ShiroDbRealm shiroDbRealm = new ShiroDbRealm();
        shiroDbRealm.setAuthenticationCachingEnabled(true);
        shiroDbRealm.setCredentialsMatcher(credentialsMatcher());
        shiroDbRealm.setPermissionResolver(antPermissionResolver());
        return shiroDbRealm;
    }

    /**
     * 配置自定义的密码比较器
     *
     * @return
     */
    @Bean("credentialsMatcher")
    public CredentialsMatcher credentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher(ShiroKit.hashAlgorithmName);
        hashedCredentialsMatcher.setHashIterations(ShiroKit.hashIterations);
        return hashedCredentialsMatcher;
    }

    /**
     * 自定义的地址访问控制
     *
     * @return
     */
    @Bean("antPermissionResolver")
    public AntPermissionResolver antPermissionResolver() {
        return new AntPermissionResolver();
    }

    /**
     * session管理器(单机环境采用protostuff会因为simplesession报错，需要重写)
     */
    @Bean
    @ConditionalOnProperty(prefix = "guns", name = "spring-session-open", havingValue = "false")
    public DefaultWebSessionManager defaultWebSessionManager(ShiroSpringCacheManager cacheManager,
                                                             GunsProperties gunsProperties,
                                                             CachingSessionDAO sessionDAO) {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setCacheManager(cacheManager);
        sessionManager.setSessionValidationInterval(gunsProperties.getSessionValidationInterval() * 1000);
        sessionManager.setGlobalSessionTimeout(gunsProperties.getSessionInvalidateTime() * 1000);
        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.setSessionValidationSchedulerEnabled(true);
        //Shiro提供SessionDAO用于会话的CRUD
        sessionManager.setSessionDAO(sessionDAO);
        return sessionManager;
    }

    /**
     * spring session管理器（多机环境）
     */
    @Bean
    @ConditionalOnProperty(prefix = "guns", name = "spring-session-open", havingValue = "true")
    public ServletContainerSessionManager servletContainerSessionManager() {
        return new ServletContainerSessionManager();
    }

    @Bean
    public CachingSessionDAO sessionDAO(ShiroSpringCacheManager cacheManager) {
        EnterpriseCacheSessionDAO sessionDAO = new EnterpriseCacheSessionDAO();
        sessionDAO.setCacheManager(cacheManager);
        return sessionDAO;
    }

    /**
     * 整合redis作为缓存
     *
     * @param cacheManager
     * @return
     */
    @Bean("shiroSpringCacheManager")
    public ShiroSpringCacheManager shiroSpringCacheManager(CacheManager cacheManager) {
        ShiroSpringCacheManager shiroSpringCacheManager = new ShiroSpringCacheManager(cacheManager);
        return shiroSpringCacheManager;
    }

    /**
     * Shiro生命周期处理器:
     * 用于在实现了Initializable接口的Shiro bean初始化时调用Initializable接口回调(例如:UserRealm)
     * 在实现了Destroyable接口的Shiro bean销毁时调用 Destroyable接口回调(例如:DefaultSecurityManager)
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 在方法中 注入 securityManager,进行代理控制
     */
    @Bean
    public MethodInvokingFactoryBean methodInvokingFactoryBean(DefaultWebSecurityManager securityManager) {
        MethodInvokingFactoryBean bean = new MethodInvokingFactoryBean();
        bean.setStaticMethod("org.apache.shiro.SecurityUtils.setSecurityManager");
        bean.setArguments(new Object[]{securityManager});
        return bean;
    }

    /**
     * 启用shrio授权注解拦截方式，AOP式方法级权限检查
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor =
                new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }


    /**
     * rememberMe管理器, cipherKey生成见{@code Base64Test.java}
     */
//    @Bean
//    @ConditionalOnProperty(prefix = "guns", name = "remember-me-open:", havingValue = "true")
//    public CookieRememberMeManager rememberMeManager() {
//        CookieRememberMeManager manager = new CookieRememberMeManager();
//        manager.setCipherKey(Base64.decode("Z3VucwAAAAAAAAAAAAAAAA=="));
//        manager.setCookie(rememberMeCookie());
//        return manager;
//    }

    /**
     * 记住密码Cookie
     */
//    @Bean
//    @ConditionalOnProperty(prefix = "guns", name = "remember-me-open:", havingValue = "true")
//    public SimpleCookie rememberMeCookie() {
//        SimpleCookie simpleCookie = new SimpleCookie("rememberMeOpen");
//        simpleCookie.setHttpOnly(true);
//        simpleCookie.setMaxAge(7 * 24 * 60 * 60);//7天
//        return simpleCookie;
//    }
}
