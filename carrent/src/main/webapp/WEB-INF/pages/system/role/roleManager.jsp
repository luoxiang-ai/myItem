<%--
  Created by IntelliJ IDEA.
  User: luoxiang
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
    <title>角色管理</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Access-Control-Allow-Origin" content="*">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layui/css/layui.css" media="all" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/public.css" media="all" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layui_ext/dtree/dtree.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layui_ext/dtree/font/dtreefont.css">

</head>
<body class="childrenBody">
<!-- 搜索条件开始 -->
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>查询条件</legend>
</fieldset>
<form class="layui-form" method="post" id="searchFrm">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">角色名称:</label>
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
<table class="layui-hide" id="roleTable" lay-filter="roleTable"></table>
<div style="display: none;" id="roleToolBar">
    <button type="button" class="layui-btn layui-btn-sm" lay-event="add">增加</button>
    <button type="button" class="layui-btn layui-btn-sm layui-btn-danger" lay-event="batchDelete">批量删除</button>
</div>
<div id="roleBar" style="display: none;">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-xs layui-btn-normal" lay-event="selectRoleMenu">分配菜单</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</div>
<!-- 数据表格结束 -->

<!-- 添加和修改的弹出层开始 -->
<div style="display: none;padding: 20px" id="saveOrUpdateDiv" >
    <form class="layui-form"  lay-filter="dataFrm" id="dataFrm">
        <div class="layui-form-item">
            <label class="layui-form-label">角色名称：</label>
            <div class="layui-input-block">
                <input type="hidden" name="roleid" id="roleid">
                <input type="text" name="rolename"  placeholder="请输入角色名称" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">描述:</label>
            <div class="layui-input-block">
                <input type="hidden" name="id">
                <input type="text" name="roledesc"  placeholder="请输入角色描述" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">是否可用:</label>
                <div class="layui-input-inline">
                    <input type="radio" name="available" value="1" checked="checked" title="可用">
                    <input type="radio" name="available" value="0" title="不可">
                </div>
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

<!-- 角色权限设置的弹出层开始 -->
<div style="display: none;" id="selectRoleMenu">
    <ul id="menuTree" id="dtree" data-id="0"></ul>
</div>
<!-- 角色权限设置的弹出层结束 -->

