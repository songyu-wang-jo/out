package com.songyuwong.infrastructure.api.starter;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * 启动配置类
 * </p>
 *
 * @author SongYu
 * @since 2022/7/2
 */
@Configuration
@ComponentScans({@ComponentScan("com.songyuwong.infrastructure.api.starter.advice")})
public class StarterConfiguration {

}
