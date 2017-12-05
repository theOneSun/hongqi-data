package com.idata3d.hongqi.service;

import com.idata3d.hongqi.domain.SalesInfo;
import com.idata3d.hongqi.mapper.SalesInfoMapper;
import org.apache.log4j.Logger;
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
    private ImportRegionCascade importRegionCascade;

    @Resource
    private SalesCalculate salesCalculate;

    @Resource
    private ImportMarketOptionDict importMarketOptionDictService;

    @Resource
    private MatchName matchName;

    private final Logger logger = Logger.getLogger(this.getClass());


    /**
     * 每次导入更新新数据执行的方法
     */
    public void updateSalesInfoAndMarket(){
        this.updateSalesInfo();
        this.reImportRegionCascade();
        this.updateMarketClassified();
        this.reImportMarketOption();
    }

    /**
     * 更新销量表
     */
    private void updateSalesInfo()
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
        logger.info("销量表新增数据" + insertTotal + "条");
        //3.删除全国的数据
        int deleteTotal = salesInfoMapper.deleteWholeNationData();
        //4.计算全国数据并插入
        int importTotal = salesCalculate.calculateChinaTotal();
        logger.info("插入的全国数据是" + importTotal + "条,删除的全国数据是" + deleteTotal + "条");
        //5.匹配车型名称
        matchName.matchSalesCarSeriesName();
    }

    /**
     * 更新市场表
     */
    private void updateMarketClassified()
    {
        int i = importDataToMarketClassified.importData();
        logger.info("市场表导入了" + i + "条数据");
    }

    /**
     * 重新导入regionCascade
     */
    private void reImportRegionCascade()
    {
        int deleteCount = importRegionCascade.deleteAll();
        logger.info("清空cascade,清空了" + deleteCount + "条");

        long start = System.currentTimeMillis();
        int i = importRegionCascade.importDataToRegionCascade();
        long end = System.currentTimeMillis();
        logger.info("重新导入区域级联耗时: " + (end - start) + "导入了:  " + i + "条");
    }

    /**
     * 更新marketOption
     */
    private void reImportMarketOption()
    {

        int insertCount = importMarketOptionDictService.reImportData();
        logger.info("marketOption重新导入了" + insertCount + "条");
    }

}
