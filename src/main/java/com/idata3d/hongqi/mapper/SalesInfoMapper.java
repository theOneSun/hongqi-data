package com.idata3d.hongqi.mapper;

import com.idata3d.hongqi.domain.RegionCascade;
import com.idata3d.hongqi.domain.SalesInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.YearMonth;
import java.util.List;

/**
 * @author sunjian.
 */
@Mapper
public interface SalesInfoMapper
{
    List<SalesInfo> getAll();
    //批量更新
    int batchUpdate(@Param("updateList") List<SalesInfo> salesInfoList);

    //批量更新
    int batchUpdateById(@Param("updateList") List<SalesInfo> salesInfoList);

    /**
     * 统计全国数据
     */
    List<SalesInfo> getChinaTotalByCodeAndYearMonth();
    /**
     * 统计全国数据
     */
    List<SalesInfo> getChinaTotalByCodeAndTypeCodeAndYearMonth();

    int batchInsert(@Param("insertList") List<SalesInfo> insertList);

    List<SalesInfo> getNoNameList();

    //生成区域级联
    List<RegionCascade> getRegionCascade();

    /**
     * 查询各大区总销量
     *
     * @param beginYearMonth
     * @param endYearMonth
     * @return
     */
    List<SalesInfo> getRegionTotalSales(@Param("beginYearMonth") YearMonth beginYearMonth, @Param("endYearMonth") YearMonth endYearMonth);

    int batchInsertStatistics(@Param("insertList") List<SalesInfo> insertList);

    //导出csv
    List<SalesInfo> exportSalesInfo(@Param("start") int start,@Param("end") int end);

    //删除全国的数据
    int deleteWholeNationData();


    //查询总数
    int getTotal();

    //查询新增的所有
    List<SalesInfo> getAllAdd();

    /**
     * 查询所有对象,只查id和code,用于更新carSeriesName
     * @return
     */
    List<SalesInfo> getAllIdCode();
    /**
     * 查询所有缺失车型名称的对象,只查id和code,用于更新carSeriesName
     * @return
     */
    List<SalesInfo> getAllIdCodeLackName();

    /**
     * 查询limit(start,end)缺失车型名称的对象,只查id和code,用于更新carSeriesName
     * @return
     */
    List<SalesInfo> getIdCodeLackNameLimit(@Param("start") int start,@Param("end") int end);

    /**
     * 查询车型名称为空的总数
     * @return
     */
    int getNameNullTotal();

    /**
     * 根据id集合修改车型名称
     * @param idList
     * @param carSeriesName
     * @return
     */
    int setNameByIdList(@Param("idList") List<String> idList,@Param("carSeriesName") String carSeriesName);

}
