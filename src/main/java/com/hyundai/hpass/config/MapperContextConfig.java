package com.hyundai.hpass.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(value = "com.hyundai.hpass.mapper")
public class MapperContextConfig {
    @Value("${oracle.client-name}")
    private String clientName;

    @Value("${oracle.client-password}")
    private String clientPassword;

    @Value("${oracle.url}")
    private String url;

    @Value ("${oracle.driver}")
    private String driver;

    @Bean
    public BasicDataSource dataSource() {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(driver);
        basicDataSource.setUrl(url);
        basicDataSource.setUsername(clientName);
        basicDataSource.setPassword(clientPassword);
        return basicDataSource;
    }
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);

        Resource[] res = new PathMatchingResourcePatternResolver()
                .getResources("classpath:database/mapper/*Mapper.xml");
        sessionFactoryBean.setMapperLocations(res);
        return sessionFactoryBean.getObject();
    }
    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
