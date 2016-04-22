package com.woowahan.sample.spring4.config.spring;

import com.woowahan.sample.spring4.config.exception.GlobalExceptionHandler;
import com.woowahan.sample.spring4.config.exception.ParamsExceptionHandler;
import com.woowahan.sample.spring4.config.exception.UrlExceptionHandler;
import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Spring의 Bean 설정
 */
@Configuration
@ComponentScan(basePackages = {"com.woowahan.sample.spring4"})
public class BeanConfiguration {

    @Bean
    public BaseHandlerInterceptor baseHandlerInterceptor() {
        return new BaseHandlerInterceptor();
    }

    @Bean
    public GlobalExceptionHandler globalExceptionHandler() {
        return new GlobalExceptionHandler();
    }

    @Bean
    public ParamsExceptionHandler paramsExceptionHandler() {
        return new ParamsExceptionHandler();
    }

    @Bean
    public UrlExceptionHandler urlExceptionHandler() {
        return new UrlExceptionHandler();
    }


}
