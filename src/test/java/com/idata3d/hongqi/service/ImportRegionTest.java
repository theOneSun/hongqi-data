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
//@TestPropertySource("classpath:application.properties")
@TestPropertySource("classpath:application-dev.properties")
public class ImportRegionTest
{
    @Resource
    private ImportRegionCascade importRegionCascade;

    @Test
    public void importRegionCascade() throws Exception
    {
        long start = System.currentTimeMillis();
        int i = importRegionCascade.importDataToRegionCascade();
        long end = System.currentTimeMillis();
        System.out.println("导入了:  " + i + "条");
        System.out.println("耗时: "+(end-start));
    }
}
