package com.idata3d.hongqi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author sunjian.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalesMarket
{
    /*
    `group` varchar(255) DEFAULT NULL,
  `brand` varchar(255) DEFAULT NULL,
  `series` varchar(255) DEFAULT NULL COMMENT '系别',
  `luxury` varchar(255) DEFAULT NULL,
  `energy` varchar(255) DEFAULT NULL,
  `car_type` varchar(255) DEFAULT NULL,
  `car_segment` varchar(255) DEFAULT NULL,
  `car_subsegment` varchar(255) DEFAULT NULL,
  `market` varchar(255) DEFAULT NULL,
  `car_series_code` varchar(255) DEFAULT NULL,
  `domestic_import` varchar(255) DEFAULT NULL COMMENT '国产进口'
     */
    private String group;
    private String brand;
    private String series;
    private String luxury;
    private String energy;
    private String carType;
    private String carSegment;
    private String carSubSegment;
//    private String market;
    private String carSeriesCode;
    private String domesticImport;
}
