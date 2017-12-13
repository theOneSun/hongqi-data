package com.idata3d.hongqi.mapper;

import com.idata3d.hongqi.domain.PriceInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author sunjian.
 */
@Mapper
public interface PriceInfoMapper
{
    /**
     * 查询全部
     * @return
     */
    List<PriceInfo> getAll();

    /**
     * 批量插入(插入前需要做校验)
     * @param insertList
     * @return
     */
    int batchInsert(@Param("insertList") List<PriceInfo> insertList);

    /**
     * 批量更新(更新前需要做校验不能为空或0元素)
     * @param updateList
     * @return
     */
    int batchUpdate(@Param("updateList") List<PriceInfo> updateList);

    /**
     * 查询去重后的code
     * @return
     */
    List<PriceInfo> getCodeGroup();

    /**
     * 查询缺失车型名称的priceInfo
     */
    List<PriceInfo> getIdCodeLackNameLimit(@Param("start") int start,@Param("end") int end);

    /**
     * 查询没有车型name的总数
     */
    int getLackNameTotal();

    /**
     * 根据id集合设置车型名称
     */
    int setNameByIdList(@Param("idList") List<String> idList,@Param("carSeriesName") String carSeriesName);


}
