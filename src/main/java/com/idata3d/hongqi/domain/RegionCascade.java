package com.idata3d.hongqi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.YearMonth;

/**
 * @author sunjian.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegionCascade
{
    private String id;
    private String region;
    private String primaryArea;
    private String secondaryArea;
    private YearMonth yearMonth;
}
