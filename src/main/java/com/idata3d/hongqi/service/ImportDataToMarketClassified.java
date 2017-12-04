package com.idata3d.hongqi.service;

import com.idata3d.hongqi.constant.MarketEnum;
import com.idata3d.hongqi.domain.MarketClassified;
import com.idata3d.hongqi.mapper.SalesMarketMapper;
import com.idata3d.hongqi.mapper.MarketClassifiedMapper;
import com.idata3d.hongqi.util.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * @author sunjian.
 */
@Service
public class ImportDataToMarketClassified
{
    public static final String SEDAN = "Sedan";
    public static final String SUV = "SUV";
    public static final String MPV = "MPV";
    public static final String LIGHT_BUS = "轻客";
    public static final String MINI_BUS = "微客";

    public static final String SEGEMENT_A = "A";
    public static final String SEGEMENT_A0 = "A0";
    public static final String SEGEMENT_A00 = "A00";
    public static final String SEGEMENT_B = "B";
    public static final String SEGEMENT_C = "C";
    public static final String SEGEMENT_D = "D";

    public static final String LUXURY_CAR = "豪华车";
    public static final String UN_LUXURY_CAR = "非豪华车";

    @Autowired
    private SalesMarketMapper readMapper;
    @Autowired
    private MarketClassifiedMapper writeMapper;

    //本次导入数据需要新增的
    private Set<MarketClassified> passengerNewSet = new HashSet<>();
    private Set<MarketClassified> sedanNewSet = new HashSet<>();
    private Set<MarketClassified> suvNewSet = new HashSet<>();
    private Set<MarketClassified> mpvNewSet = new HashSet<>();
    private Set<MarketClassified> luxuryNewSet = new HashSet<>();
    private Set<MarketClassified> luxurySedanNewSet = new HashSet<>();
    private Set<MarketClassified> luxurySuvNewSet = new HashSet<>();
    private Set<MarketClassified> luxuryMpvNewSet = new HashSet<>();

