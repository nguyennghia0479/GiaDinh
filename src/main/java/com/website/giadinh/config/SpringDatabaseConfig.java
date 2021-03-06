package com.website.giadinh.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.website.giadinh.entity.BuoiHoc;
import com.website.giadinh.entity.GiangVien;
import com.website.giadinh.entity.Khoa;
import com.website.giadinh.entity.LopHoc;
import com.website.giadinh.entity.MonHoc;
import com.website.giadinh.entity.NganhHoc;
import com.website.giadinh.entity.PhanCong;
import com.website.giadinh.entity.PhongHoc;
import com.website.giadinh.entity.SinhVien;
import com.website.giadinh.entity.TaiKhoan;
import com.website.giadinh.entity.ThoiKhoaBieu;

@EnableTransactionManagement
@PropertySource("classpath:/database/database.properties")
@Configuration
public class SpringDatabaseConfig {
	@Autowired
	Environment env;

	@Bean
	public LocalSessionFactoryBean factoryBean() {
		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
		factoryBean.setDataSource(dataSource());
		factoryBean.setPackagesToScan(new String[] { "com.website.giadinh.entity" });
		factoryBean.setAnnotatedClasses(Khoa.class, NganhHoc.class, GiangVien.class, SinhVien.class, LopHoc.class,
				TaiKhoan.class, MonHoc.class, PhongHoc.class, PhanCong.class, BuoiHoc.class, ThoiKhoaBieu.class);
		factoryBean.setHibernateProperties(hibernateProperties());
		return factoryBean;
	}

	private Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
		properties.put("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));
		properties.put("hibernate.format_sql", env.getRequiredProperty("hibernate.format_sql"));
		properties.put("hibernate.hbm2ddl.auto", env.getRequiredProperty("hibernate.hbm2ddl.auto"));
		return properties;
	}

	@Bean()
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getRequiredProperty("jdbc.driverClassName"));
		dataSource.setUrl(env.getRequiredProperty("jdbc.url"));
		dataSource.setUsername(env.getRequiredProperty("jdbc.username"));
		dataSource.setPassword(env.getRequiredProperty("jdbc.password"));
		return dataSource;
	}

	@Bean
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory);
		return transactionManager;
	}
}