package com.java.teaching.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.java.teaching.common.vo.TeacherCourseVo;
import com.java.teaching.common.vo.UserVo;
import com.java.teaching.constast.SysConstast;
import com.java.teaching.entity.TeacherCourseExample;
import com.java.teaching.entity.TeacherCourseKey;
import com.java.teaching.mapper.SysUserMapper;
import com.java.teaching.mapper.TeacherCourseMapper;
import com.java.teaching.service.TeacherCourseService;
import com.java.teaching.utils.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 教师课程服务实现类
 */
@Service
public class TeacherCourseServiceImpl implements TeacherCourseService {

    @Autowired
    private TeacherCourseMapper teacherCourseMapper;
    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 添加老师课程关系
     *
     * @param teacherCourseVo
     */
    @Override
    public void addTeacherCourse(TeacherCourseVo teacherCourseVo) {
        Integer[] ids = teacherCourseVo.getIds();
        if(ids != null && ids.length > 0) {
            for (Integer id : ids) {
                TeacherCourseVo vo = new TeacherCourseVo();
                vo.setCid(teacherCourseVo.getCid());
                vo.setTid(id);
                this.add(vo);
            }
        }

    }

    public void add(TeacherCourseVo vo) {
        int i = this.teacherCourseMapper.insertSelective(vo);
        System.out.println(i);
    }

    /**
     * 回显课程授课老师
     *
     * @param teacherCourseVo
     * @return
     */
    @Override
    public DataGridView queryCourseTeacherList(TeacherCourseVo teacherCourseVo) {
        Page<Object> page = PageHelper.startPage(teacherCourseVo.getPage(), teacherCourseVo.getLimit());
//        1.查询全部老师
        UserVo userVo = new UserVo();
        userVo.setType(SysConstast.TEACHER_FLAG);
        userVo.setAvailable(SysConstast.AVAILABLE_TRUE);
        List<UserVo> studentList = this.sysUserMapper.queryAllStudent(userVo);
//        2.查询该课程授课的老师
        TeacherCourseExample example = new TeacherCourseExample();
        TeacherCourseExample.Criteria criteria = example.createCriteria();
        criteria.andCidEqualTo(teacherCourseVo.getCid());
        List<TeacherCourseKey> teacherList = this.teacherCourseMapper.selectByExample(example);

        Boolean LAY_CHECKED;

        List<Map<String, Object>> list = new ArrayList<>();
        for (UserVo s1 : studentList) {
            LAY_CHECKED = false;
            for (TeacherCourseKey s2 : teacherList) {
                if(s2.getTid() == s1.getUserid()) {
                    LAY_CHECKED = true;
                }
            }

            Map<String, Object> map = new HashMap<>();
            map.put("userid", s1.getUserid());
            map.put("loginname", s1.getLoginname());
            map.put("realname", s1.getRealname());
            map.put("LAY_CHECKED", LAY_CHECKED);
            list.add(map);
        }
        return new DataGridView(page.getTotal(), list);
    }

    /**
     * 更新课程授课老师
     *
     * @param teacherCourseVo
     */
    @Override
    public void updateCourseTeacher(TeacherCourseVo teacherCourseVo) {
//        1.根据课程id删除全部授课老师
        TeacherCourseExample teacherCourseExample = new TeacherCourseExample();
        TeacherCourseExample.Criteria criteria = teacherCourseExample.createCriteria();
        criteria.andCidEqualTo(teacherCourseVo.getCid());
        this.teacherCourseMapper.deleteByExample(teacherCourseExample);
//        2.再重新添加
        this.addTeacherCourse(teacherCourseVo);
    }
}
