package com.rrortegav.olimpiadas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories
		(entityManagerFactoryRef = "localEntityManagerFactory",
				transactionManagerRef = "localTransactionManager",
				basePackages = {"com.rrortegav.olimpiadas.repository"})
public class LocalDBConfig {

	@Autowired
	private Environment env;

	@Bean(name = "localDataSource")
	public DataSource localDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl(env.getProperty("local.datasource.url"));
		dataSource.setUsername(env.getProperty("local.datasource.username"));
		dataSource.setPassword(env.getProperty("local.datasource.password"));
		dataSource.setDriverClassName(env.getProperty("local.datasource.driver-class-name"));

		return dataSource;
	}

	@Bean(name = "localEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean localEntityManagerFactory() {

		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(localDataSource());
		em.setPackagesToScan("com.rrortegav.olimpiadas.models.entities");

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);

		Map<String, Object> properties = new HashMap<>();
		properties.put("hibernate.dialect", env.getProperty("local.jpa.properties.hibernate.dialect"));
		properties.put("hibernate.show-sql", env.getProperty("local.jpa.properties.hibernate.show-sql"));
		properties.put("hibernate.hbm2ddl.auto", env.getProperty("local.jpa.hibernate.ddl-auto"));

		em.setJpaPropertyMap(properties);

		return em;
	}

	@Bean(name = "localTransactionManager")
	public PlatformTransactionManager localTransactionManager() {

		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(localEntityManagerFactory().getObject());

		return transactionManager;
	}
}