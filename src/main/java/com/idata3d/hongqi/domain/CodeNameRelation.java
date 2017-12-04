package com.idata3d.hongqi.domain;

import lombok.Data;

/**
 * 对应车系code和车系name的对应关系
 * @author sunjian.
 */
@Data
public class CodeNameRelation
{
    private String id;
    private String carSeriesCode;
    private String carSeriesName;
}
