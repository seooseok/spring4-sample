package com.woowahan.sample.spring4.config.spring;

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
 * Created by 서오석 on 2016. 4. 22..
 * JPA 관련 설정들
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.woowahan.sample.spring4.repository")
@EnableAspectJAutoProxy(proxyTargetClass = true)
@PropertySource("classpath:sample.properties")
public class JpaConfiguration {

	@Resource
	private Environment env;

	@Bean
	public EntityManagerFactory entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean localEntityManager = new LocalContainerEntityManagerFactoryBean();
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

		localEntityManager.setPackagesToScan("com.woowahan.sample.spring4.entity");

		vendorAdapter.setDatabasePlatform(env.getProperty(AvailableSettings.DIALECT));  //방언 설정
		vendorAdapter.setDatabase(Database.valueOf(env.getProperty("hibernate.database"))); //Database 지정

		localEntityManager.setJpaVendorAdapter(vendorAdapter);
		localEntityManager.setDataSource(this.dataSource());
		localEntityManager.setJpaProperties(this.jpaProperties());
		localEntityManager.afterPropertiesSet();

		return localEntityManager.getObject();
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager(entityManagerFactory());
		jpaTransactionManager.setEntityManagerFactory(entityManagerFactory());
		jpaTransactionManager.setJpaDialect(new HibernateJpaDialect());
		jpaTransactionManager.setGlobalRollbackOnParticipationFailure(false);


		return jpaTransactionManager;
	}

	@Bean
	public HibernateExceptionTranslator hibernateExceptionTranslator() {
		return new HibernateExceptionTranslator();
	}

	/**
	 * DataSource 설정(hikari 사용)
	 * <a herf="http://netframework.tistory.com/entry/HikariCP-%EC%86%8C%EA%B0%9C">Hikari 설정법</a></br>
	 * <a herf="https://github.com/brettwooldridge/HikariCP">Hikari Github</a>
	 *
	 * @return dataSource
	 */
	@Bean
	public DataSource dataSource() {
		HikariDataSource dataSource = new HikariDataSource();

		dataSource.setAutoCommit(false);

		dataSource.setDriverClassName(env.getProperty(AvailableSettings.DRIVER));
		dataSource.setUsername(env.getProperty(AvailableSettings.USER));
		dataSource.setPassword(env.getProperty(AvailableSettings.PASS));
		dataSource.setJdbcUrl(env.getProperty(AvailableSettings.URL));

		dataSource.setIdleTimeout(Integer.valueOf(env.getProperty("hikari.idle_timeout")));
		dataSource.setConnectionTimeout(Integer.valueOf(env.getProperty("hikari.connection_timeout")));

		dataSource.setMinimumIdle(Integer.valueOf(env.getProperty("hikari.minimum_idle")));
		dataSource.setMaximumPoolSize(Integer.parseInt(env.getProperty("hikari.datasource.max")));

		return dataSource;
	}

	private Properties jpaProperties() {
		Properties properties = new Properties();

		properties.setProperty(AvailableSettings.HBM2DDL_AUTO, env.getProperty(AvailableSettings.HBM2DDL_AUTO));    //DDL 자동 생성
		properties.setProperty(AvailableSettings.SHOW_SQL, env.getProperty(AvailableSettings.SHOW_SQL));            //SQL 보기
		properties.setProperty(AvailableSettings.FORMAT_SQL, env.getProperty(AvailableSettings.FORMAT_SQL));        //SQL 정렬해서 보기
		properties.setProperty(AvailableSettings.USE_SQL_COMMENTS, env.getProperty(AvailableSettings.USE_SQL_COMMENTS));    //SQL 코멘트 보기
		properties.setProperty(AvailableSettings.USE_NEW_ID_GENERATOR_MAPPINGS, env.getProperty(AvailableSettings.USE_NEW_ID_GENERATOR_MAPPINGS));  //새 버전의 ID 생성 옵션

		return properties;
	}
}
