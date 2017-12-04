package com.idata3d.hongqi.mapper;

import com.idata3d.hongqi.domain.MarketSelect;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author sunjian.
 */
@Mapper
public interface MarketSelectMapper
{
    @Select("select * from market_select")
    List<MarketSelect> getAll();


}
