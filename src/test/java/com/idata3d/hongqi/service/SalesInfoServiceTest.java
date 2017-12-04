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
public class SalesInfoServiceTest
{
    @Resource
    private SalesInfoService salesInfoService;

    /**
     * 更新销量和市场表
     */
    @Test
    public void updateSalesInfoAndMarket(){
        salesInfoService.updateSalesInfo();
        salesInfoService.updateMarketClassified();
    }
}
