package com.java.carrent.mapper;

import com.java.carrent.common.vo.NewsVo;
import com.java.carrent.entity.SysNews;
import com.java.carrent.entity.SysNewsExample;
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