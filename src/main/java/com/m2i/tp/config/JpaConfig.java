package com.m2i.tp.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//necessite spring-orm dans pom.xml
@Configuration
@EnableTransactionManagement() // "transactionManager" (not "txManager") is expected !!!
public class JpaConfig {
// JpaVendorAdapter (Hibernate ou OpenJPA ou ...)
	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		hibernateJpaVendorAdapter.setShowSql(false);
		hibernateJpaVendorAdapter.setGenerateDdl(false);
		hibernateJpaVendorAdapter.setDatabase(Database.MYSQL);
		//hibernateJpaVendorAdapter.setDatabase(Database.HSQL);
		return hibernateJpaVendorAdapter;
	}

// EntityManagerFactory
	@Bean(name = { "entityManagerFactory", "myEmf", "otherAliasEmf" })
	public EntityManagerFactory entityManagerFactory(JpaVendorAdapter jpaVendorAdapter, DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(jpaVendorAdapter);
		factory.setPackagesToScan("com.m2i.tp.entity");
		factory.setDataSource(dataSource);
		Properties jpaProperties = new Properties(); //java.util
		jpaProperties.setProperty("javax.persistence.schema-generation.database.action",
				                   "drop-and-create");
		factory.setJpaProperties(jpaProperties);
		factory.afterPropertiesSet();
		return factory.getObject();
	}

// pour activer la prise en charge de @PersistentContext dans le code
	@Bean
	public PersistenceAnnotationBeanPostProcessor enablePersistentContextAnnotation() {
		return new PersistenceAnnotationBeanPostProcessor();
	}

// Transaction Manager for JPA or ...
	@Bean(name = "transactionManager") // ("transactionManager" but not "txManager")
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(entityManagerFactory);
		return txManager;
	}
}