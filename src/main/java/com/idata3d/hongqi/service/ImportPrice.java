package com.idata3d.hongqi.service;

import com.idata3d.hongqi.mapper.PriceInfoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author sunjian.
 */
@Service
public class ImportPrice
{
    @Resource
    private PriceInfoMapper priceInfoMapper;

    //增加全国数据
    public int addChinaData()
    {

        return 0;
    }
}
