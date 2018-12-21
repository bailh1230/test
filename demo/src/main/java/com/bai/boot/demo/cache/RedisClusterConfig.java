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
@ConfigurationProperties(prefix = "redis.cluster")
public class RedisClusterConfig {

    private String nodes;

    private Integer maxRedirects;

    /**
     * @return the nodes
     */
    public String getNodes() {
        return nodes;
    }

    /**
     * @param nodes the nodes to set
     */
    public void setNodes(String nodes) {
        this.nodes = nodes;
    }

    /**
     * @return the maxRedirects
     */
    public Integer getMaxRedirects() {
        return maxRedirects;
    }

    /**
     * @param maxRedirects the maxRedirects to set
     */
    public void setMaxRedirects(Integer maxRedirects) {
        this.maxRedirects = maxRedirects;
    }


}
