package com.idata3d.hongqi.mapper;

import com.idata3d.hongqi.domain.RegionCascade;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author sunjian.
 */
@Mapper
public interface RegionCascadeMapper
{
    int batchInsert(@Param("insertList") List<RegionCascade> insertList);
    //清空
    int deleteAll();
}
