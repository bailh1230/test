/**
 * 版权所属：东软望海科技有限公司
 * 作者：张晓明 
 * 版本：V1.0
 * 创建日期：2018年5月15日
 * 修改日期：2018年5月15日
 */
package com.bai.boot.demo.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
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
@Configuration
public class DruidDbConfig {

	@Value("${spring.datasource.write.driverClassName}")
	private String writeDriverClassName;
	@Value("${spring.datasource.write.url}")
	private String writeUrl;
	@Value("${spring.datasource.write.username}")
	private String writeUsername;
	@Value("${spring.datasource.write.password}")
	private String writePassword;

	@Value("${spring.datasource.read.driverClassName}")
	private String backupDriverClassName;
	@Value("${spring.datasource.read.url}")
	private String backupUrl;
	@Value("${spring.datasource.read.username}")
	private String backupUsername;
	@Value("${spring.datasource.read.password}")
	private String backupPassword;

	@Value("${spring.datasource.maxActive}")
	private int maxActive;
	@Value("${spring.datasource.initialSize}")
	private int initialSize;
	@Value("${spring.datasource.maxWait}")
	private int maxWait;
	@Value("${spring.datasource.minIdle}")
	private int minIdle;

	@Value("${spring.datasource.timeBetweenEvictionRunsMillis}")
	private int timeBetweenEvictionRunsMillis;

	@Value("${spring.datasource.minEvictableIdleTimeMillis}")
	private int minEvictableIdleTimeMillis;

	@Value("${spring.datasource.validationQuery}")
	private String validationQuery;

	@Value("${spring.datasource.testWhileIdle}")
	private boolean testWhileIdle;

	@Value("${spring.datasource.testOnBorrow}")
	private boolean testOnBorrow;

	@Value("${spring.datasource.testOnReturn}")
	private boolean testOnReturn;

	@Value("${spring.datasource.filters}")
	private String filters;

	/**
	 * 主数据库连接配置
	 * @return
	 */
	@Bean
	@Qualifier("writeDataSource")
	@Primary
	public DataSource writeDataSource(){
		return datasource(writeUrl, writeDriverClassName, writeUsername, writePassword);
	}

	/**
	 * 备数据库连接配置
	 * @return
	 */
	@Qualifier("readDataSource")
	@Bean
	public DataSource readDataSource(){
		return datasource(backupUrl, backupDriverClassName, backupUsername, backupPassword);
	}

	public DataSource datasource(String url, String driverClassName, String username, String password){
		DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(url);
        datasource.setUsername(username);
        datasource.setPassword(password);
        datasource.setDriverClassName(driverClassName);
        //configuration
		datasource.setInitialSize(initialSize);
		datasource.setMinIdle(minIdle);
		datasource.setMaxActive(maxActive);
		datasource.setMaxWait(maxWait);
		datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
		datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
		datasource.setValidationQuery(validationQuery);
		datasource.setTestWhileIdle(testWhileIdle);
		datasource.setTestOnBorrow(testOnBorrow);
		datasource.setTestOnReturn(testOnReturn);
/*		try {
			datasource.setFilters(filters);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}*/
        datasource.setPoolPreparedStatements(true);
        datasource.setMaxPoolPreparedStatementPerConnectionSize(20);
        return datasource;
	}
}
