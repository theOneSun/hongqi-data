package com.idata3d.hongqi.mapper;

import com.idata3d.hongqi.domain.MarketClassified;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

/**
 * @author sunjian.
 */
@Mapper
public interface SalesMarketMapper
{
    @Select("select * from sales_market")
    Set<MarketClassified> getAll();

    Set<MarketClassified> getGroupByResult();

    /**
     *  从销量市场更新表查询
     * @return
     */
    Set<MarketClassified> getGroupByResultFromSalesMarketAdd();


}
