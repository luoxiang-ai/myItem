package com.java.teaching.mapper;

import com.java.teaching.common.vo.ResearchVo;
import com.java.teaching.common.vo.StudentWorkVo;
import com.java.teaching.entity.ResearchResource;
import com.java.teaching.entity.ResearchResourceExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResearchResourceMapper {
    int countByExample(ResearchResourceExample example);

    int deleteByExample(ResearchResourceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ResearchResource record);

    int insertSelective(ResearchResource record);

    List<ResearchResource> selectByExample(ResearchResourceExample example);

    ResearchResource selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ResearchResource record, @Param("example") ResearchResourceExample example);

    int updateByExample(@Param("record") ResearchResource record, @Param("example") ResearchResourceExample example);

    int updateByPrimaryKeySelective(ResearchResource record);

    int updateByPrimaryKey(ResearchResource record);

    /**
     * 根据教师id加载科研资料
     * @param researchVo
     * @return
     */
    List<StudentWorkVo> loadResearchByTid(ResearchVo researchVo);

    /**
     * 模糊查询科研资料信息
     * @param researchVo
     * @return
     */
    List<ResearchVo> fuzzyQueryResearch(ResearchVo researchVo);
}