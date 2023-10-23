package com.rrortegav.olimpiadas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories
		(entityManagerFactoryRef = "cloudEntityManagerFactory",
				transactionManagerRef = "cloudTransactionManager",
				basePackages = {"com.rrortegav.olimpiadas.repository"})
public class CloudDBConfig {

	@Autowired
	private Environment env;


	@Bean(name = "cloudDataSource")
	public DataSource cloudDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl(env.getProperty("cloud.datasource.url"));
		dataSource.setUsername(env.getProperty("cloud.datasource.username"));
		dataSource.setPassword(env.getProperty("cloud.datasource.password"));
		dataSource.setDriverClassName(env.getProperty("cloud.datasource.driver-class-name"));

		return dataSource;
	}

	@Bean(name = "cloudEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean cloudEntityManagerFactory() {

		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(cloudDataSource());
		em.setPackagesToScan("com.rrortegav.olimpiadas.models.entities");

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);

		Map<String, Object> properties = new HashMap<>();
		properties.put("hibernate.dialect", env.getProperty("cloud.jpa.properties.hibernate.dialect"));
		properties.put("hibernate.show-sql", env.getProperty("cloud.jpa.properties.hibernate.show-sql"));
		properties.put("hibernate.hbm2ddl.auto", env.getProperty("cloud.jpa.hibernate.ddl-auto"));

		em.setJpaPropertyMap(properties);

		return em;
	}

	@Bean(name = "cloudTransactionManager")
	public PlatformTransactionManager cloudTransactionManager() {

		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(cloudEntityManagerFactory().getObject());

		return transactionManager;
	}
}