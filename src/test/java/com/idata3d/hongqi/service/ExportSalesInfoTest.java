package com.idata3d.hongqi.service;

import com.idata3d.hongqi.HongqiDataApplication;
import com.idata3d.hongqi.mapper.SalesInfoMapper;
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
public class ExportSalesInfoTest
{
    @Resource
    private ExportSalesInfo exportSalesInfo;
    @Resource
    private SalesInfoMapper salesInfoMapper;

    private static final int PER_COUNT = 500000;

    @Test
    public void export() throws Exception
    {
        long startTime = System.currentTimeMillis();
        int total = salesInfoMapper.getTotal();
        int loopTimes = (total / PER_COUNT) + 1;
        int startIndex;
        int endIndex;
//        for (int i = 0; i < loopTimes; i++)
//        {
//            startIndex = i * PER_COUNT;
//            endIndex = (i + 1) * PER_COUNT;
            exportSalesInfo.exportCsv(1500000, 2000000, 3);
//        }
        long endTime = System.currentTimeMillis();
        System.out.println("导出耗时:  " + (endTime - startTime));
    }
}
