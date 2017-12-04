package com.idata3d.hongqi.mapper;

import com.idata3d.hongqi.domain.UserDefinedMarket;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 临时测试使用,无真正用途
 * @author sunjian.
 */
@Mapper
public interface UserDefinedMarketMapper
{
    List<UserDefinedMarket> getAll();

    int batchInsert(@Param("insertList") List<UserDefinedMarket> insertList);
}
