package com.tingfeng;

import com.tingfeng.cas.config.CasConfig;
import org.jasig.cas.client.authentication.AuthenticationFilter;
import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.session.SingleSignOutHttpSessionListener;
import org.jasig.cas.client.util.HttpServletRequestWrapperFilter;
import org.jasig.cas.client.validation.Cas30ProxyReceivingTicketValidationFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.EventListener;
import java.util.HashMap;
import java.util.Map;

/**
 * cas 参数配置含义参考：http://elim.iteye.com/blog/2142631
 */
@SpringBootApplication
public class AppRun {
    private static final String ENCODING = "UTF-8";

    /*************************************   SSO配置-开始   ************************************************/

    /**
     * SingleSignOutHttpSessionListener 添加监听器
     * 用于单点退出，该过滤器用于实现单点登出功能，可选配置
     *
     * @return
     */
    @Bean
    public ServletListenerRegistrationBean<EventListener> singleSignOutListenerRegistration() {
        ServletListenerRegistrationBean<EventListener> registrationBean = new ServletListenerRegistrationBean<EventListener>();
        registrationBean.setListener(new SingleSignOutHttpSessionListener());
        registrationBean.setOrder(1);
        return registrationBean;
    }

    /**
     * SingleSignOutFilter 登出过滤器
     * 该过滤器用于实现单点登出功能，可选配置
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean filterSingleRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new SingleSignOutFilter());
        // 设定匹配的路径
        registration.addUrlPatterns("/*");
        Map<String, String> initParameters = new HashMap();
        initParameters.put("casServerUrlPrefix", CasConfig.CAS_SERVER_LOGIN_PATH);
        registration.setInitParameters(initParameters);
        // 设定加载的顺序
        registration.setOrder(1);
        return registration;
    }


    /**
     * AuthenticationFilter 授权过滤器
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean filterAuthenticationRegistration() {

        FilterRegistrationBean registration = new FilterRegistrationBean();
        Map<String, String> initParameters = new HashMap();

        registration.setFilter(new AuthenticationFilter());
        registration.addUrlPatterns("*.html");
        initParameters.put("casServerLoginUrl", CasConfig.APP_LOGIN_PAGE);
        initParameters.put("serverName", CasConfig.SERVER_NAME);

        // 不拦截的请求
        initParameters.put("ignorePattern", "^.*[.](js|css|gif|png|zip)$");

        // 表示过滤所有
        initParameters.put("ignoreUrlPatternType", "com.tingfeng.cas.auth.SimpleUrlPatternMatcherStrategy");

        registration.setInitParameters(initParameters);
        // 设定加载的顺序
        registration.setOrder(1);
        return registration;
    }


    /**
     * Cas30ProxyReceivingTicketValidationFilter 验证过滤器
     * 该过滤器负责对Ticket的校验工作，必须启用它
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean filterValidationRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new Cas30ProxyReceivingTicketValidationFilter());
        // 设定匹配的路径
        registration.addUrlPatterns("*.html");
        Map<String, String> initParameters = new HashMap();
        initParameters.put("casServerUrlPrefix", CasConfig.CAS_SERVER_PATH);
        initParameters.put("serverName", CasConfig.SERVER_NAME);

        // 是否对serviceUrl进行编码，默认true：设置false可以在302对URL跳转时取消显示;jsessionid=xxx的字符串
        // 观察CommonUtils.constructServiceUrl方法可以看到
        initParameters.put("encodeServiceUrl", "false");
        registration.setInitParameters(initParameters);
        // 设定加载的顺序
        registration.setOrder(1);
        return registration;
    }

    /**
     * HttpServletRequestWrapperFilter wraper过滤器
     * 该过滤器负责实现HttpServletRequest请求的包裹，
     * 比如允许开发者通过HttpServletRequest的getRemoteUser()方法获得SSO登录用户的登录名，可选配置。
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean filterWrapperRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new HttpServletRequestWrapperFilter());
        // 设定匹配的路径
        registration.addUrlPatterns("/*");
        // 设定加载的顺序
        registration.setOrder(1);
        return registration;
    }


    /*************************************   SSO配置-结束   ************************************************/


    /**
     * CharacterEncodingFilter 编码过滤器
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean filterEncodeRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new CharacterEncodingFilter());
        // 设定匹配的路径
        registration.addUrlPatterns("/*");
        Map<String, String> initParameters = new HashMap();
        initParameters.put("encoding", ENCODING);
        registration.setInitParameters(initParameters);
        // 设定加载的顺序
        registration.setOrder(1);
        return registration;
    }

    /**
     * 设定首页
     */
    @Configuration
    public class DefaultView extends WebMvcConfigurerAdapter {

        @Override
        public void addViewControllers(ViewControllerRegistry registry) {
            //设定首页为index
            registry.addViewController("/").setViewName("forward:/index.html");

            //设定匹配的优先级
            registry.setOrder(Ordered.HIGHEST_PRECEDENCE);

            //添加视图控制类
            super.addViewControllers(registry);
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(AppRun.class, args);
    }
}
