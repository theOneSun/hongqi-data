package com.idata3d.hongqi.tempTest;

import com.idata3d.hongqi.HongqiDataApplication;
import com.idata3d.hongqi.domain.MarketClassified;
import com.idata3d.hongqi.domain.UserDefinedMarket;
import com.idata3d.hongqi.mapper.UserDefinedMarketMapper;
import com.idata3d.hongqi.util.UUIDUtils;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sunjian.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HongqiDataApplication.class)
@TestPropertySource("classpath:application.properties")
public class UserDefinedTest
{
    @Resource
    private UserDefinedMarketMapper userDefinedMarketMapper;

    @Test
    public void testSelect(){
        List<UserDefinedMarket> all = userDefinedMarketMapper.getAll();
        all.forEach(System.out::println);
    }
    @Test
    public void testBatchInsert(){
        List<UserDefinedMarket> insertList = new ArrayList<>();
        for (int i = 0; i < 5; i++)
        {
            insertList.add(new UserDefinedMarket(UUIDUtils.getUUID(), "test", LocalDateTime.now(), null));
        }
        int batchInsert = userDefinedMarketMapper.batchInsert(insertList);
        System.out.println(batchInsert);
    }
    @Test
    public void testCreate(){
//        UserDefinedMarket userDefinedMarket = new UserDefinedMarket();
        UserDefinedMarket userDefinedMarket1 = new UserDefinedMarket("1", "12", LocalDateTime.now(), new MarketClassified());
//        String id = userDefinedMarket.getMarketClassified().getId();
        String id = userDefinedMarket1.getMarketClassified().getId();
        System.out.println(id);
    }
}
