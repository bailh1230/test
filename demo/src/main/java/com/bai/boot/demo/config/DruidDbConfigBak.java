/**
 * 版权所属：东软望海科技有限公司
 * 作者：张晓明 
 * 版本：V1.0
 * 创建日期：2018年5月15日
 * 修改日期：2018年5月15日
 */
package com.bai.boot.demo.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @Description  
 * @author zhangxm 
 * @version v1.0
 * @since 2018年5月15日
 */
//@Configuration
//public class DruidDbConfigBak {
//
//	@Value(value = "${spring.datasource.driverClassName}")
//	private String driverClassName;
//	@Value("${spring.datasource.url}")
//	private String url;
//	@Value("${spring.datasource.username}")
//	private String username;
//	@Value("${spring.datasource.password}")
//	private String password;
//	@Value("${spring.datasource.maxActive}")
//	private int maxActive;
//	@Value("${spring.datasource.initialSize}")
//	private int initialSize;
//	@Value("${spring.datasource.maxWait}")
//	private int maxWait;
//	@Value("${spring.datasource.minIdle}")
//	private int minIdle;
//
//	@Value("${spring.datasource.timeBetweenEvictionRunsMillis}")
//	private int timeBetweenEvictionRunsMillis;
//
//	@Value("${spring.datasource.minEvictableIdleTimeMillis}")
//	private int minEvictableIdleTimeMillis;
//
//	@Value("${spring.datasource.validationQuery}")
//	private String validationQuery;
//
//	@Value("${spring.datasource.testWhileIdle}")
//	private boolean testWhileIdle;
//
//	@Value("${spring.datasource.testOnBorrow}")
//	private boolean testOnBorrow;
//
//	@Value("${spring.datasource.testOnReturn}")
//	private boolean testOnReturn;
//
//	@Value("${spring.datasource.filters}")
//	private String filters;
//
//
//	@Value("${spring.datasource.poolPreparedStatements}")
//	private boolean poolPreparedStatements;
//
//
//	protected static final Logger logger = LoggerFactory.getLogger(DruidDbConfigBak.class);
//
//	@Bean
//	@Primary
//	public DataSource datasource(){
//		DruidDataSource datasource = new DruidDataSource();
//
//        datasource.setUrl(url);
//        datasource.setUsername(username);
//        datasource.setPassword(password);
//        datasource.setDriverClassName(driverClassName);
//
//        //configuration
//		datasource.setInitialSize(initialSize);
//		datasource.setMinIdle(minIdle);
//		datasource.setMaxActive(maxActive);
//		datasource.setMaxWait(maxWait);
//		datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
//		datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
//		datasource.setValidationQuery(validationQuery);
//		datasource.setTestWhileIdle(testWhileIdle);
//		datasource.setTestOnBorrow(testOnBorrow);
//		datasource.setTestOnReturn(testOnReturn);
////		try {
////			datasource.setFilters(filters);
////		} catch (Exception e) {
////			e.printStackTrace();
////			logger.error(e.getMessage());
////		}
//        datasource.setPoolPreparedStatements(true);
//        datasource.setMaxPoolPreparedStatementPerConnectionSize(20);
//        return datasource;
//	}
//}
