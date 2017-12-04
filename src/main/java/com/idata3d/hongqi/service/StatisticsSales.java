package com.idata3d.hongqi.service;

import com.idata3d.hongqi.domain.SalesInfo;
import com.idata3d.hongqi.mapper.SalesInfoMapper;
import com.idata3d.hongqi.util.UUIDUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author sunjian.
 */
@Service
public class StatisticsSales
{
    @Resource
    private SalesInfoMapper salesInfoMapper;

    public int calculateRegionTotal(){
        /*
        1.查询每个月的累计销量
        2.插入数据库
         */
        YearMonth january15 = YearMonth.of(2015,1);
        YearMonth january16 = YearMonth.of(2016,1);
        YearMonth january17 = YearMonth.of(2017,1);
        //查询每个月的累计销量
        List<SalesInfo> regionTotalSales;
        List<SalesInfo> insertList = new ArrayList<>();
        YearMonth currentYearMonth;
        //15年
        for (int i = 1; i <= 12; i++)
        {
            currentYearMonth = YearMonth.of(2015, i);
            regionTotalSales = salesInfoMapper.getRegionTotalSales(january15, currentYearMonth);
            insertList = addInsertRegionStatistics(regionTotalSales, insertList,currentYearMonth);
        }
        //16年
        for (int i = 1; i <= 12; i++)
        {
            currentYearMonth = YearMonth.of(2016, i);
            regionTotalSales = salesInfoMapper.getRegionTotalSales(january16, currentYearMonth);
            insertList = addInsertRegionStatistics(regionTotalSales, insertList,currentYearMonth);
        }
        //17年
        for (int i = 1; i <= 7; i++)
        {
            currentYearMonth = YearMonth.of(2017, i);
            regionTotalSales = salesInfoMapper.getRegionTotalSales(january17, currentYearMonth);
            insertList = addInsertRegionStatistics(regionTotalSales, insertList,currentYearMonth);
        }
        //插入数据库
        //批量插入
        //分批次批量插入
        int size = insertList.size();
        int insertTotal = 0;
        List<SalesInfo> currentImportList;
        if (size > 3000)
        {
            int result = size / 3000;//商
            int remainder = size % 3000;//余数
            if (remainder > 0)
            {
                //没有整除,循环result+1次
                result += 1;
            }
            for (int i = 0; i < result; i++)
            {
                currentImportList = insertList.stream().skip(i * 3000).limit(3000).collect(Collectors.toList());
                insertTotal += salesInfoMapper.batchInsertStatistics(currentImportList);
            }
        } else
        {
            insertTotal = salesInfoMapper.batchInsertStatistics(insertList);
        }
        return insertTotal;
    }

    /**
     * 添加累计区域销量插入的数据
     * @param regionSalesList
     * @param insertList
     * @return
     */
    private List<SalesInfo> addInsertRegionStatistics(List<SalesInfo> regionSalesList,List<SalesInfo> insertList,YearMonth yearMonth){
        SalesInfo salesInfo;
        for (SalesInfo regionSalesInfo : regionSalesList)
        {
            salesInfo = new SalesInfo();
            salesInfo.setId(UUIDUtils.getUUID());
            salesInfo.setRegion(regionSalesInfo.getRegion());
            salesInfo.setSales(regionSalesInfo.getSales());
            salesInfo.setYearMonth(yearMonth);
            insertList.add(salesInfo);
        }
        return insertList;
    }
}
