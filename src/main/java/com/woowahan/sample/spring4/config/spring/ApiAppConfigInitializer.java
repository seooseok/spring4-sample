package com.woowahan.sample.spring4.config.spring;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.ServletRegistration;


public class ApiAppConfigInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {


	/**
     * Spring에서 생성해서 사용하는 Bean 객체 외에 개발자가 직접 Bean을 생성하는 경우 해당 Class를 등록해줌.
     * @return
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{BeanConfiguration.class};
    }

    /**
     * 서블릿 설정에 사용하는 Class 추가
     * (API의 req, res를 Json 포멧으로 사용하기 위해 Json Configuration을 추가해준다)
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{ServletConfiguration.class};
    }

    /**
     * Uri 주소 매핑
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/*"};
    }

    /**
     * 서블릿 필터 걸 애들 요기서 설정
     */
    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");

        LoggingFilter loggingFilter = new LoggingFilter();

        return new Filter[]{characterEncodingFilter, loggingFilter};
    }

    /**
     * Servlet에 설정을 Override해서 추가로 기능을 넣음.
     */
    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        registration.setInitParameter("throwExceptionIfNoHandlerFound", "true");
    }
}
