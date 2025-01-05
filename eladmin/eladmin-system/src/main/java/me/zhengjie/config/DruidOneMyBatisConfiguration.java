package me.zhengjie.config;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.stream.Stream;

@Configuration
@MapperScan(basePackages = {"me.zhengjie.mapper", "me.zhengjie.modules.mappers.primary"})
public class DruidOneMyBatisConfiguration {

    @Primary
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        MybatisConfiguration config = new MybatisConfiguration();
        config.setCacheEnabled(false);
        config.setArgNameBasedConstructorAutoMapping(true);

        MybatisSqlSessionFactoryBean factory = new MybatisSqlSessionFactoryBean();
        factory.setConfiguration(config);
        factory.setDataSource(dataSource);

        Resource[] commonResources = new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml");
        Resource[] primaryResources = new PathMatchingResourcePatternResolver().getResources("classpath:mappers/primary/**/*.xml");
        Resource[] resources = Stream.concat(Arrays.stream(commonResources), Arrays.stream(primaryResources)).toArray(Resource[]::new);
        factory.setMapperLocations(resources);
        return factory.getObject();
    }
}
