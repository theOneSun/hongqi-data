package com.idata3d.hongqi.mapper;

import com.idata3d.hongqi.domain.BoxStructure;
import com.idata3d.hongqi.domain.MarketClassified;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import javax.swing.*;
import java.util.List;
import java.util.Set;

/**
 * @author sunjian.
 */
@Mapper
public interface MarketClassifiedMapper
{
    /**
     * 批量插入
     * @param marketClassifiedSet
     * @return
     */
    int batchInsert(@Param("insertSet") Set<MarketClassified> marketClassifiedSet);

    Set<MarketClassified> getAllWithOutId();

    Set<MarketClassified> getByMarketName(@Param("marketName") String marketName);

    //查询所有集团
    @Select("SELECT `group` FROM market_classified GROUP BY `group`")
    List<String> getAllGroup();

    //查询所有品牌
    @Select("SELECT brand from market_classified GROUP BY brand")
    List<String> getAllBrand();

    //查询所有系别
    @Select("SELECT series from market_classified GROUP BY series")
    List<String> getAllSeries();

    //查询所有箱体结构(1,2,3级)
    //查询carType
    @Select("SELECT car_type FROM market_classified GROUP BY car_type")
    List<BoxStructure> getAllCarType();

    //查询carType,carSegment
    @Select("SELECT car_type,car_segment FROM market_classified GROUP BY car_type,car_segment")
    List<BoxStructure> getAllCarTypeAndCarSegment();

    //查询carType,carSegment,carSubSegment
    @Select("SELECT car_type,car_segment,car_sub_segment FROM market_classified GROUP BY car_type,car_segment,car_sub_segment")
    List<BoxStructure> getAllBoxStructure();

    //查询所有豪华非豪华
    @Select("SELECT luxury from market_classified GROUP BY luxury")
    List<String> getAllLuxury();

    //查询所有新能源
    @Select("SELECT energy from market_classified GROUP BY energy")
    List<String> getAllEnergy();
}
