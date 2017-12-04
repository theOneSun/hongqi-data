package com.idata3d.hongqi.service;

import com.idata3d.hongqi.domain.SalesInfo;
import com.idata3d.hongqi.mapper.SalesInfoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author sunjian.
 */
@Service
public class SalesInfoService
{
    @Resource
    private ImportDataToMarketClassified importDataToMarketClassified;

    @Resource
    private SalesInfoMapper salesInfoMapper;

    @Resource
    private SalesCalculate salesCalculate;

    @Resource
    private MatchName matchName;

    //更新销量表
    public void updateSalesInfo()
    {
        /*
        1,分别读取sales_info_market_add表
        2,插入到sales_info中
        3,删除sales_info中全国的数据
        4,计算全国数据并插入
        5,匹配车型名称
         */
        //1.要增加的数据
        List<SalesInfo> insertList = salesInfoMapper.getAllAdd();
        // TODO: 2017/12/4 分多次插入
//        int addCount = salesInfoMapper.batchInsert(insertList);
        //2.分批次批量插入
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
        System.out.println("销量表新增数据"+insertTotal+"条");

        //3.删除全国的数据
        int deleteTotal = salesInfoMapper.deleteWholeNationData();
        //4.计算全国数据并插入
        int importTotal = salesCalculate.calculateChinaTotal();
        System.out.println("插入的全国数据是"+importTotal+"条,删除的全国数据是"+deleteTotal+"条");
        //5.匹配车型名称
        matchName.matchSalesCarSeriesName();

    }

    /**
     * 更新市场表
     */
    public void updateMarketClassified(){
        importDataToMarketClassified.importData();
    }
}
