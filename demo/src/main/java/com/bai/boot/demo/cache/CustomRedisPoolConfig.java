/**
 * Copyright (C), 2018-2018, 东软望海科技有限公司
 * FileName: CustomRedisPoolConfig
 * Author:   bailh
 * Date:     2018/12/20 17:03
 * Description: 自定义redis连接池配置
 * Version:  v1.0
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.bai.boot.demo.cache;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import redis.clients.jedis.JedisPoolConfig;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocketFactory;
import java.time.Duration;
import java.util.Optional;

public class CustomRedisPoolConfig implements JedisClientConfiguration {
    private JedisPoolConfig jedisPoolConfig;
    private Integer timeOut;

    public CustomRedisPoolConfig(JedisPoolConfig jedisPoolConfig, Integer timeOut) {
        this.jedisPoolConfig = jedisPoolConfig;
        this.timeOut = timeOut;
    }

    @Override
    public boolean isUseSsl() {
        return false;
    }

    @Override
    public Optional<SSLSocketFactory> getSslSocketFactory() {
        return Optional.empty();
    }

    @Override
    public Optional<SSLParameters> getSslParameters() {
        return Optional.empty();
    }

    @Override
    public Optional<HostnameVerifier> getHostnameVerifier() {
        return Optional.empty();
    }

    @Override
    public boolean isUsePooling() {
        return true;
    }

    @Override
    public Optional<GenericObjectPoolConfig> getPoolConfig() {
        return Optional.ofNullable(this.jedisPoolConfig);
    }

    @Override
    public Optional<String> getClientName() {
        return Optional.empty();
    }

    @Override
    public Duration getConnectTimeout() {
        return Duration.ofSeconds(timeOut);
    }

    @Override
    public Duration getReadTimeout() {
        return Duration.ofSeconds(timeOut);
    }
}
