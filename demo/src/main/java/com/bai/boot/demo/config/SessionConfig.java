/**
 * Copyright (C), 2018-2018, 东软望海科技有限公司
 * FileName: SessionConfig
 * Author:   bailh
 * Date:     2018/12/20 18:41
 * Description: 配置会话共享
 * Version:  v1.0
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.bai.boot.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.CookieHttpSessionIdResolver;
import org.springframework.session.web.http.DefaultCookieSerializer;

@Configuration
@EnableRedisHttpSession
public class SessionConfig {
    @Value("${server.servlet.session.cookie.path}")
    private String path;

    @Value("${server.servlet.session.cookie.name}")
    private String sessionName;

    @Value("${server.servlet.session.cookie.http-only}")
    private Boolean isHttpOnly;

    @Value("${server.servlet.session.cookie.timeout}")
    private Integer timeout;
    @Bean
    public CookieHttpSessionIdResolver cookieHttpSessionStrategy(){
        CookieHttpSessionIdResolver strategy=new CookieHttpSessionIdResolver();
        DefaultCookieSerializer cookieSerializer=new DefaultCookieSerializer();
        //cookies名称
        cookieSerializer.setCookieName(sessionName);
        //过期时间(秒) 默认 30分钟
        cookieSerializer.setCookieMaxAge(timeout == null? 1800:timeout);
        cookieSerializer.setCookiePath(path);
        if(isHttpOnly!=null){
            cookieSerializer.setUseHttpOnlyCookie(isHttpOnly);
        }
        strategy.setCookieSerializer(cookieSerializer);
        return strategy;
    }
}
