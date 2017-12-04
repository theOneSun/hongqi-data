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
public class MarketOptionDict
{
    private String id;
    private String category;
    private String firstOption;
    private String secondOption;
    private String thirdOption;
}
