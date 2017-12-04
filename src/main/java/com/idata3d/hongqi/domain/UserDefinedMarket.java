package com.idata3d.hongqi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 测试减少重复代码用
 * @author sunjian.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDefinedMarket
{
    private String id;
    private String userId;
    private LocalDateTime createTime;
    MarketClassified marketClassified = new MarketClassified();
}
