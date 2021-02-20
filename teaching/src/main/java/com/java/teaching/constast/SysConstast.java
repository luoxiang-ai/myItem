package com.java.teaching.constast;

/**
 * 全局常量
 */
public interface SysConstast {

    /**
     * 帐号密码错误提示信息
     */
    String LOGIN_ERROR_MSG = "用户或密码错误，请重新输入";

    /**
     * 可用状态
     */
    Integer AVAILABLE_TRUE = 1;
    /**
     * 不可用状态
     */
    Integer AVAILABLE_FALSE = 0;

    /**
     * 用户类型
     */
    Integer USER_TYPE_SUPER = 1;
    Integer USER_TYPE_NORMAL= 2;

    /**
     * 是否展开
     */
    Integer SPREAD_TRUE = 1;
    Integer SPREAD_FALSE = 0;


    /**
     * 操作状态
     */
    String ADD_SUCCESS="添加成功";
    String ADD_ERROR="添加失败";

    String UPDATE_SUCCESS="更新成功";
    String UPDATE_ERROR="更新失败";

    String DELETE_SUCCESS="删除成功";
    String DELETE_ERROR="删除失败";

    String RESET_SUCCESS="重置成功";
    String RESET_ERROR="重置失败";

    String DISPATCH_SUCCESS="分配成功";
    String DISPATCH_ERROR="分配失败";

    Integer CODE_SUCCESS=0; //操作成功
    Integer CODE_ERROR=-1;//失败

    Integer CODE_ZERO = 0;
    Integer CODE_ONE = 1;

    /**
     * 默认用户头像图片名称
     */
    String DEFAULT_USER_FACE_IMG = "defaultUserFaceImg.jpg";

    /**
     * 用户头像存放目录
     */
    String USER_FACE_DIRECTORY = "userFaceImg";

    /**
     * 默认资源存放目录
     */
    String DEFAULT_DIRECTORY = "default";
    /**
     * excel存放目录
     */
    String EXCEL_DATA_DIRECTORY = "excelData";

    /**
     * 临时图片后缀
     */
    String DEFAULT_UPLOAD_TEMP = "_temp";

    String LEAVEBILL_STATE_ZERO = "0";
    String LEAVEBILL_STATE_ONE = "1";
    String LEAVEBILL_STATE_TWO = "2";
    String LEAVEBILL_STATE_THREE = "3";

    String START_SUCCESS = "启动成功";
    String START_ERROR = "启动失败";

    String TEACHER_ID_PREFIX = "T";
    String CLASS_ID_PREFIX = "C";
    String STUDENT_ID_PREFIX = "S";
    String COURSE_PREFIX = "K";


    Integer STUDENT_FLAG = 3;
    Integer TEACHER_FLAG = 2;
    Integer SUPER_USER_FLAG = 1;
//    默认密码
    String DEFAULT_PWD = "123456";

//    文件上传提示信息
    String UPLOAD_SUCCESS = "上传成功";
    String UPLOAD_ERROR = "上传失败";
    String DELETE_CLASS_ERROR = "该班级下有学生，删除失败";
    String TIME_OUT = "已过截止时间，请联系授课老师";
    String REVIEW_SUCCESS = "批阅成功";
    String REVIEW_ERROR = "批阅失败";
    String SUBMIT_SUCCESS = "提交成功";
    String SUBMIT_ERROR = "提交失败";
    Object CODE_MSG = "验证码错误";
}
