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
    int batchUpdate(@Param("updateList") List<SalesInfo> salesInfoList);

    /**
     * 统计全国数据
     */
    List<SalesInfo> getChinaTotalByCodeAndYearMonth();

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
}
