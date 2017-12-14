package com.idata3d.hongqi.service;

import com.idata3d.hongqi.HongqiDataApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author sunjian.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HongqiDataApplication.class)
@TestPropertySource("classpath:application.properties")
//@TestPropertySource("classpath:application-dev.properties")
public class SalesCalculateTest
{
    @Resource
    private SalesCalculate salesCalculate;

    @Test
    public void insertAllCountryTotal() throws Exception
    {
        int i = salesCalculate.calculateChinaTotal();
        System.out.println("导入了" + i + "条");
    }

    @Test
    public void insertAllCountryTotal2() throws Exception
    {
        int i = salesCalculate.calculateChinaTotal2();
        System.out.println("导入了" + i + "条");
    }
}
