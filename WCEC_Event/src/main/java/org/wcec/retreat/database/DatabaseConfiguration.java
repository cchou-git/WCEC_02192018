package org.wcec.retreat.database;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaDialect;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableConfigurationProperties
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"org.wcec.retreat.repo"}, entityManagerFactoryRef= " wcecEntityManagerFactory", transactionManagerRef = "wcecTransactionManager")
@EntityScan({"org.wcec.retreat.entity"})
public class DatabaseConfiguration {
	
	@Value("${wcec.dbschema}")
	private String dbschema;
	
	@Value("${spring.datasource.driver-class-name}")
	private String databaseDriverClassName;
	
	@Value("${spring.datasource.url}")
	private String databaseURL;
	
	@Value("${spring.datasource.username}")
	private String databaseUsername;
	
	@Value("${spring.datasource.password}")
	private String databasePassword;
	
	@Bean 
	public DataSource wcecDataSource() {
		
		DataSourceBuilder builder = DataSourceBuilder
				.create(this.getClass().getClassLoader())
				.driverClassName(databaseDriverClassName)
				.url(databaseURL)
				.username(databaseUsername)
				.password(databasePassword);
		return builder.build(); 
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean wcecEntityManagerFactory() {
		LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();
		lef.setDataSource(wcecDataSource());
		lef.setJpaVendorAdapter(jpaWCECVendorAdapter());
		lef.setJpaDialect(jpaWCECDialect());
		lef.setMappingResources();
		Properties props = new Properties();
		props.put("hibernate.show_sql", "true");
		props.put("hibernate.format_sql", "true");
		props.put("hibernate.default_schema", dbschema);
		lef.setJpaProperties(props);
		lef.setPersistenceUnitName("wcecUnit");
		lef.afterPropertiesSet();
		return lef;
		
		}
	
	@Bean
	public JpaVendorAdapter jpaWCECVendorAdapter() {
		HibernateJpaVendorAdapter hibernateJpaVendorAdaptor = new HibernateJpaVendorAdapter();
		hibernateJpaVendorAdaptor.setShowSql(true);
		hibernateJpaVendorAdaptor.setGenerateDdl(true);  //?? really ??
		return hibernateJpaVendorAdaptor;
		
	}
	
	@Bean
	public JpaDialect jpaWCECDialect() {
		JpaDialect jpaDialect = new HibernateJpaDialect();
		return jpaDialect; 
	}
	
	@Bean
	public JpaTransactionManager wcecTransactionManager() {
		JpaTransactionManager tranManager = new JpaTransactionManager();
		tranManager.setEntityManagerFactory(wcecEntityManagerFactory().getObject());
		return tranManager;
	}

}
