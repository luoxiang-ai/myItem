package com.java.carrent.constast;

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

    String ADD_TO_BLACKLIST_SUCCESS = "移入黑名单成功";
    String ADD_TO_BLACKLIST_ERROR = "移入黑名单失败";

    Integer CODE_SUCCESS=0; //操作成功
    Integer CODE_ERROR=-1;//失败

    Integer CODE_ZERO = 0;
    Integer CODE_ONE = 1;

    /**
     * 默认汽车图片名字
     */
    String DEFAULT_CAR_IMG = "defaultCarImg.jpg";
    /**
     * 默认用户头像图片名称
     */
    String DEFAULT_USER_FACE_IMG = "defaultUserFaceImg.jpg";

    /**
     * 汽车图片存放目录
     */
    String CAR_DIRECTORY = "carImg";
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

    /**
     * 出租单号的前缀
     */
    String CAR_ORDER_RENT = "RENT";
    /**
     * 检查单号的前缀
     */
    String CAR_ORDER_CHECK = "CHECK";

    /**
     * 汽车归还状态
     */
    Integer RENT_BACK_FALSE = 0;
    Integer RENT_BACK_TRUE = 1;

    /**
     * 汽车出租状态
     */
    Integer RENT_CAR_FALSE = 0;
    Integer RENT_CAR_TRUE = 1;


    String DELETE_BLACKLIST_SUCCESS = "移出黑名单成功";
    String DELETE_BLACKLIST_ERROR = "移出黑名单失败";

    String DEPLOY_SUCCESS = "部署成功";
    String DEPLOY_ERROR = "部署失败";

    String LEAVEBILL_STATE_ZERO = "0";
    String LEAVEBILL_STATE_ONE = "1";
    String LEAVEBILL_STATE_TWO = "2";
    String LEAVEBILL_STATE_THREE = "3";

    String START_SUCCESS = "启动成功";
    String START_ERROR = "启动失败";
    Object CODE_MSG = "验证码错误";
}