<script src="${pageContext.request.contextPath}/resources/layui/layui.js"></script>
<script type="text/javascript">
    var tableIns;
    layui.extend({
        dtree:'${pageContext.request.contextPath}/resources/layui_ext/dtree/dtree'
    }).use([ 'jquery', 'layer', 'form', 'table','dtree'  ], function() {
        var $ = layui.jquery;
        var layer = layui.layer;
        var form = layui.form;
        var table = layui.table;
        var dtree = layui.dtree;
//渲染数据表格
        tableIns=table.render({
            elem: '#roleTable'   //渲染的目标对象
            ,url:'${pageContext.request.contextPath}/role/loadAllRole.action' //数据接口
            ,title: '角色数据表'//数据导出来的标题
            ,toolbar:"#roleToolBar"   //表格的工具条
            ,height:'full-150'
            ,cellMinWidth:100 //设置列的最小默认宽度
            ,page: true  //是否启用分页
            ,cols: [[   //列表数据
                {type: 'checkbox', fixed: 'left'}
                ,{field:'roleid', title:'ID',align:'center',sort: true}
                ,{field:'rolename', title:'角色名称',align:'center'}
                ,{field:'roledesc', title:'描述',align:'center'}
                ,{field:'available', title:'是否可用',align:'center',sort:true,templet:function(d){
                    return d.available=='1'?'<font color=green>可用</font>':'<font color=red>不可用</font>';
                }},
                {fixed: 'right', title: '操作', toolbar: '#roleBar', align: 'center'}
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
                url:"${pageContext.request.contextPath}/role/loadAllRole.action?"+params
            })
        });

        //监听头部工具栏事件
        table.on("toolbar(roleTable)",function(obj){
            switch(obj.event){
                case 'add':
                    openAddRole();
                    break;
                case 'batchDelete':
                    batchDeleteRoles();
                    break;
            };
        })
        //监听行工具事件
        table.on('tool(roleTable)', function(obj){
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            if(layEvent === 'del'){ //删除
                layer.confirm('您确定删除【' + data.rolename + '】这个角色吗', function (index) {
                    console.log(index);
                    // 向服务端发送删除请求
                    $.post('${pageContext.request.contextPath}/role/deleteRole.action', {roleid: data.roleid}, function (res) {
                        layer.msg(res.msg);
                        // 重新刷新数据表格
                        tableIns.reload();
                    })
                });
            } else if(layEvent === 'edit'){ //编辑
                openUpdateRole(data);
            } else if (layEvent == 'selectRoleMenu') {
                openSelectRoleMenu(data);
            }
        });

        var url;
        var mainIndex;
//打开添加页面
        function openAddRole(){
            mainIndex=layer.open({
                type:1,
                title:'添加角色',
                content:$("#saveOrUpdateDiv"),
                area:['800px','450px'],
                success:function(index){
//清空表单数据
                    $("#dataFrm")[0].reset();
                    url="${pageContext.request.contextPath}/role/addRole.action";
                }
            });
        }
//打开修改页面
        function openUpdateRole(data){
            mainIndex=layer.open({
                type:1,
                title:'修改角色',
                content:$("#saveOrUpdateDiv"),
                area:['800px','450px'],
                success:function(index){
                    form.val("dataFrm",data);
                    url="${pageContext.request.contextPath}/role/updateRole.action";
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
            $("#roleSelectDiv").toggleClass("layui-show layui-anim layui-anim-upbit");
        });
        dtree.on("node(roleTree)", function(obj){
            $("#pid_str").val(obj.param.context);
            $("#pid").val(obj.param.nodeId);
            $("#pid_div").toggleClass("layui-form-selected");
            $("#roleSelectDiv").toggleClass("layui-show layui-anim layui-anim-upbit");
        });

        // 批量删除角色
        function batchDeleteRoles() {
            var checkStatus = table.checkStatus('roleTable');
            var data = checkStatus.data;
            var params = '';
            $.each(data, function (i, item) {
                if (i == 0) {
                    params += 'ids=' + item.roleid;
                } else {
                    params += '&ids=' + item.roleid;
                }
                layer.alert(params);
            });

            layer.confirm('您确定要批量删除这些角色吗', function (index) {
            //    向服务端发送删除请求
                $.post('${pageContext.request.contextPath}/role/deleteBatchRole.action', params, function (res) {
                    layer.msg(res.msg);
                //    重新刷新数据表格
                    tableIns.reload();
                })
            })
        }

        // 打开分配菜单的弹出层
        function openSelectRoleMenu(data) {
            var menuTree;
            mainIndex = layer.open({
                type: 1,
                title: '正在分配【' + data.rolename + '】的角色',
                content: $('#selectRoleMenu'),
                offset: 'auto',
                anim: 5,       // 动画效果： 渐显
                area: ['400px', '500px'],
                btn: ['确定分配', '取消分配'],
                btnAlign: 'c',      // 按钮的对齐方式
                yes: function(index) {
                    var nodes = dtree.getCheckbarNodesParam('menuTree');
                    var roleid = data.roleid;
                    var params = 'roleid=' + roleid;
                    $.each(nodes, function (i, item) {
                        params += '&ids=' + item.nodeId;
                    });

                    console.log(params);
                    // 保存角色和菜单的关系
                    $.post('${pageContext.request.contextPath}/role/saveRoleMenu.action', params, function (res) {
                        layer.close(index);     // 关闭弹层
                        layer.msg(res.msg);
                    });
                },
                success: function (layero, index) {     // 弹出后成功的回调方法
                //    初始化树
                    menuTree = dtree.render({
                        elem: '#menuTree',
                        dataStyle: 'layuiStyle',
                        response: {message: 'msg', statusCode: 0},
                        dataFormat: 'list',
                        checkbar: true,     // 开启复选框
                        checkbarType: 'all',
                        checkbarData: 'choose',
                        url: '${pageContext.request.contextPath}/role/initRoleMenuTreeJson.action?roleid=' + data.roleid
                    });
                }
            });
        }

    });

    function reloadTable(id){
        tableIns.reload({
            url:"${pageContext.request.contextPath}/role/loadAllRole.action?id="+id
        })
    }


</script>
</body>
</html>
