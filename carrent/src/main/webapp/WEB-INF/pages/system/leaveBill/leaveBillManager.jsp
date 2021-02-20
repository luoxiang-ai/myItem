<%--
  Created by IntelliJ IDEA.
  leaveBill: luoxiang
  Date: 2020/11/12
  Time: 11:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>请假单管理</title>
        <meta name="renderer" content="webkit">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta http-equiv="Access-Control-Allow-Origin" content="*">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <meta name="apple-mobile-web-app-status-bar-style" content="black">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="format-detection" content="telephone=no">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layui/css/layui.css" media="all" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/public.css" media="all" />

    </head>
    <body class="childrenBody">
        <!-- 搜索条件开始 -->
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
            <legend>查询条件</legend>
        </fieldset>
        <form class="layui-form" method="post" id="searchFrm">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">请假单名称:</label>
                    <div class="layui-input-inline">
                        <input type="text" name="title"  autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <button type="button" class="layui-btn layui-btn-normal  layui-icon layui-icon-search" id="doSearch">查询</button>
                    <button type="reset" class="layui-btn layui-btn-warm  layui-icon layui-icon-refresh">重置</button>
                </div>
            </div>
        </form>

        <!-- 搜索条件结束 -->


        <!-- 数据表格开始 -->
        <table class="layui-hide" id="leaveBillTable" lay-filter="leaveBillTable"></table>
        <div style="display: none;" id="leaveBillToolBar">
            <button type="button" class="layui-btn layui-btn-sm" lay-event="add">增加</button>
            <button type="button" class="layui-btn layui-btn-sm layui-btn-danger" lay-event="batchDelete">批量删除</button>
        </div>
        <!--操作-->
        <script type="text/html" id="leaveBillBar">
            {{#  if(d.state==0){ }}
            <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="startProcess">提交申请</a>
            <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
            <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
            {{#  } else if(d.state==1){ }}
            <a class="layui-btn layui-btn-xs layui-btn-normal" lay-event="viewSpProcess">审批进度查询</a>
            {{#  } else if(d.state==2){ }}
            <a class="layui-btn layui-btn-xs layui-btn-normal" lay-event="viewSpProcess">审批进度查询</a>
            {{#  } else if(d.state==3){ }}
            <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
            <a class="layui-btn layui-btn-xs layui-btn-normal" lay-event="viewSpProcess">审批进度查询</a>
            {{#  } }}
        </script>
        <!-- 数据表格结束 -->

        <!-- 添加和修改的弹出层开始 -->
        <div style="display: none;padding: 20px" id="saveOrUpdateDiv" >
            <form class="layui-form"  lay-filter="dataFrm" id="dataFrm">
                <div class="layui-form-item">
                    <label class="layui-form-label">请假标题：</label>
                    <div class="layui-input-block">
                        <input type="hidden" name="userid" value="${sessionScope.sysUser.userid}">
                        <input type="text" name="title" lay-verify="required"  placeholder="请输入请假单标题" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-col-md5">
                        <label class="layui-form-label">请假天数：</label>
                        <div class="layui-input-block">
                            <input type="text" name="days" lay-verify="required|number" placeholder="请输入天数" class="layui-input">
                        </div>
                    </div>
<%--                    <div class="layui-col-md5 layui-col-md-offset2">--%>
<%--                        <label class="layui-form-label">请假时间</label>--%>
<%--                        <div class="layui-input-block">--%>
<%--                            <input type="text" class="layui-input" name="leavetime" placeholder="请输入请假时间">--%>
<%--                        </div>--%>
<%--                    </div>--%>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">请假原因：</label>
                    <div class="layui-input-block">
                        <textarea class="layui-textarea" lay-verify="required" name="content" placeholder="请输入请假内容"></textarea>
                    </div>
                </div>
                <div class="layui-form-item" style="text-align: center;">
                    <div class="layui-input-block">
                        <button type="button" class="layui-btn layui-btn-normal layui-btn-sm layui-icon layui-icon-release" lay-filter="doSubmit" lay-submit="">提交</button>
                        <button type="reset" class="layui-btn layui-btn-warm layui-btn-sm layui-icon layui-icon-refresh" >重置</button>
                    </div>
                </div>
            </form>

        </div>
        <!-- 添加和修改的弹出层结束 -->

        <!-- 请假单权限设置的弹出层开始 -->
        <div style="display: none;" id="selectleaveBillRole">
            <table class="layui-hide" id="roleTable" lay-filter="roleTable"></table>
        </div>
        <!-- 请假单权限设置的弹出层结束 -->

        <script src="${pageContext.request.contextPath}/resources/layui/layui.js"></script>
        <script type="text/javascript">
            var tableIns;
            layui.extend({
                dtree:'${pageContext.request.contextPath}/resources/layui_ext/dist/dtree'
            }).use([ 'jquery', 'layer', 'form', 'table'], function() {
                var $ = layui.jquery;
                var layer = layui.layer;
                var form = layui.form;
                var table = layui.table;
                //渲染数据表格
                tableIns=table.render({
                    elem: '#leaveBillTable'   //渲染的目标对象
                    ,url:'${pageContext.request.contextPath}/leaveBill/loadAllLeaveBill.action' //数据接口
                    ,title: '请假单数据表'//数据导出来的标题
                    ,toolbar:"#leaveBillToolBar"   //表格的工具条
                    ,height:'full-150'
                    ,cellMinWidth:100 //设置列的最小默认宽度
                    ,page: true  //是否启用分页
                    ,cols: [[   //列表数据
                        {type: 'checkbox', fixed: 'left'}
                        ,{field:'title', title:'请假单标题',align:'center', width: 120}
                        ,{field: 'content', title: '内容', align: 'center', width: 120}
                        ,{field:'days', title:'请假天数',align:'center', width: 180}
                        ,{field: 'leavetime', title: '请假时间', align: 'center', width: 130}
                        ,{field: 'state', title: '标识', align: 'center', width: 200}
                        ,{field: 'userid', title: '用户', align: 'center', width: 80,sort: true}
                        ,{fixed: 'right', title: '操作', toolbar: '#leaveBillBar', align: 'center', width: 260}
                    ]]
                    ,done: function(res, curr, count) {
                        // res：接口返回的信息
                        // curr：当前页码数
                        // count：数据总记录数

                        // 如果不是第一页，并且数据量为0的情况下，就返回上一页
                        if(curr != 1 && res.data.length == 0) {
                            tableIns.reload({
                                page: {
                                    curr: curr - 1
                                }
                            })
                        }
                    }
                })

                //模糊查询
                $("#doSearch").click(function(){
                    var params=$("#searchFrm").serialize();
                    alert(params);
                    tableIns.reload({
                        url:"${pageContext.request.contextPath}/leaveBill/loadAllleaveBill.action?"+params
                    })
                });

                //监听头部工具栏事件
                table.on("toolbar(leaveBillTable)",function(obj){
                    switch(obj.event){
                        case 'add':
                            openAddLeaveBill();
                            break;
                        case 'batchDelete':
                            batchDeleteleaveBills();
                            break;
                    };
                })
                //监听行工具事件
                table.on('tool(leaveBillTable)', function(obj){
                    var data = obj.data; //获得当前行数据
                    var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
                    if(layEvent === 'del'){ //删除
                        layer.confirm('您确定删除【' + data.loginname + '】这个请假单吗', function (index) {
                            console.log(index);
                            // 向服务端发送删除请求
                            $.post('${pageContext.request.contextPath}/leaveBill/deleteleaveBill.action', {leaveBillId: data.leaveBillid}, function (res) {
                                layer.msg(res.msg);
                                // 重新刷新数据表格
                                tableIns.reload();
                            })
                        });
                    } else if(layEvent === 'edit'){ //编辑
                        openUpdateleaveBill(data);
                    } else if (layEvent == 'startProcess') {
                        startProcess(data);
                    }
                });

                var url;
                var mainIndex;
//打开添加页面
                function openAddLeaveBill(){
                    mainIndex=layer.open({
                        type:1,
                        title:'添加请假单',
                        content:$("#saveOrUpdateDiv"),
                        area:['800px','450px'],
                        success:function(index){
                            //清空表单数据
                            $("#dataFrm")[0].reset();
                            url="${pageContext.request.contextPath}/leaveBill/addLeaveBill.action";
                        }
                    });
                }

                // 请假单修改函数
                function openUpdateleaveBill(data){
                    console.log(data);
                    mainIndex = layer.open({
                        type:1,
                        title:'修改请假单',
                        content:$("#saveOrUpdateDiv"),
                        area:['800px','450px'],
                        success:function(index){
                            form.val("dataFrm",data);
                            url="${pageContext.request.contextPath}/leaveBill/updateLeaveBill.action";
                        }
                    });
                }
//保存
                form.on("submit(doSubmit)",function(obj){
//序列化表单数据
                    var params=$("#dataFrm").serialize();
                    $.post(url,params,function(obj){
                        layer.msg(obj.msg);
//关闭弹出层
                        layer.close(mainIndex)
//刷新数据 表格
                        tableIns.reload();
                    })
                });

                $("#pid_div").on("click",function(){
                    $(this).toggleClass("layui-form-selected");
                    $("#leaveBillSelectDiv").toggleClass("layui-show layui-anim layui-anim-upbit");
                });

                // 批量删除请假单
                function batchDeleteleaveBills() {
                    var checkStatus = table.checkStatus('leaveBillTable');
                    var data = checkStatus.data;
                    var params = '';
                    $.each(data, function (i, item) {
                        if (i == 0) {
                            params += 'ids=' + item.leaveBillid;
                        } else {
                            params += '&ids=' + item.leaveBillid;
                        }
                        layer.alert(params);
                    });

                    layer.confirm('您确定要批量删除这些请假单吗', function (index) {
                        //    向服务端发送删除请求
                        $.post('${pageContext.request.contextPath}/leaveBill/batchDeleteLeaveBill.action', params, function (res) {
                            layer.msg(res.msg);
                            //    重新刷新数据表格
                            tableIns.reload();
                        })
                    })
                }

                // 提交请申请
                function startProcess(data) {
                    console.log(data);
                    layer.confirm('确定将【' + data.title + '】这个请假单提交', {icon: 3, title:'提示'}, function(index){
                        $.post('${pageContext.request.contextPath}/workFlow/startProcess.action', {id: data.id}, function (res) {
                            layer.msg(res.msg);
                        })
                        layer.close(index);
                    })
                }

            });

            function reloadTable(id){
                tableIns.reload({
                    url:"${pageContext.request.contextPath}/leaveBill/loadAllLeaveBill.action?id="+id
                })
            }


        </script>
    </body>
</html>
