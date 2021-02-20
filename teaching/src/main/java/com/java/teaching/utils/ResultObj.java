package com.java.teaching.utils;

import com.java.teaching.constast.SysConstast;

public class ResultObj {

    private Integer code = 0;
    private String msg;

    /**
     * 添加成功
     */
    public static final ResultObj ADD_SUCCESS = new ResultObj(SysConstast.CODE_SUCCESS, SysConstast.ADD_SUCCESS);
    /**
     * 添加失败
     */
    public static final ResultObj ADD_ERROR = new ResultObj(SysConstast.CODE_SUCCESS, SysConstast.ADD_ERROR);
    /**
     * 修改成功
     */
    public static final ResultObj UPDATE_SUCCESS = new ResultObj(SysConstast.CODE_SUCCESS, SysConstast.UPDATE_SUCCESS);
    /**
     * 修改失败
     */
    public static final ResultObj UPDATE_ERROR = new ResultObj(SysConstast.CODE_ERROR, SysConstast.UPDATE_ERROR);
    /**
     * 删除成功
     */
    public static final ResultObj DELETE_SUCCESS = new ResultObj(SysConstast.CODE_SUCCESS, SysConstast.DELETE_SUCCESS);
    /**
     * 删除失败
     */
    public static final ResultObj DELETE_ERROR = new ResultObj(SysConstast.CODE_SUCCESS, SysConstast.DELETE_ERROR);
    /**
     * 重置成功
     */
    public static final ResultObj RESET_SUCCESS = new ResultObj(SysConstast.CODE_SUCCESS, SysConstast.RESET_SUCCESS);
    /**
     * 重置失败
     */
    public static final ResultObj RESET_ERROR = new ResultObj(SysConstast.CODE_SUCCESS, SysConstast.RESET_ERROR);
    /**
     * 分配成功
     */
    public static final ResultObj DISPATCH_SUCCESS = new ResultObj(SysConstast.CODE_SUCCESS, SysConstast.DISPATCH_SUCCESS);
    /**
     * 分配失败
     */
    public static final ResultObj DISPATCH_ERROR = new ResultObj(SysConstast.CODE_SUCCESS, SysConstast.DISPATCH_ERROR);
    /**
     * 状态码 0
     */
    public static final ResultObj STATUS_TRUE = new ResultObj(SysConstast.CODE_SUCCESS);
    /**
     * 状态码 1
     */
    public static final ResultObj STATUS_FALSE = new ResultObj(SysConstast.CODE_ERROR);

    /**
     * 启动成功
     */
    public static final ResultObj START_SUCCESS = new ResultObj(SysConstast.CODE_SUCCESS, SysConstast.START_SUCCESS);
    /**
     * 启动失败
     */
    public static final ResultObj START_ERROR = new ResultObj(SysConstast.CODE_SUCCESS, SysConstast.START_ERROR);

    /**
     * 上传成功
     */
    public static final ResultObj UPLOAD_SUCCESS = new ResultObj(SysConstast.CODE_SUCCESS, SysConstast.UPLOAD_SUCCESS);
    /**
     * 上传失败
     */
    public static final ResultObj UPLOAD_ERROR = new ResultObj(SysConstast.CODE_SUCCESS, SysConstast.UPLOAD_ERROR);

    /**
     * 删除班级
     */
    public static final ResultObj DELETE_CLASS_ERROR = new ResultObj(SysConstast.CODE_SUCCESS, SysConstast.DELETE_CLASS_ERROR);

    /**
     * 作业提交超过截止时间
     */
    public static final ResultObj TIME_OUT = new ResultObj(SysConstast.CODE_ERROR, SysConstast.TIME_OUT);

    public static final ResultObj REVIEW_SUCCESS = new ResultObj(SysConstast.CODE_SUCCESS, SysConstast.REVIEW_SUCCESS);
    public static final ResultObj REVIEW_ERROR = new ResultObj(SysConstast.CODE_ERROR, SysConstast.REVIEW_ERROR);

    /**
     * 提交成功
     */
    public static final ResultObj SUBMIT_SUCCESS = new ResultObj(SysConstast.CODE_SUCCESS, SysConstast.SUBMIT_SUCCESS);
    /**
     * 提交失败
     */
    public static final ResultObj SUBMIT_ERROR = new ResultObj(SysConstast.CODE_ERROR, SysConstast.SUBMIT_ERROR);



    private ResultObj(Integer code) {
        this.code = code;
    }

    public ResultObj(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
