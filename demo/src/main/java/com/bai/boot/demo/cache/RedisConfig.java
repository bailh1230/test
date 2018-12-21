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
 * @Description
 * @author zhangxm
 * @version v1.0
 * @since 2018年8月10日
 */
@Component
@ConfigurationProperties(prefix = "redis.single")
public class RedisConfig {

    private String host = "127.0.0.1";

    private Integer port = 6379;

    /**
     * @return the host
     */
    public String getHost() {
        return host;
    }

    /**
     * @param host the host to set
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * @return the port
     */
    public Integer getPort() {
        return port;
    }

    /**
     * @param port the port to set
     */
    public void setPort(Integer port) {
        this.port = port;
    }


}
