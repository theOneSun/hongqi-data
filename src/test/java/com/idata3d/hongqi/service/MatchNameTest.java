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
public class MatchNameTest
{
    @Resource
    private MatchName matchName;

    @Test
    public void testMatchPriceCarSeriesName()
    {
        int i = matchName.matchPriceCarSeriesName();
        System.out.println("导入了" + i + "条");
    }

    @Test
    public void matchSalesInfoCarSeriesName() throws Exception
    {
        int i = matchName.matchSalesCarSeriesName();
        System.out.println("导入了" + i + "条");
    }

    @Test
    public void matchaSalesInfoNoName() throws Exception
    {
        long startTime = System.currentTimeMillis();
        int i = matchName.matchSalesCarSeriesNameLack();
        System.out.println("导入了" + i + "条");
        long endTime = System.currentTimeMillis();
        System.out.println("耗时:"+(endTime-startTime));
    }

    @Test
    public void matchPriceCarNameNew() throws Exception
    {
        long startTime = System.currentTimeMillis();
        int i = matchName.matchPriceCarNameNew();
        System.out.println("导入了" + i + "条");
        long endTime = System.currentTimeMillis();
        System.out.println("耗时:"+(endTime-startTime));
    }
}