    /**
     * 导入数据
     *
     * @return
     */
    public int importData()
    {
        //定义8各市场的set

        //已有的
        Set<MarketClassified> passengerSet = writeMapper.getByMarketName(MarketEnum.PASSENGER_MARKET.getMarketName());
        Set<MarketClassified> sedanSet = writeMapper.getByMarketName(MarketEnum.SEDAN_MARKET.getMarketName());
        Set<MarketClassified> suvSet = writeMapper.getByMarketName(MarketEnum.SUV_MARKET.getMarketName());
        Set<MarketClassified> mpvSet = writeMapper.getByMarketName(MarketEnum.MPV_CAR_MARKET.getMarketName());
        Set<MarketClassified> luxurySet = writeMapper.getByMarketName(MarketEnum.LUXURY_MARKET.getMarketName());
        Set<MarketClassified> luxurySedanSet = writeMapper.getByMarketName(MarketEnum.LUXURY_SEDAN_MARKET.getMarketName());
        Set<MarketClassified> luxurySuvSet = writeMapper.getByMarketName(MarketEnum.LUXURY_SUV_MARKET.getMarketName());
        Set<MarketClassified> luxuryMpvSet = writeMapper.getByMarketName(MarketEnum.LUXURY_MPV_MARKET.getMarketName());

        /*
        1.查询已有的,不查id
        2.查询新增的,遍历判断市场
         --判断新增的是否已存在
         --如果存在,pass
         --如果不存在,给id入库,已有的set新增

         */
        //查询已有的
        Set<MarketClassified> existSet = writeMapper.getAllWithOutId();
        //查询新增的数据(根据表选择方法,新的使用salesMarketAdd)
//        Set<MarketClassified> groupByResult = readMapper.getGroupByResult();
        Set<MarketClassified> groupByResult = readMapper.getGroupByResultFromSalesMarketAdd();
//        Set<MarketClassified> checkSet = readMapper.getAll();

        if (existSet == null || existSet.size() < 1)
        {
            //原来没有的,第一次导入数据
            for (MarketClassified marketClassified : groupByResult)
            {
                //判断市场,并添加到新增的对应市场的集合中
                dispatchMarket(marketClassified);
            }
        } else
        {
            for (MarketClassified marketClassified : groupByResult)
            {
                //判断所属市场
                dispatchMarket(marketClassified);
            }
            this.passengerNewSet.removeAll(passengerSet);
            this.sedanNewSet.removeAll(sedanSet);
            this.suvNewSet.removeAll(suvSet);
            this.mpvNewSet.removeAll(mpvSet);
            this.luxuryNewSet.removeAll(luxurySet);
            this.luxurySedanNewSet.removeAll(luxurySedanSet);
            this.luxurySuvNewSet.removeAll(luxurySuvSet);
            this.luxuryMpvNewSet.removeAll(luxuryMpvSet);
        }

        //遍历设置名称和id
        setMarketNameAndId();
        //插入数据
        int newPassengerCount = 0;
        if (this.passengerNewSet != null && this.passengerNewSet.size() > 0)
        {
            newPassengerCount = writeMapper.batchInsert(this.passengerNewSet);
        }

        int newSedanCount = 0;
        if (this.sedanNewSet != null && this.sedanNewSet.size() > 0)
        {
            newSedanCount = writeMapper.batchInsert(this.sedanNewSet);
        }

        int newSuvCount = 0;
        if (this.suvNewSet != null && this.suvNewSet.size() > 0)
        {
            newSuvCount = writeMapper.batchInsert(this.suvNewSet);
        }

        int newMpvCount = 0;
        if (this.mpvNewSet != null && this.mpvNewSet.size() > 0)
        {
            newMpvCount = writeMapper.batchInsert(this.mpvNewSet);
        }

        int newLuxuryCount = 0;
        if (this.luxuryNewSet != null && this.luxuryNewSet.size() > 0)
        {
            newLuxuryCount = writeMapper.batchInsert(this.luxuryNewSet);
        }

        int newLuxurySedanCount = 0;
        if (this.luxurySedanNewSet != null && this.luxurySedanNewSet.size() > 0)
        {
            newLuxurySedanCount = writeMapper.batchInsert(this.luxurySedanNewSet);
        }

        int newLuxurySuvCount = 0;
        if (this.luxurySuvNewSet != null && this.luxurySuvNewSet.size() > 0)
        {
            newLuxurySuvCount = writeMapper.batchInsert(this.luxurySuvNewSet);
        }

        int newLuxuryMpvCount = 0;
        if (this.luxuryMpvNewSet != null && this.luxuryMpvNewSet.size() > 0)
        {
            newLuxuryMpvCount = writeMapper.batchInsert(this.luxuryMpvNewSet);
        }

        return newPassengerCount + newSedanCount + newSuvCount + newMpvCount + newLuxuryCount + newLuxurySedanCount + newLuxurySuvCount + newLuxuryMpvCount;
    }

