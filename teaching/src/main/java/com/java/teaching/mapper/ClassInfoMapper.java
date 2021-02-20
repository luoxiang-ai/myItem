package com.java.teaching.mapper;

import com.java.teaching.common.vo.ClassVo;
import com.java.teaching.entity.ClassInfo;
import com.java.teaching.entity.ClassInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClassInfoMapper {
    int countByExample(ClassInfoExample example);

    int deleteByExample(ClassInfoExample example);

    int deleteByPrimaryKey(String classId);

    int insert(ClassInfo record);

    int insertSelective(ClassInfo record);

    List<ClassInfo> selectByExample(ClassInfoExample example);

    ClassInfo selectByPrimaryKey(String classId);

    int updateByExampleSelective(@Param("record") ClassInfo record, @Param("example") ClassInfoExample example);

    int updateByExample(@Param("record") ClassInfo record, @Param("example") ClassInfoExample example);

    int updateByPrimaryKeySelective(ClassInfo record);

    int updateByPrimaryKey(ClassInfo record);

    /**
     * 更新标识（用于逻辑删除）
     * @param classId
     */
    void updateClassInfoFlag(String classId);

    /**
     * 模糊查询班级信息
     * @param classVo
     * @return
     */
    List<ClassInfo> fuzzyQueryClass(ClassVo classVo);
}