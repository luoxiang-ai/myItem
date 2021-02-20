package com.java.teaching.mapper;

import com.java.teaching.common.vo.NewsVo;
import com.java.teaching.entity.SysNews;
import com.java.teaching.entity.SysNewsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysNewsMapper {
    int countByExample(SysNewsExample example);

    int deleteByExample(SysNewsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysNews record);

    int insertSelective(SysNews record);

    List<SysNews> selectByExample(SysNewsExample example);

    SysNews selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SysNews record, @Param("example") SysNewsExample example);

    int updateByExample(@Param("record") SysNews record, @Param("example") SysNewsExample example);

    int updateByPrimaryKeySelective(SysNews record);

    int updateByPrimaryKey(SysNews record);

    /**
     * 模糊查询公告
     * @param newsVo
     * @return
     */
    List<SysNews> fuzzyQueryNews(NewsVo newsVo);
}