    private void dispatchMarket(MarketClassified marketClassified)
    {
        //判断乘用车市场
        if (SEDAN.equals(marketClassified.getCarType()) || SUV.equals(marketClassified.getCarType()) || MPV.equals(marketClassified
                .getCarType()))
        {
            //满足乘用车市场
            passengerNewSet.add(new MarketClassified(marketClassified));
        }
        //轿车市场
        if (SEDAN.equals(marketClassified.getCarType()) && (SEGEMENT_A.equals(marketClassified.getCarSegment()) || SEGEMENT_A0
                .equals(marketClassified.getCarSegment()) || SEGEMENT_A00.equals(marketClassified.getCarSegment()) || SEGEMENT_B
                .equals(marketClassified.getCarSegment()) || SEGEMENT_C.equals(marketClassified.getCarSegment()) || SEGEMENT_D
                .equals(marketClassified.getCarSegment())))
        {
            sedanNewSet.add(new MarketClassified(marketClassified));
        }
        //SUV市场
        if (SUV.equals(marketClassified.getCarType()) && (SEGEMENT_A.equals(marketClassified.getCarSegment()) || SEGEMENT_A0
                .equals(marketClassified.getCarSegment()) || SEGEMENT_B.equals(marketClassified.getCarSegment()) || SEGEMENT_C
                .equals(marketClassified.getCarSegment()) || SEGEMENT_D.equals(marketClassified.getCarSegment())))
        {
            suvNewSet.add(new MarketClassified(marketClassified));
        }
        //MPV市场
        if (MPV.equals(marketClassified.getCarType()) && (SEGEMENT_A.equals(marketClassified.getCarSegment()) || SEGEMENT_A0
                .equals(marketClassified.getCarSegment()) || SEGEMENT_B.equals(marketClassified.getCarSegment()) || SEGEMENT_B
                .equals(marketClassified.getCarSegment())))
        {
            mpvNewSet.add(new MarketClassified(marketClassified));
        }
        //豪华车市场
        if (LUXURY_CAR.equals(marketClassified.getLuxury()) && (SEDAN.equals(marketClassified.getCarType()) || SUV.equals(marketClassified
                .getCarType()) || MPV.equals(marketClassified.getCarType())))
        {
            //满足乘用车市场
            luxuryNewSet.add(new MarketClassified(marketClassified));
        }
        //豪华轿车市场
        if (LUXURY_CAR.equals(marketClassified.getLuxury()) && SEDAN.equals(marketClassified.getCarType()) && (SEGEMENT_A
                .equals(marketClassified.getCarSegment()) || SEGEMENT_A0.equals(marketClassified.getCarSegment()) || SEGEMENT_A00
                .equals(marketClassified.getCarSegment()) || SEGEMENT_B.equals(marketClassified.getCarSegment()) || SEGEMENT_C
                .equals(marketClassified.getCarSegment()) || SEGEMENT_D.equals(marketClassified.getCarSegment())))
        {
            luxurySedanNewSet.add(new MarketClassified(marketClassified));
        }
        //豪华SUV市场
        if (LUXURY_CAR.equals(marketClassified.getLuxury()) && SUV.equals(marketClassified.getCarType()) && (SEGEMENT_A.equals(marketClassified
                .getCarSegment()) || SEGEMENT_A0.equals(marketClassified.getCarSegment()) || SEGEMENT_B.equals(marketClassified
                .getCarSegment()) || SEGEMENT_C.equals(marketClassified.getCarSegment()) || SEGEMENT_D.equals(marketClassified
                .getCarSegment())))
        {
            luxurySuvNewSet.add(new MarketClassified(marketClassified));
        }
        //豪华MPV市场
        if (LUXURY_CAR.equals(marketClassified.getLuxury()) && MPV.equals(marketClassified.getCarType()) && (SEGEMENT_A.equals(marketClassified
                .getCarSegment()) || SEGEMENT_A0.equals(marketClassified.getCarSegment()) || SEGEMENT_B.equals(marketClassified
                .getCarSegment()) || SEGEMENT_C.equals(marketClassified.getCarSegment())))
        {
            luxuryMpvNewSet.add(new MarketClassified(marketClassified));
        }
    }

    /**
     * 设置市场名称和id
     */
    private void setMarketNameAndId()
    {
        //乘用车
        for (MarketClassified marketClassified : this.passengerNewSet)
        {
            marketClassified.setId(UUIDUtils.getUUID());
            marketClassified.setMarketName(MarketEnum.PASSENGER_MARKET.getMarketName());
        }
        //轿车
        for (MarketClassified marketClassified : this.sedanNewSet)
        {
            marketClassified.setId(UUIDUtils.getUUID());
            marketClassified.setMarketName(MarketEnum.SEDAN_MARKET.getMarketName());
        }
        //suv
        for (MarketClassified marketClassified : this.suvNewSet)
        {
            marketClassified.setId(UUIDUtils.getUUID());
            marketClassified.setMarketName(MarketEnum.SUV_MARKET.getMarketName());
        }
        //mpv
        for (MarketClassified marketClassified : this.mpvNewSet)
        {
            marketClassified.setId(UUIDUtils.getUUID());
            marketClassified.setMarketName(MarketEnum.MPV_CAR_MARKET.getMarketName());
        }
        //豪华
        for (MarketClassified marketClassified : this.luxuryNewSet)
        {
            marketClassified.setId(UUIDUtils.getUUID());
            marketClassified.setMarketName(MarketEnum.LUXURY_MARKET.getMarketName());
        }
        //豪华轿车
        for (MarketClassified marketClassified : this.luxurySedanNewSet)
        {
            marketClassified.setId(UUIDUtils.getUUID());
            marketClassified.setMarketName(MarketEnum.LUXURY_SEDAN_MARKET.getMarketName());
        }
        //豪华suv
        for (MarketClassified marketClassified : this.luxurySuvNewSet)
        {
            marketClassified.setId(UUIDUtils.getUUID());
            marketClassified.setMarketName(MarketEnum.LUXURY_SUV_MARKET.getMarketName());
        }
        //豪华mpv
        for (MarketClassified marketClassified : this.luxuryMpvNewSet)
        {
            marketClassified.setId(UUIDUtils.getUUID());
            marketClassified.setMarketName(MarketEnum.LUXURY_MPV_MARKET.getMarketName());
        }

    }

}
