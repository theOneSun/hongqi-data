package com.idata3d.hongqi.service;

import com.idata3d.hongqi.domain.SalesInfo;
import com.idata3d.hongqi.mapper.SalesInfoMapper;
import com.idata3d.hongqi.util.UUIDUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author sunjian.
 */
@Service
@Transactional
public class SalesCalculate
{
    @Resource
    private SalesInfoMapper salesInfoMapper;

    private static final String CHINA_TOTAL = "全国";

    public int calculateChinaTotal()
    {
        /*
        1.分组查询计算(根据车系和年月分组);
        2.插入数据
         */
        List<SalesInfo> insertList = new ArrayList<>();
        SalesInfo totalSalesInfo;
        String id;
        List<SalesInfo> totalList = salesInfoMapper.getChinaTotalByCodeAndYearMonth();
        for (SalesInfo salesInfo : totalList)
        {
            id = UUIDUtils.getUUID();
            totalSalesInfo = new SalesInfo(id, salesInfo.getCarSeriesCode(), salesInfo.getCarSeriesName(), salesInfo.getCarBaseTypeCode(), salesInfo
                    .getCarBaseTypeName(), salesInfo.getCarType(), salesInfo.getYearMonth(), CHINA_TOTAL, CHINA_TOTAL, CHINA_TOTAL, salesInfo
                    .getSales());
            insertList.add(totalSalesInfo);
        }

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
                insertTotal += salesInfoMapper.batchInsert(currentImportList);
            }
        } else
        {
            insertTotal = salesInfoMapper.batchInsert(insertList);
        }
        return insertTotal;
    }


    /**
     * 统计全国数据根据车型id和模糊车型id和年月分组
     *
     * @return
     */
    public int calculateChinaTotal2()
    {
        Logger logger = Logger.getLogger(this.getClass());
        //删除全国数据
        /*int deleteCount = salesInfoMapper.deleteWholeNationData();
        logger.info("删除了原全国数据" + deleteCount + "条");*/
        /*
        1.分组查询计算(根据车系和年月分组);
        2.插入数据
         */
        List<SalesInfo> insertList = new ArrayList<>();
        SalesInfo totalSalesInfo;
        String id;
        List<SalesInfo> totalList = salesInfoMapper.getChinaTotalByCodeAndTypeCodeAndYearMonth();
        for (SalesInfo salesInfo : totalList)
        {
            id = UUIDUtils.getUUID();
            totalSalesInfo = new SalesInfo(id, salesInfo.getCarSeriesCode(), salesInfo.getCarSeriesName(), salesInfo.getCarBaseTypeCode(), salesInfo
                    .getCarBaseTypeName(), salesInfo.getCarType(), salesInfo.getYearMonth(), CHINA_TOTAL, CHINA_TOTAL, CHINA_TOTAL, salesInfo
                    .getSales());
            insertList.add(totalSalesInfo);
        }

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
                insertTotal += salesInfoMapper.batchInsert(currentImportList);
            }
        } else
        {
            insertTotal = salesInfoMapper.batchInsert(insertList);
        }
        logger.info("新插入了全国数据" + insertTotal + "条");
        return insertTotal;
    }
}
