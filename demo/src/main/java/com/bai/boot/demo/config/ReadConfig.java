/**
 * Copyright (C), 2018-2018, 东软望海科技有限公司
 * FileName: ReadConfig
 * Author:   bailh
 * Date:     2018/12/28 15:49
 * Description: 读数据库配置
 * Version:  v1.0
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.bai.boot.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityReadManagerFactory",
        transactionManagerRef = "transactionReadManager",
        basePackages = {"com.bai.boot.demo.dao.read"})
public class ReadConfig {

    @Resource(name = "readDataSource")
    private DataSource readDruidDataSource;

    @Autowired
    private JpaProperties jpaProperties;

    @Autowired
    private HibernateProperties hibernateProperties;

    @Bean(name = "entityReadManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityReadManagerFactory(EntityManagerFactoryBuilder builder) {
        LocalContainerEntityManagerFactoryBean slavePersistenceUnit = builder
                .dataSource(readDruidDataSource)
                .properties(getVendorProperties())
                .packages("com.bai.boot.demo.domain")
                .persistenceUnit("read")
                .build();
        slavePersistenceUnit.getJpaPropertyMap().remove("hibernate.hbm2ddl.auto");
        return slavePersistenceUnit;
    }

    @Bean(name = "entityReadManager")
    public EntityManager entityManagerSlave(EntityManagerFactoryBuilder builder) {
        return entityReadManagerFactory(builder).getObject().createEntityManager();
    }

    @Bean(name = "transactionReadManager")
    public PlatformTransactionManager transactionReadManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityReadManagerFactory(builder).getObject());
    }

    /**
     * 配置hibernate的配置信息
     * @return
     */
    protected Map<String, Object> getVendorProperties() {
        Map<String, Object> vendorProperties = new LinkedHashMap<String, Object>();
        HibernateSettings setting = new HibernateSettings();
        setting.ddlAuto(hibernateProperties::getDdlAuto);
        vendorProperties.putAll(hibernateProperties.determineHibernateProperties(jpaProperties.getProperties(), setting));
        return vendorProperties;
    }
}