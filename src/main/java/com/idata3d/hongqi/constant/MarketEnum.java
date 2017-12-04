package com.idata3d.hongqi.constant;


/**
 * @author sunjian.
 */
public enum MarketEnum
{
    PASSENGER_MARKET("乘用车市场"),
    SEDAN_MARKET("轿车市场"),
    SUV_MARKET("SUV市场"),
    MPV_CAR_MARKET("MPV市场"),
    LUXURY_MARKET("豪华车市场"),
    LUXURY_SEDAN_MARKET("豪华轿车市场"),
    LUXURY_SUV_MARKET("豪华SUV市场"),
    LUXURY_MPV_MARKET("豪华MPV市场"),

    //car_type
    /*SEDAN("Sedan"),
    SUV("SUV"),
    MPV("SUV"),
    LIGHT_BUS("轻客"),
    MINI_BUS("微客"),*/

    //car_segment
   /* A("A"),
    A0("A0"),
    A00("A00"),
    B("B"),
    C("C"),
    D("D"),*/
    ;


    private String marketName;

    MarketEnum(String marketName)
    {
        this.marketName = marketName;
    }

    public String getMarketName()
    {
        return marketName;
    }
}
