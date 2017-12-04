package com.idata3d.hongqi.base;

import com.idata3d.hongqi.domain.MarketClassified;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

/**
 * @author sunjian.
 */

public class EqualsTest
{
    @Test
    public void ObjectEqualsTest() throws Exception
    {
        Set<MarketClassified> set = new HashSet<>();
        MarketClassified marketClassified = new MarketClassified();
        marketClassified.setId("1");
        marketClassified.setCarSeriesCode("car17001");
        marketClassified.setBrand("宝马");
        set.add(marketClassified);

        MarketClassified marketClassified1 = new MarketClassified();
        marketClassified1.setBrand("宝马");
        marketClassified1.setCarSeriesCode("car17001");
        marketClassified1.setId("1");
        boolean contains = set.contains(marketClassified1);
        System.out.println(contains);
    }
}
