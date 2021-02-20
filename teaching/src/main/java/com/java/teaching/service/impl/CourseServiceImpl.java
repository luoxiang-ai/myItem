package com.java.teaching.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.java.teaching.common.vo.CourseVo;
import com.java.teaching.common.vo.UserVo;
import com.java.teaching.constast.SysConstast;
import com.java.teaching.entity.Course;
import com.java.teaching.entity.CourseExample;
import com.java.teaching.entity.SysUser;
import com.java.teaching.mapper.CourseMapper;
import com.java.teaching.service.CourseService;
import com.java.teaching.utils.DataGridView;
import com.java.teaching.utils.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 课程服务实现类
 */
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    /**
     * 加载全部课程
     *
     * @param courseVo
     * @return
     */
    @Override
    public DataGridView loadAllCourse(CourseVo courseVo) {
        Page<Object> page = PageHelper.startPage(courseVo.getPage(), courseVo.getLimit());
        List<Course> courseList = this.courseMapper.selectByExample(new CourseExample());
        return new DataGridView(page.getTotal(), courseList);
    }

    /**
     * 添加课程
     *
     * @param courseVo
     */
    @Override
    public Integer addCourse(CourseVo courseVo) {
        if(courseVo != null) {
            return this.courseMapper.insertSelective(courseVo);
        }

        return null;
    }

    /**
     * 删除课程
     *
     * @param id
     */
    @Override
    public void deleteCourse(Integer id) {
        if(id != null && id > 0) {
            this.courseMapper.deleteByPrimaryKey(id);
        }
    }

    /**
     * 批量删除课程
     *
     * @param courseVo
     */
    @Override
    public void batchDeleteCourse(CourseVo courseVo) {
        if(courseVo != null) {
            Integer[] ids = courseVo.getIds();
            for (Integer id : ids) {
                this.deleteCourse(id);
            }
        }
    }

    /**
     * 修改课程信息
     *
     * @param courseVo
     */
    @Override
    public void updateCourse(CourseVo courseVo) {
        if(courseVo != null) {
            this.courseMapper.updateByPrimaryKeySelective(courseVo);
        }
    }

    /**
     * 模糊查询课程
     *
     * @param courseVo
     * @return
     */
    @Override
    public DataGridView fuzzyQueryCourse(CourseVo courseVo) {
        Page<Object> page = PageHelper.startPage(courseVo.getPage(), courseVo.getLimit());
        List<Course> list = this.courseMapper.fuzzyQueryCourse(courseVo);
        return new DataGridView(page.getTotal(), list);
    }

    /**
     * 根据分类id查询课程
     *
     * @param courseVo
     * @return
     */
    @Override
    public DataGridView loadCourseBySortId(CourseVo courseVo) {
        Page<Object> page = PageHelper.startPage(courseVo.getPage(), courseVo.getLimit());
        List<Course> list = this.courseMapper.loadCourseBySortId(courseVo);
        return new DataGridView(page.getTotal(), list);
    }

    /**
     * 根据用户id加载课程
     *
     * @param userVo
     * @return
     */
    @Override
    public DataGridView loadCourseByUserId(UserVo userVo) {
        Page<Object> page = PageHelper.startPage(userVo.getPage(), userVo.getLimit());
        List<Course> list = this.courseMapper.loadCourseByUserId(userVo);
        return new DataGridView(page.getTotal(), list);
    }

    /**
     * 根据老师id查询该老师的教授课程
     *
     * @param userVo
     * @return
     */
    @Override
    public DataGridView loadCourseByTeacherId(UserVo userVo) {
        Page<Object> page = PageHelper.startPage(userVo.getPage(), userVo.getLimit());
        List<SysUser> list = this.courseMapper.loadCourseByTeacherId(userVo);
        return new DataGridView(page.getTotal(), list);
    }

    /**
     * 模糊查询全部课程
     *
     * @param courseVo
     * @return
     */
    @Override
    public DataGridView fuzzyQueryAllCourse(CourseVo courseVo) {
        Page<Object> page = PageHelper.startPage(courseVo.getPage(), courseVo.getLimit());
        List<Course> list = this.courseMapper.fuzzyQueryAllCourse(courseVo);
        return new DataGridView(page.getTotal(), list);
    }
}
