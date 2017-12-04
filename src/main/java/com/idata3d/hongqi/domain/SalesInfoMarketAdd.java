package com.idata3d.hongqi.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.YearMonth;

/**
 * 更新销量表和市场表前期excel-csv导入数据库的实体类(应该用不到)
 * @author sunjian.
 */
@Data
public class SalesInfoMarketAdd
{
    private String id;
    private String carSeriesCode;
    private String carSeriesName;
    private String carBaseTypeCode;
    private String carBaseTypeName;
    private String carSalesInfoType;

    @JsonFormat(pattern = "yyyy-MM")
    @DateTimeFormat(pattern = "yyyy-MM")
    private YearMonth yearMonth;
    private String region;
    private String primaryArea;
    private String secondaryArea;
    private int sales;
    private String group;
    private String brand;
    private String series;
    private String luxury;
    private String energy;
    private String carType;
    private String carSegment;
    private String carSubSegment;
    private String market;
    private String domesticImport;
}
