package com.idata3d.hongqi.domain;

import lombok.Data;

import java.time.LocalDate;
import java.time.YearMonth;

/**
 * @author sunjian.
 */
@Data
public class PriceInfo
{
    private String id;
    private String carSeriesCode;
    private String carSeriesName;
    private String carBaseTypeCode;//模糊车型code
    private String carType;//车型
    private String area;
    private YearMonth yearMonth;
    private LocalDate listedDate;//上市日期
    private Integer guidancePrice;
    private Integer finalPrice;
    private Double mix;//百分比整形(%前面的内容)
    private String halfMonth;
    private String allCountryFinalPrice;
}
