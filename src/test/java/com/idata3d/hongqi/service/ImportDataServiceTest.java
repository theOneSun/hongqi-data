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
public class ImportDataServiceTest
{
    @Autowired
    private ImportDataToMarketClassified importDataService;

    /**
     * 每次导入都调用这个
     *
     * @throws Exception
     */
    @Test
    public void importData() throws Exception
    {
        long start = System.currentTimeMillis();
        int i = importDataService.importData();
        long end = System.currentTimeMillis();
        System.out.println("导入了" + i + "条数据");
        System.out.println("耗时:" + (end - start));
    }
}
