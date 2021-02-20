package com.java.teaching.mapper;

import com.java.teaching.common.vo.ResourceSortVo;
import com.java.teaching.entity.ResourceSort;
import com.java.teaching.entity.ResourceSortExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResourceSortMapper {
    int countByExample(ResourceSortExample example);

    int deleteByExample(ResourceSortExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ResourceSort record);

    int insertSelective(ResourceSort record);

    List<ResourceSort> selectByExample(ResourceSortExample example);

    ResourceSort selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ResourceSort record, @Param("example") ResourceSortExample example);

    int updateByExample(@Param("record") ResourceSort record, @Param("example") ResourceSortExample example);

    int updateByPrimaryKeySelective(ResourceSort record);

    int updateByPrimaryKey(ResourceSort record);

    /**
     * 模糊查询资源分类
     * @param resourceSortVo
     * @return
     */
    List<ResourceSortVo> fuzzyQueryResourceSort(ResourceSortVo resourceSortVo);
}