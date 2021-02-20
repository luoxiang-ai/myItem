package com.java.teaching.mapper;

import com.java.teaching.common.vo.ResourceVo;
import com.java.teaching.entity.Resource;
import com.java.teaching.entity.ResourceExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResourceMapper {
    int countByExample(ResourceExample example);

    int deleteByExample(ResourceExample example);

    int deleteByPrimaryKey(Integer rId);

    int insert(Resource record);

    int insertSelective(Resource record);

    List<Resource> selectByExample(ResourceExample example);

    Resource selectByPrimaryKey(Integer rId);

    int updateByExampleSelective(@Param("record") Resource record, @Param("example") ResourceExample example);

    int updateByExample(@Param("record") Resource record, @Param("example") ResourceExample example);

    int updateByPrimaryKeySelective(Resource record);

    int updateByPrimaryKey(Resource record);

    /**
     * 查询全部的资料
     * @return
     */
    List<ResourceVo> queryAllResource(ResourceVo resourceVo);

    /**
     * 根据课程id查询该课程的资料
     * @param cId
     * @return
     */
    List<Resource> queryResourceByCourseId(String cId);

    /**
     * 模糊查询资料
     * @param resourceVo
     * @return
     */
    List<ResourceVo> fuzzyQueryResource(ResourceVo resourceVo);

    /**
     * 加载推荐资源
     * @param resourceVo
     * @return
     */
    List<ResourceVo> loadRecommendResource(ResourceVo resourceVo);

    /**
     * 加载推荐书
     * @param resourceVo
     * @return
     */
    List<ResourceVo> loadRecommendBook(ResourceVo resourceVo);

    /**
     * 根据分类id查询资源
     * @param resourceVo
     * @return
     */
    List<ResourceVo> loadResourceBySortId(ResourceVo resourceVo);
}