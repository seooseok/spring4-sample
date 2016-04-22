package com.woowahan.sample.spring4.config.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewInterceptor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.persistence.EntityManagerFactory;

/**
 * Json Rest API Controller 설정 </br>
 * 일반적으로 WebMvcConfigurerAdapter를 사용하면 된다. </br>
 * 만약 @EnableWebMvc를 사용하지 않고 직접 커스터마이징을 하고 싶다면 @EnableWebMvc 어노테이션을 제거하고 WebMvcConfigurationSupport를 extand 해서 사용하자. </br>
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.woowahan.sample.spring4.controller", useDefaultFilters = false, includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, value = RestController.class))
@Import(SwaggerConfiguration.class)
public class ServletConfiguration extends WebMvcConfigurerAdapter {

    @Autowired
    private BaseHandlerInterceptor baseHandlerInterceptor;

    @Autowired
    @Qualifier("entityManagerFactory")
    private EntityManagerFactory entityManagerFactory;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(baseHandlerInterceptor);

        OpenEntityManagerInViewInterceptor openSessionInViewInterceptor = new OpenEntityManagerInViewInterceptor();
        openSessionInViewInterceptor.setEntityManagerFactory(entityManagerFactory);

        registry.addWebRequestInterceptor(openSessionInViewInterceptor);
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }



}
