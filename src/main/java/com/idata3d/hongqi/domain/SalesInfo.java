package com.idata3d.hongqi.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.YearMonth;

/**
 * @author sunjian.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesInfo
{
    private String id;
    /**
     * 车系code
     */
    private String carSeriesCode;

    /**
     * 车系名称
     */
    private String carSeriesName;

    /**
     * 模糊车型code
     */
    private String carBaseTypeCode;
    /**
     * 模糊车型名称
     */
    private String carBaseTypeName;
    /**
     * 车型
     */
    private String carType;
    /**
     * 年月
     */
    @JsonFormat(pattern = "yyyy-MM")
    @DateTimeFormat(pattern = "yyyy-MM")
    private YearMonth yearMonth;

    /**
     * 大区名
     */
    private String region;


    /**
     * 一级地区
     */
    private String primaryArea;

    /**
     * 二级地区
     */
    private String secondaryArea;

    /**
     * 销量
     */
    private int sales;
}
