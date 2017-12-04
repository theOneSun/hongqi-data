package com.idata3d.hongqi.service;

import com.idata3d.hongqi.HongqiDataApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author sunjian.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HongqiDataApplication.class)
@TestPropertySource("classpath:application.properties")
public class ImportToMarketOptionTest
{
    @Autowired
    private ImportMarketOptionDict importMarketOptionDictService;

    @Test
    public void importData() throws Exception
    {
        importMarketOptionDictService.importData();
    }
}
