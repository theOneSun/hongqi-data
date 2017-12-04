package com.idata3d.hongqi.service;

import com.idata3d.hongqi.domain.RegionCascade;
import com.idata3d.hongqi.domain.SalesInfo;
import com.idata3d.hongqi.mapper.RegionCascadeMapper;
import com.idata3d.hongqi.mapper.SalesInfoMapper;
import com.idata3d.hongqi.util.UUIDUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.swing.plaf.synth.Region;
import java.time.Year;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author sunjian.
 */
@Service
public class ImportRegionCascade
{
    @Resource
    private RegionCascadeMapper regionCascadeMapper;
    @Resource
    private SalesInfoMapper salesInfoMapper;

    public int importDataToRegionCascade()
    {
        List<RegionCascade> regionCascadeList = salesInfoMapper.getRegionCascade();
        List<RegionCascade> insertList = new ArrayList<>();
        YearMonth maxYearMonth = YearMonth.of(2017, 1);
        YearMonth minYearMonth = YearMonth.of(2014, 12);
        YearMonth yearMonth;
        for (RegionCascade cascade : regionCascadeList)
        {
            yearMonth = cascade.getYearMonth();
            if (cascade.getPrimaryArea().contains("市"))
            {
                cascade.setSecondaryArea(null);
            }
            if (yearMonth.isBefore(maxYearMonth) && yearMonth.isAfter(minYearMonth)){
                cascade.setSecondaryArea(null);
            }

        }
        Set<RegionCascade> set = new HashSet<>();
        set.addAll(regionCascadeList);
        insertList.addAll(set);
        System.out.println(regionCascadeList.size());
        System.out.println(insertList.size());
        //setId
        for (RegionCascade cascade : insertList)
        {
            cascade.setId(UUIDUtils.getUUID());
        }

        //分批次批量插入
        int size = insertList.size();
        int insertTotal = 0;
        List<RegionCascade> currentImportList;
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
                insertTotal += regionCascadeMapper.batchInsert(currentImportList);
            }
        } else
        {
            insertTotal = regionCascadeMapper.batchInsert(insertList);
        }
        return insertTotal;
    }
}
