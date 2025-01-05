package me.zhengjie.config;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = {"me.zhengjie.modules.mappers.secondary"}, sqlSessionFactoryRef= "secondarySqlSessionFactory")
public class DruidTwoMyBatisConfiguration {

    @Bean(name = "secondarySqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("secondaryDataSource")DataSource dataSource) throws Exception {
        MybatisConfiguration config = new MybatisConfiguration();
        config.setCacheEnabled(false);
        config.setArgNameBasedConstructorAutoMapping(true);

        MybatisSqlSessionFactoryBean factory = new MybatisSqlSessionFactoryBean();
        factory.setConfiguration(config);
        factory.setDataSource(dataSource);
        factory.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath:mappers/secondary/**/*.xml")
        );
        return factory.getObject();
    }
}
