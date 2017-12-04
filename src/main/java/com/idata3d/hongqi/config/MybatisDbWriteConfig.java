package com.idata3d.hongqi.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

/**
 * @authur sunjian.
 */
//@Configuration
//@MapperScan(basePackages = {"com.idata3d.hongqi.mapper.write"}, sqlSessionFactoryRef = "writeSqlSessionFactory")
public class MybatisDbWriteConfig
{
    @Autowired
    @Qualifier("write")
    private DataSource writeDataSource;

    //驼峰映射
//    protected boolean mapUnderscoreToCamelCase = true;

    @Bean
    public SqlSessionFactory writeSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(writeDataSource);
        factoryBean.setConfigLocation(new ClassPathResource("mybatis-config.xml"));//配置mybatis配置文件(驼峰映射)
        return factoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate writeSqlSessionTemplate() throws Exception {
        SqlSessionTemplate template = new SqlSessionTemplate(writeSqlSessionFactory());
        return template;
    }
}
