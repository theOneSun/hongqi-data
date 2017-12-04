package com.idata3d.hongqi.mapper;

import com.idata3d.hongqi.domain.CodeNameRelation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author sunjian.
 */
@Mapper
public interface CodeNameRelationMapper
{
    List<CodeNameRelation> getAll();
}
