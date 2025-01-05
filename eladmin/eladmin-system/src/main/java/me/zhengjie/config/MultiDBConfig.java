package me.zhengjie.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class MultiDBConfig {

    @Primary
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.druid.one")
    public DataSource dataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.druid.two")
    public DataSource secondaryDataSource() {
        return DruidDataSourceBuilder.create().build();
    }
}
