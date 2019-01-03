/**
 * Copyright (C), 2018-2018, 东软望海科技有限公司
 * FileName: writeConfig
 * Author:   bailh
 * Date:     2018/12/28 15:45
 * Description: 写数据库配置
 * Version:  v1.0
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.bai.boot.demo.config;

import org.hibernate.boot.model.naming.ImplicitNamingStrategy;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.jdbc.SchemaManagement;
import org.springframework.boot.jdbc.SchemaManagementProvider;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.*;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityWriteManagerFactory",
        transactionManagerRef = "transactionWriteManager",
        basePackages = {"com.bai.boot.demo.dao.write"})
public class WriteConfig {

    @Resource(name = "writeDataSource")
    private DataSource writeDruidDataSource;

    @Autowired
    private JpaProperties jpaProperties;

    @Autowired
    private HibernateProperties hibernateProperties;

    @Primary
    @Bean(name = "entityWriteManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityWriteManagerFactory(EntityManagerFactoryBuilder builder) {
        LocalContainerEntityManagerFactoryBean bean = builder
                .dataSource(writeDruidDataSource)
                .properties(getVendorProperties())
                .packages("com.bai.boot.demo.domain")
                .persistenceUnit("write")
                .build();
        Map map = bean.getJpaPropertyMap();
        return bean;
    }

    @Primary
    @Bean(name = "entityWriteManager")
    public EntityManager entityWriteManager(EntityManagerFactoryBuilder builder) {
        return entityWriteManagerFactory(builder).getObject().createEntityManager();
    }

    @Primary
    @Bean(name = "transactionWriteManager")
    public PlatformTransactionManager transactionWriteManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityWriteManagerFactory(builder).getObject());
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
