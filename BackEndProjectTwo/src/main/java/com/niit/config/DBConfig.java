package com.niit.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.model.BlogComment;
import com.niit.model.BlogPost;
import com.niit.model.Friend;
import com.niit.model.Job;
import com.niit.model.MessagePrivate;
import com.niit.model.ProfilePicture;
import com.niit.model.Users;
@Configuration
@EnableTransactionManagement
public class DBConfig {
	@Bean
	public SessionFactory sessionFactory() {
	LocalSessionFactoryBuilder lsf=
	new LocalSessionFactoryBuilder(getDataSource());
	Properties hibernateProperties=new Properties();
	hibernateProperties.setProperty(
	"hibernate.dialect", "org.hibernate.dialect.H2Dialect");
	hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
	hibernateProperties.setProperty("hibernate.show_sql", "true");
	hibernateProperties.setProperty("hibernate.hbm2ddl.import_files_sql_extractor", "org.hibernate.tool.hbm2ddl.MultipleLinesSqlCommandExtractor");
	lsf.addProperties(hibernateProperties);
	@SuppressWarnings("rawtypes")
	Class classes[]=new Class[]{Users.class,Job.class,BlogPost.class,BlogComment.class,Friend.class,ProfilePicture.class,MessagePrivate.class};
	return lsf.addAnnotatedClasses(classes).buildSessionFactory();
	}
	@Bean
	public DataSource getDataSource() {
	BasicDataSource dataSource = new BasicDataSource();
	dataSource.setDriverClassName("org.h2.Driver");
	dataSource.setUrl("jdbc:h2:tcp://localhost/~/SecondDatabase"); //see the doc and change the url if it is different
	dataSource.setUsername("sa"); //change the value if it is different
	dataSource.setPassword(""); //change the password if it is different
	return dataSource;
	}
	@Bean
	public HibernateTransactionManager hibTransManagement(){
	return new HibernateTransactionManager(sessionFactory());
	}

}
