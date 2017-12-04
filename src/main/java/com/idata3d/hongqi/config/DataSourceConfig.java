package com.idata3d.hongqi.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @authur sunjian.
 */
//@Configuration
public class DataSourceConfig
{
    protected boolean mapUnderscoreToCamelCase = true;
    /**
     * 读取数据的数据源
     *
     * @return
     */
    @Bean(name = "read")
    @ConfigurationProperties(prefix = "spring.datasource.read") // application.properties中对应属性的前缀
    public DataSource readDataSource()
    {
        return DataSourceBuilder.create().build();
    }

    /**
     * 插入数据的数据源
     *
     * @return
     */
    @Bean(name = "write")
    @ConfigurationProperties(prefix = "spring.datasource.write") // application.properties中对应属性的前缀
    public DataSource writeDataSource()
    {
        return DataSourceBuilder.create().build();
    }
}
