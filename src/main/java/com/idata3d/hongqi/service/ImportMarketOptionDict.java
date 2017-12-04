package com.idata3d.hongqi.service;

import com.idata3d.hongqi.domain.BoxStructure;
import com.idata3d.hongqi.domain.MarketClassified;
import com.idata3d.hongqi.domain.MarketOptionDict;
import com.idata3d.hongqi.domain.MarketSelect;
import com.idata3d.hongqi.mapper.MarketClassifiedMapper;
import com.idata3d.hongqi.mapper.MarketOptionDictMapper;
import com.idata3d.hongqi.mapper.MarketSelectMapper;
import com.idata3d.hongqi.util.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author sunjian.
 */
@Service
public class ImportMarketOptionDict
{
    @Autowired
    private MarketOptionDictMapper dictMapper;
    @Autowired
    private MarketClassifiedMapper marketClassifiedMapper;


    public void importData()
    {
        /*
        思路
        1.查询所有
        2,批量插入
         */
        //查询group
        List<MarketOptionDict> optionDictList = new ArrayList<>();
        List<String> groupList = marketClassifiedMapper.getAllGroup();
        List<String> brandList = marketClassifiedMapper.getAllBrand();
        List<String> seriesList = marketClassifiedMapper.getAllSeries();
        List<String> luxuryList = marketClassifiedMapper.getAllLuxury();
        List<String> energyList = marketClassifiedMapper.getAllEnergy();
        energyList.remove(null);

        List<BoxStructure> carTypeList = marketClassifiedMapper.getAllCarType();
        List<BoxStructure> segmentList = marketClassifiedMapper.getAllCarTypeAndCarSegment();
        List<BoxStructure> subSegmentList = marketClassifiedMapper.getAllBoxStructure();

        for (String group : groupList)
        {
            optionDictList.add(new MarketOptionDict(UUIDUtils.getUUID(), "集团", group, null, null));
        }
        for (String brand : brandList)
        {
            optionDictList.add(new MarketOptionDict(UUIDUtils.getUUID(), "品牌", brand, null, null));
        }
        for (String series : seriesList)
        {
            optionDictList.add(new MarketOptionDict(UUIDUtils.getUUID(), "系别", series, null, null));
        }
        for (String luxury : luxuryList)
        {
            optionDictList.add(new MarketOptionDict(UUIDUtils.getUUID(), "是否豪华车", luxury, null, null));
        }
        for (String energy : energyList)
        {
            optionDictList.add(new MarketOptionDict(UUIDUtils.getUUID(), "新能源", energy, null, null));
        }
        for (BoxStructure carType : carTypeList)
        {
            optionDictList.add(new MarketOptionDict(UUIDUtils.getUUID(), "箱体结构", carType.getCarType(), null, null));
        }
        for (BoxStructure segment : segmentList)
        {
            optionDictList.add(new MarketOptionDict(UUIDUtils.getUUID(), "箱体结构", segment.getCarType(), segment.getCarSegment(), null));
        }
        for (BoxStructure boxStructure : subSegmentList)
        {
            optionDictList.add(new MarketOptionDict(UUIDUtils.getUUID(), "箱体结构", boxStructure.getCarType(), boxStructure
                    .getCarSegment(), boxStructure.getCarSubSegment()));
        }

        //插入
        int result = dictMapper.batchInsert(optionDictList);
        System.out.println("插入了: " + result + " 条");

    }

    private Set<String> getCateGoryList(Map<String, List<MarketClassified>> categoryMap)
    {
        categoryMap.keySet();
        return null;
    }
}
