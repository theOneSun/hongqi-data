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
public class MarketSelect
{
    private String group;
    private String brand;
    private String series;
    private String carType;
    private String carSegment;
    private String carSubSegment;
    private String luxury;
    private String energy;
    private String domesticImport;
}
