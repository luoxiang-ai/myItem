package com.java.teaching.mapper;

import com.java.teaching.common.vo.UserVo;
import com.java.teaching.entity.SysUser;
import com.java.teaching.entity.SysUserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserMapper {
    int countByExample(SysUserExample example);

    int deleteByExample(SysUserExample example);

    int deleteByPrimaryKey(Integer userid);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    List<SysUser> selectByExample(SysUserExample example);

    SysUser selectByPrimaryKey(Integer userid);

    int updateByExampleSelective(@Param("record") SysUser record, @Param("example") SysUserExample example);

    int updateByExample(@Param("record") SysUser record, @Param("example") SysUserExample example);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    /**
     * 查询全部学生
     * @param userVo
     * @return
     */
    List<UserVo> queryAllStudent(UserVo userVo);

    /**
     * 模糊查询学生
     * @param userVo
     * @return
     */
    List<UserVo> fuzzyQueryStudent(UserVo userVo);

    /**
     * 根据课程id查询课程报名学生信息
     * @param userVo
     * @return
     */
    List<UserVo> loadStudentNumByCourseId(UserVo userVo);
}