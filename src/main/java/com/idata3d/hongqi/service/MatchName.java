package com.idata3d.hongqi.service;

import com.idata3d.hongqi.domain.CodeNameRelation;
import com.idata3d.hongqi.domain.PriceInfo;
import com.idata3d.hongqi.domain.SalesInfo;
import com.idata3d.hongqi.mapper.CodeNameRelationMapper;
import com.idata3d.hongqi.mapper.PriceInfoMapper;
import com.idata3d.hongqi.mapper.SalesInfoMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 匹配各个表中的车系名称
 *
 * @author sunjian.
 */
@Service
public class MatchName
{
    @Resource
    private CodeNameRelationMapper codeNameRelationMapper;
    @Resource
    private PriceInfoMapper priceInfoMapper;
    @Resource
    private SalesInfoMapper salesInfoMapper;

    //匹配价格表的车系名称
    public int matchPriceCarSeriesName()
    {
        //查询车系表
        List<CodeNameRelation> codeNameList = codeNameRelationMapper.getAll();
        //查询价格表
        List<PriceInfo> priceInfoList = priceInfoMapper.getAll();
        List<PriceInfo> codeGroupList = priceInfoMapper.getCodeGroup();
        List<PriceInfo> updateList = new ArrayList<>();
        if (priceInfoList.size() == 0)
        {
            return 0;
        }

        Map<String, String> codeNameMap = new HashMap<>();
        for (CodeNameRelation codeNameRelation : codeNameList)
        {
            codeNameMap.put(codeNameRelation.getCarSeriesCode(), codeNameRelation.getCarSeriesName());
        }
        String carSeriesCode;
        String name;

        for (PriceInfo priceInfo : codeGroupList)
        {
            carSeriesCode = priceInfo.getCarSeriesCode();
            name = codeNameMap.get(carSeriesCode);
            if (StringUtils.isBlank(name))
            {
                System.out.println("此price中的code未能匹配到车系名称: " + carSeriesCode);
            } else
            {
                priceInfo.setCarSeriesName(name);
                updateList.add(priceInfo);
            }
        }

        //分批次批量插入
        int size = updateList.size();
        int insertTotal = 0;
        List<PriceInfo> currentImportList;
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
                currentImportList = updateList.stream().skip(i * 3000).limit(3000).collect(Collectors.toList());
                insertTotal += priceInfoMapper.batchUpdate(currentImportList);
            }
        } else
        {
            insertTotal = priceInfoMapper.batchUpdate(updateList);
        }

        return insertTotal;
    }

    //新思路匹配价格表
    public int matchPriceCarNameNew()
    {
        //查询车系表
        List<CodeNameRelation> codeNameList = codeNameRelationMapper.getAll();
        //查询价格表

        List<PriceInfo> updateList = new ArrayList<>();

        Map<String, String> codeNameMap = new HashMap<>();
        for (CodeNameRelation codeNameRelation : codeNameList)
        {
            codeNameMap.put(codeNameRelation.getCarSeriesCode(), codeNameRelation.getCarSeriesName());
        }

        PriceInfo priceInfo;
        Set<Map.Entry<String, String>> entrySet = codeNameMap.entrySet();
        for (Map.Entry<String, String> entry : entrySet)
        {
            priceInfo = new PriceInfo();
            priceInfo.setCarSeriesCode(entry.getKey());
            priceInfo.setCarSeriesName(entry.getValue());
            updateList.add(priceInfo);
        }

        //分批次批量插入
        int size = updateList.size();
        int insertTotal = 0;
        List<PriceInfo> currentImportList;
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
                currentImportList = updateList.stream().skip(i * 3000).limit(3000).collect(Collectors.toList());
                insertTotal += priceInfoMapper.batchUpdate(currentImportList);
            }
        } else
        {
            insertTotal = priceInfoMapper.batchUpdate(updateList);
        }

        return insertTotal;
    }

    /**
     * 匹配销量表的carSeriesName
     * @return
     */
    public int matchSalesCarSeriesName(){
        /*
        1.获得所有车系名称对应
        2,查询所有salesInfo表
         */
        //查询车系表
        List<CodeNameRelation> codeNameList = codeNameRelationMapper.getAll();
        Map<String,String> codeNameMap = new HashMap<>();
        List<SalesInfo> noNameList = salesInfoMapper.getNoNameList();
        for (CodeNameRelation codeName : codeNameList)
        {
            codeNameMap.put(codeName.getCarSeriesCode(), codeName.getCarSeriesName());
        }

        String carSeriesCode;
        for (SalesInfo info : noNameList)
        {
            carSeriesCode = info.getCarSeriesCode();
            info.setCarSeriesName(codeNameMap.get(carSeriesCode));
        }

        return salesInfoMapper.batchUpdate(noNameList);
    }

    /**
     * 匹配销量表的缺失的carSeriesName
     * @return
     */
    public int matchSalesCarSeriesNameLack(){
        /*
        1.获得所有车系名称对应
        2,查询所有缺失name的salesInfo
         */
        //查询车系表
        List<CodeNameRelation> codeNameList = codeNameRelationMapper.getAll();
        Map<String,String> codeNameMap = new HashMap<>();
        List<SalesInfo> updateList = new ArrayList<>();
        for (CodeNameRelation codeName : codeNameList)
        {
            codeNameMap.put(codeName.getCarSeriesCode(), codeName.getCarSeriesName());
        }

        Set<Map.Entry<String, String>> entrySet = codeNameMap.entrySet();
        SalesInfo salesInfo;
        for (Map.Entry<String, String> entry : entrySet)
        {
            salesInfo = new SalesInfo();
            salesInfo.setCarSeriesCode(entry.getKey());
            salesInfo.setCarSeriesName(entry.getValue());
            updateList.add(salesInfo);
        }

        return salesInfoMapper.batchUpdate(updateList);
    }

    //匹配销量表全部的carSeriesName,新方法根据id做
    public int matchAllSalesCarSeriesNameById(){
        /*
        1.获得所有车系名称对应
        2,查询所有salesInfo表
         */
        //查询车系表
        List<CodeNameRelation> codeNameList = codeNameRelationMapper.getAll();
        Map<String,String> codeNameMap = new HashMap<>();
//        List<SalesInfo> updateList = new ArrayList<>();
        //封装code和name对应的map
        for (CodeNameRelation codeName : codeNameList)
        {
            codeNameMap.put(codeName.getCarSeriesCode(), codeName.getCarSeriesName());
        }
        List<SalesInfo> salesInfoList = salesInfoMapper.getAllIdCode();
        for (SalesInfo salesInfo : salesInfoList)
        {
            salesInfo.setCarSeriesName(codeNameMap.get(salesInfo.getCarSeriesCode()));
        }
        return salesInfoMapper.batchUpdateById(salesInfoList);
    }

    //匹配销量表缺失的carSeriesName,新方法根据id做
    public int matchLackSalesCarSeriesNameById(){
        int perCount = 1000;
        /*
        1.获得所有车系名称对应
        2,查询所有salesInfo表
         */
        //查询车系表
        List<CodeNameRelation> codeNameList = codeNameRelationMapper.getAll();
        Map<String,String> codeNameMap = new HashMap<>();
        //封装code和name对应的map
        for (CodeNameRelation codeName : codeNameList)
        {
            codeNameMap.put(codeName.getCarSeriesCode(), codeName.getCarSeriesName());
        }

        /*
        查询总数分批次修改
         */
        int nameNullTotal = salesInfoMapper.getNameNullTotal();
        int insertTotal = 0;
        int currentTotal;
        int loopTimes = nameNullTotal / perCount + 1;
        List<SalesInfo> salesInfoList;
        for (int i = 0; i < loopTimes; i++)
        {
            System.out.println("第"+i+"/"+loopTimes+"次");
            salesInfoList = salesInfoMapper.getIdCodeLackNameLimit(i * perCount, (i + 1) * perCount);
            for (SalesInfo salesInfo : salesInfoList)
            {
                salesInfo.setCarSeriesName(codeNameMap.get(salesInfo.getCarSeriesCode()));
            }
            currentTotal = salesInfoMapper.batchUpdateById(salesInfoList);
            insertTotal = insertTotal + currentTotal;
        }
        return insertTotal;
    }

}
