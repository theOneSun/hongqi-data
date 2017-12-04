package com.idata3d.hongqi.mapper;

import com.idata3d.hongqi.domain.MarketOptionDict;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author sunjian.
 */
@Mapper
public interface MarketOptionDictMapper
{
    int batchInsert(@Param("dictList") List<MarketOptionDict> dictList);
}
