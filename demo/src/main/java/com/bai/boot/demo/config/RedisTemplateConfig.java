/**
 * 版权所属：东软望海科技有限公司
 * 作者：张晓明
 * 版本：V1.0
 * 创建日期：2018年5月24日
 * 修改日期：2018年5月24日
 */
package com.bai.boot.demo.config;

import com.bai.boot.demo.cache.RedisClusterConfig;
import com.bai.boot.demo.cache.RedisCommonConfig;
import com.bai.boot.demo.cache.RedisConfig;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.StringUtils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @author zhangxm
 * @version v1.0
 * @since 2018年5月24日
 */
@Configuration
public class RedisTemplateConfig {

    @Autowired
    private RedisConfig redisConfig;

    @Autowired
    private RedisCommonConfig redisCommonConfig;

    @Autowired
    private RedisClusterConfig redisClusterConfig;

/*    @Bean
    @Primary
    public LettucePoolingClientConfiguration initPoolingConfig() {
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMaxIdle(redisCommonConfig.getMaxIdle());
        poolConfig.setMaxTotal(redisCommonConfig.getMaxActive());
        poolConfig.setMinIdle(redisCommonConfig.getMinIdle());
        poolConfig.setTestOnBorrow(redisCommonConfig.getTestOnBorrow());
        poolConfig.setTestOnReturn(redisCommonConfig.getTestOnReturn());
        poolConfig.setMaxWaitMillis(redisCommonConfig.getMaxWait());
        LettucePoolingClientConfiguration lettuceClientConfiguration = LettucePoolingClientConfiguration.builder()
                .commandTimeout(Duration.ofSeconds(redisCommonConfig.getTimeout()))
                .poolConfig(poolConfig)
                .shutdownTimeout(Duration.ZERO)
                .build();

        return lettuceClientConfiguration;
    }

    @Bean
    @Primary
    public LettuceConnectionFactory initConnectionFactory(LettucePoolingClientConfiguration poolConfig) {
        //jedis的连接工厂
        LettuceConnectionFactory connectionFactory;
        if (StringUtils.isEmpty(redisClusterConfig.getNodes())) {
            RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
            redisStandaloneConfiguration.setHostName(redisConfig.getHost());
            redisStandaloneConfiguration.setPort(redisConfig.getPort());
            redisStandaloneConfiguration.setDatabase(redisCommonConfig.getDbIndex());
            redisStandaloneConfiguration.setPassword(redisCommonConfig.getPassword());
            connectionFactory = new LettuceConnectionFactory(redisStandaloneConfiguration, poolConfig);
        } else {
            //集群版 连接工厂
            //初始化nodes
            List<RedisNode> nodeList = new ArrayList<RedisNode>();
            for (String node : redisClusterConfig.getNodes().split(",")) {
                nodeList.add(new RedisNode(node.split(":")[0], Integer.valueOf(node.split(":")[1])));
            }
            //初始化 redisClusterConfiguration
            RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
            redisClusterConfiguration.setMaxRedirects(redisClusterConfig.getMaxRedirects());
            redisClusterConfiguration.setClusterNodes(nodeList);
            redisClusterConfiguration.setPassword(redisCommonConfig.getPassword());
            //初始化 connectionFactory
            connectionFactory = new LettuceConnectionFactory(redisClusterConfiguration, poolConfig);
        }
        connectionFactory.afterPropertiesSet();
        connectionFactory.setShareNativeConnection(false);
        return connectionFactory;
    }*/

    @Bean
    @Primary
    public JedisClientConfiguration initPoolingConfig() {
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMaxIdle(redisCommonConfig.getMaxIdle());
        poolConfig.setMaxTotal(redisCommonConfig.getMaxActive());
        poolConfig.setMinIdle(redisCommonConfig.getMinIdle());
        poolConfig.setTestOnBorrow(redisCommonConfig.getTestOnBorrow());
        poolConfig.setTestOnReturn(redisCommonConfig.getTestOnReturn());
        poolConfig.setMaxWaitMillis(redisCommonConfig.getMaxWait());
        JedisClientConfiguration jedisClientConfiguration = JedisClientConfiguration.builder()
                .connectTimeout(Duration.ofSeconds(redisCommonConfig.getTimeout()))
                .readTimeout(Duration.ZERO)
                .usePooling().poolConfig(poolConfig)
                .build();
        return jedisClientConfiguration;
    }
    @Bean
    @Primary
    public JedisConnectionFactory initConnectionFactory(JedisClientConfiguration poolConfig) {
        //jedis的连接工厂
        JedisConnectionFactory connectionFactory;
        if (StringUtils.isEmpty(redisClusterConfig.getNodes())) {
            RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
            redisStandaloneConfiguration.setHostName(redisConfig.getHost());
            redisStandaloneConfiguration.setPort(redisConfig.getPort());
            redisStandaloneConfiguration.setDatabase(redisCommonConfig.getDbIndex());
            redisStandaloneConfiguration.setPassword(redisCommonConfig.getPassword());
            connectionFactory = new JedisConnectionFactory(redisStandaloneConfiguration, poolConfig);
        } else {
            //集群版 连接工厂
            //初始化nodes
            List<RedisNode> nodeList = new ArrayList<RedisNode>();
            for (String node : redisClusterConfig.getNodes().split(",")) {
                nodeList.add(new RedisNode(node.split(":")[0], Integer.valueOf(node.split(":")[1])));
            }
            //初始化 redisClusterConfiguration
            RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
            redisClusterConfiguration.setMaxRedirects(redisClusterConfig.getMaxRedirects());
            redisClusterConfiguration.setClusterNodes(nodeList);
            redisClusterConfiguration.setPassword(redisCommonConfig.getPassword());
            //初始化 connectionFactory
            connectionFactory = new JedisConnectionFactory(redisClusterConfiguration, poolConfig);
        }
        connectionFactory.afterPropertiesSet();
        return connectionFactory;
    }


    /**
     * 实例化redis
     * @return
     */
    @Bean
    @Primary
    public RedisTemplate initRedis(JedisConnectionFactory connectionFactory) {
        //初始化 RedisTemplate
        RedisTemplate redis = new RedisTemplate();
        redis.setConnectionFactory(connectionFactory);
        //使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值（默认使用JDK的序列化方式）
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        //使用StringRedisSerializer来序列化和反序列化redis的key值
        RedisSerializer redisSerializer = new StringRedisSerializer();
        //key
        redis.setKeySerializer(redisSerializer);
        redis.setHashKeySerializer(redisSerializer);
        //value
        redis.setValueSerializer(jackson2JsonRedisSerializer);
        redis.setHashValueSerializer(jackson2JsonRedisSerializer);

        redis.afterPropertiesSet();
        return redis;
    }
}
