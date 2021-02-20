package com.java.teaching.web.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面跳转控制器--作用：路由
 */
@Controller
@RequestMapping("/sys")
public class SysController {

    /**
     * 跳转到首页面
     * @return
     */
    @GetMapping(value = "/index.action")
    public String index() {

        return "system/main/index";
    }

    /**
     * 跳转到菜单管理
     * @return
     */
    @GetMapping(value = "/toMenuManager.action")
    public String toMenuManager() {

        return "system/menu/menuManager";
    }

    /**
     * 跳转菜单管理左边的的菜单树页面
     * @return
     */
    @GetMapping(value = "/toMenuLeft.action")
    public String toMenuLeft() {

        return "system/menu/menuLeft";
    }

    /**
     * 跳转菜单管理右边的菜单列表
     * @return
     */
    @GetMapping(value = "/toMenuRight.action")
    public String toMenuRight() {

        return "system/menu/menuRight";
    }

    /**
     * 跳转到角色管理页面
     * @return
     */
    @GetMapping(value = "/toRoleManager.action")
    @RequiresPermissions(value = "sys:role")
    public String toRoleManager() {

        return "system/role/roleManager";
    }


    /**
     * 跳转到公告管理页面
     * @return
     */
    @GetMapping(value = "/toNewsManager.action")
    @RequiresPermissions(value = "sys:news")
    public String toNewsManager() {

        return "system/news/newsManager";
    }

    /**
     * 跳转到个人资料管理页面
     * @return
     */
    @GetMapping(value = "/toPersonalManager.action")
    public String toPersonalManager() {

        return "system/user/personalManager";
    }

    /**
     * 跳转到工作台
     * @return
     */
    @GetMapping(value = "/toDeskManager.action")
    public String toDeskManager() {

        return "system/main/deskManager";
    }

    /**
     * 跳转到学生管理页面
     * @return
     */
    @GetMapping(value = "/toStudentManager.action")
    @RequiresPermissions(value = "sys:student")
    public String toStudentManager() {

        return "system/student/studentManager";
    }

    /**
     * 跳转到老师管理页面
     * @return
     */
    @GetMapping(value = "/toTeacherManager.action")
    @RequiresPermissions(value = "sys:teacher")
    public String toTeacherManager() {

        return "system/teacher/teacherManager";
    }

    /**
     * 跳转到班级管理页面
     * @return
     */
    @GetMapping(value = "/toClassManager.action")
    @RequiresPermissions(value = "sys:class")
    public String toClassManager() {

        return "system/class/classManager";
    }

    /**
     * 跳转到课程管理页面
     * @return
     */
    @GetMapping(value = "/toCourseManager.action")
    @RequiresPermissions(value = "sys:course")
    public String toCourseManager() {

        return "system/course/courseManager";
    }

    /**
     * 跳转到资料管理页面
     * @return
     */
    @GetMapping(value = "/toResourceManager.action")
    @RequiresPermissions(value = "sys:download")
    public String toResourceManager() {

        return "system/resource/resourceManager";
    }

    /**
     * 跳转到密码修改页面
     * @return
     */
    @GetMapping(value = "/toChangePwdManager.action")
    public String toChangePwdManager() {

        return "system/user/changePwdManager";
    }

    /**
     * 跳转到课程分类管理页面
     * @return
     */
    @GetMapping(value = "/toCourseSortManager.action")
    @RequiresPermissions(value = "sys:courseSort")
    public String toCourseSortManager() {

        return "system/courseSort/courseSortManager";
    }

    /**
     * 跳转到我报名的课程管理页面
     * @return
     */
    @GetMapping(value = "/toMyCourseManager.action")
    @RequiresPermissions(value = "sys:myCourse")
    public String toMyCourseManager() {

        return "system/course/myCourseManager";
    }

    /**
     * 跳转到教师课程管理页面
     * @return
     */
    @GetMapping(value = "/toTeacherCourseManager.action")
    @RequiresPermissions(value = "sys:teacherCourse")
    public String toTeacherCourseManager() {

        return "system/course/teacherCourseManager";
    }

    /**
     * 跳转到我的成绩管理页面
     * @return
     */
    @GetMapping(value = "/toMyScoreManager.action")
    @RequiresPermissions(value = "sys:myScore")
    public String toMyScoreManager() {

        return "system/score/myScoreManager";
    }

    /**
     * 跳转到意见提交页面
     * @return
     */
    @GetMapping(value = "/toSuggestManager.action")
    @RequiresPermissions(value = "sys:suggest")
    public String toSuggestManager() {

        return "system/suggest/suggestManager";
    }

    /**
     * 跳转到匿名建议管理页面
     * @return
     */
    @GetMapping(value = "/toSuggestListManager.action")
    @RequiresPermissions(value = "sys:suggestList")
    public String toSuggestListManager() {

        return "system/suggest/suggestListManager";
    }

    /**
     * 跳转到资源分类管理页面
     * @return
     */
    @GetMapping(value = "/toResourceSortManager.action")
    @RequiresPermissions(value = "sys:resourceSort")
    public String toResourceSortManager() {

        return "system/resource/resourceSortManager";
    }

    /**
     * 跳转到推荐资源页面
     * @return
     */
    @GetMapping(value = "/toRecommendResource.action")
    @RequiresPermissions(value = "sys:recommendResource")
    public String toRecommendResource() {

        return "system/resource/recommendResource";
    }

    /**
     * 跳转到教学参考书推荐页面
     * @return
     */
    @GetMapping(value = "/toRecommendBook.action")
    @RequiresPermissions(value = "sys:recommendBook")
    public String toRecommendBook() {

        return "system/resource/recommendBook";
    }

    /**
     * 跳转到资源上传管理页面
     * @return
     */
    @GetMapping(value = "/toResourceUploadManager.action")
    @RequiresPermissions(value = "sys:resourceUpload")
    public String toResourceUploadManager() {

        return "system/resource/uploadResourceManager";
    }

    /**
     * 跳转到科研资源管理页面
     * @return
     */
    @GetMapping(value = "/toResearchManager.action")
    @RequiresPermissions(value = "sys:research")
    public String toResearchManager() {

        return "system/research/researchManager";
    }
}
