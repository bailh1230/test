/**
 * 版权所属：东软望海科技有限公司
 * 作者：张晓明
 * 版本：V1.0
 * 创建日期：2018年8月10日
 * 修改日期：2018年8月10日
 */
package com.bai.boot.demo.cache;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description redis 公共配置类
 * @author zhangxm
 * @version v1.0
 * @since 2018年8月10日
 */
@Component
@ConfigurationProperties(prefix = "redis.common")
public class RedisCommonConfig {

    private String password = "redis";

    private Integer dbIndex = 0;

    private Integer maxIdle = 300;

    private Integer minIdle = 10;

    private Integer maxActive = 600;

    private Integer maxWait = -1;

    private Boolean testOnBorrow = true;

    private Boolean testOnReturn = false;

    private Integer timeout = 100000;

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the dbIndex
     */
    public Integer getDbIndex() {
        return dbIndex;
    }

    /**
     * @param dbIndex the dbIndex to set
     */
    public void setDbIndex(Integer dbIndex) {
        this.dbIndex = dbIndex;
    }

    /**
     * @return the maxIdle 最大空闲连接数量  default 300
     */
    public Integer getMaxIdle() {
        return maxIdle;
    }

    /**
     * @param maxIdle the maxIdle to set
     */
    public void setMaxIdle(Integer maxIdle) {
        this.maxIdle = maxIdle;
    }

    /**
     * @return the maxActive 池中持有的最大连接数量
     */
    public Integer getMaxActive() {
        return maxActive;
    }

    /**
     * @param maxActive the maxActive to set
     */
    public void setMaxActive(Integer maxActive) {
        this.maxActive = maxActive;
    }

    /**
     * @return the maxWait 等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时
     */
    public Integer getMaxWait() {
        return maxWait;
    }

    /**
     * @param maxWait the maxWait to set
     */
    public void setMaxWait(Integer maxWait) {
        this.maxWait = maxWait;
    }

    /**
     * @return the testOnBorrow  borrowObject 时是否执行检测
     */
    public Boolean getTestOnBorrow() {
        return testOnBorrow;
    }

    /**
     * @param testOnBorrow the testOnBorrow to set
     */
    public void setTestOnBorrow(Boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    /**
     * @return the timeout
     */
    public Integer getTimeout() {
        return timeout;
    }

    /**
     * @param timeout the timeout to set
     */
    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    /**
     * @return the minIdle 最小空闲连接数量
     */
    public Integer getMinIdle() {
        return minIdle;
    }

    /**
     * @param minIdle the minIdle to set
     */
    public void setMinIdle(Integer minIdle) {
        this.minIdle = minIdle;
    }

    public Boolean getTestOnReturn() {
        return testOnReturn;
    }

    public void setTestOnReturn(Boolean testOnReturn) {
        this.testOnReturn = testOnReturn;
    }
}
