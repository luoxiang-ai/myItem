<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>用户管理</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Access-Control-Allow-Origin" content="*">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layui/css/layui.css" media="all" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/public.css" media="all" />
    <link href="${pageContext.request.contextPath}/resources/layui_ext/city-picker/city-picker.css" rel="stylesheet" />
</head>
<body class="childrenBody">
<!-- 搜索条件开始 -->
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>查询条件</legend>
</fieldset>
<form class="layui-form" method="post" id="searchFrm">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">客户名称:</label>
            <div class="layui-input-inline">
                <input type="text" name="custname"  autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">身份证:</label>
            <div class="layui-input-inline">
                <input type="text" name="identity" lay-verify="number"  autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">工作:</label>
            <div class="layui-input-inline">
                <input type="text" name="career"  autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">地址:</label>
            <div class="layui-input-inline">
                <input type="text" name="address"  autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">电话号码:</label>
            <div class="layui-input-inline">
                <input type="text" name="phone" lay-verify="number"  autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">性别:</label>
            <div class="layui-input-inline">
                <input type="radio" value="1" title="男" name="sex"  autocomplete="off" class="layui-input">
                <input type="radio" value="0" title="女" name="sex"  autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-col-md-3 layui-col-md-offset5">
            <button type="button" class="layui-btn layui-btn-normal  layui-icon layui-icon-search" id="doSearch">查询</button>
            <button type="reset" class="layui-btn layui-btn-warm  layui-icon layui-icon-refresh">重置</button>
        </div>
    </div>
</form>
<!-- 搜索条件结束 -->

<!-- 数据表格开始 -->
<table class="layui-hide" id="userTable" lay-filter="userTable"></table>
<div style="display: none;" id="userToolBar">
    <button type="button" class="layui-btn layui-btn-sm" lay-event="add">增加</button>
    <button type="button" class="layui-btn layui-btn-sm layui-btn-danger" lay-event="batchDelete">批量删除</button>
</div>
<div id="userBar" style="display: none;">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-xs layui-btn-normal" lay-event="selectUserRole">分配角色</a>
    <a class="layui-btn layui-btn-xs layui-btn-warm" lay-event="resetPwd">重置密码</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</div>
<!-- 数据表格结束 -->

<!-- 添加和修改的弹出层开始 -->
<div style="display: none;padding: 20px" id="saveOrUpdateDiv" class="layui-fluid">
    <form class="layui-form"  lay-filter="dataFrm" id="dataFrm">
        <div class="layui-form-item">
            <div class="layui-col-md5">
                <label class="layui-form-label">客户身份证:</label>
                <div class="layui-input-block">
                    <input type="text" lay-verify="required|identity" name="identity" placeholder="请输入客户身份证号" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-col-md5 layui-col-md-offset1">
                <label class="layui-form-label">客户名称:</label>
                <div class="layui-input-block">
                    <input type="text" lay-verify="required" name="custname" class="layui-input" placeholder="请输入客户名称">
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-col-md11">
                <label class="layui-form-label">地址:</label>
                <div class="layui-input-block">
                    <input type="text" lay-verify="required" autocomplete="on" class="layui-input" id="city-picker" name="address" readonly="readonly" data-toggle="city-picker" placeholder="请选择">
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-col-md4">
                <label class="layui-form-label">电话号码:</label>
                <div class="layui-input-block">
                    <input type="text" lay-verify="required|phone" name="phone" class="layui-input" placeholder="请输入客户电话">
                </div>
            </div>
            <div class="layui-col-md4">
                <label class="layui-form-label">工作职位:</label>
                <div class="layui-input-block">
                    <input type="text" name="career" class="layui-input" placeholder="请输入客户职位">
                </div>
            </div>
            <div class="layui-col-md4">
                <label class="layui-form-label">性别:</label>
                <div class="layui-input-block">
                    <input type="radio" title="男" value="1" name="sex">
                    <input type="radio" title="女" value="0" name="sex">
                </div>
            </div>
        </div>

        <div class="layui-form-item" style="margin-top: 40px">
            <button type="button" class="layui-btn layui-btn-normal layui-btn-sm layui-icon layui-icon-release layui-col-md1 layui-col-md-offset5" lay-filter="doSubmit" lay-submit="">提交</button>
            <button type="reset" id="dataFrmResetBtn" class="layui-btn layui-btn-warm layui-btn-sm layui-icon layui-icon-refresh layui-col-md1" >重置</button>
        </div>
    </form>
</div>
<!-- 添加和修改的弹出层结束 -->

<!-- 用户权限设置的弹出层开始 -->
<div style="display: none;" id="selectUserRole">
    <table class="layui-hide" id="roleTable" lay-filter="roleTable"></table>
</div>
<!-- 用户权限设置的弹出层结束 -->

<script src="${pageContext.request.contextPath}/resources/layui/layui.js"></script>
<script src="${pageContext.request.contextPath}/resources/layui_ext/city-picker/city-picker.data.js"></script>
<script type="text/javascript">
    var tableIns;
    layui.extend({
        dtree:'${pageContext.request.contextPath}/resources/layui_ext/dist/dtree',
        citypicker: '${pageContext.request.contextPath}/resources/layui_ext/city-picker/city-picker'
    }).use([ 'jquery', 'layer', 'form', 'table', 'citypicker'], function() {
        var $ = layui.jquery;
        var layer = layui.layer;
        var form = layui.form;
        var table = layui.table;
        var cityPicker = layui.citypicker;

        var currentPicker = new cityPicker("#city-picker", {
            provincename:"provinceId",
            cityname:"cityId",
            districtname: "districtId",
            level: 'districtId',// 级别
        });

        //渲染数据表格
        tableIns=table.render({
            elem: '#userTable'   //渲染的目标对象
            ,url:'${pageContext.request.contextPath}/user/loadAllUser.action' //数据接口
            ,title: '用户数据表'//数据导出来的标题
            ,toolbar:"#userToolBar"   //表格的工具条
            ,height:'full-150'
            ,cellMinWidth:100 //设置列的最小默认宽度
            ,page: true  //是否启用分页
            ,cols: [[   //列表数据
                {type: 'checkbox', fixed: 'left'}
                ,{field:'userid', title:'ID',align:'center'}
                ,{field:'loginname', title:'用户名称',align:'center', width: 120}
                ,{field: 'position', title: '描述', align: 'center', width: 120}
                ,{field:'identity', title:'身份证',align:'center', width: 180}
                ,{field: 'phone', title: '电话', align: 'center', width: 130}
                ,{field: 'address', title: '地址', align: 'center', width: 200}
                ,{field: 'sex', title: '性别', align: 'center', width: 80,sort: true, templet: function (d) {
                    return d.sex == '1' ? '男' : '女';
                }}
                ,{field:'available', title:'是否可用',align:'center',width: 120,sort:true,templet:function(d){
                        return d.available=='1'?'<font color=green>可用</font>':'<font color=red>不可用</font>';
                    }},
                {fixed: 'right', title: '操作', toolbar: '#userBar', align: 'center', width: 260}
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
                url:"${pageContext.request.contextPath}/user/loadAllUser.action?"+params
            })
        });

        //监听头部工具栏事件
        table.on("toolbar(userTable)",function(obj){
            switch(obj.event){
                case 'add':
                    openAddUser();
                    break;
                case 'batchDelete':
                    batchDeleteUsers();
                    break;
            };
        })
        //监听行工具事件
        table.on('tool(userTable)', function(obj){
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            if(layEvent === 'del'){ //删除
                layer.confirm('您确定删除【' + data.loginname + '】这个用户吗', function (index) {
                    console.log(index);
                    // 向服务端发送删除请求
                    $.post('${pageContext.request.contextPath}/user/deleteUser.action', {userId: data.userid}, function (res) {
                        layer.msg(res.msg);
                        // 重新刷新数据表格
                        tableIns.reload();
                    })
                });
            } else if(layEvent === 'edit'){ //编辑
                openUpdateUser(data);
            } else if (layEvent == 'selectUserRole') {
                openSelectUserRole(data);
            }
        });

        var url;
        var mainIndex;
//打开添加页面
        function openAddUser(){
            mainIndex=layer.open({
                type:1,
                title:'添加用户',
                content:$("#saveOrUpdateDiv"),
                area:['800px','450px'],
                success:function(index){
                    //清空表单数据
                    $("#dataFrm")[0].reset();
                    url="${pageContext.request.contextPath}/user/addUser.action";
                }
            });
        }

        // 用户修改函数
        function openUpdateUser(data){
            console.log(data);
            mainIndex = layer.open({
                type:1,
                title:'修改用户',
                content:$("#saveOrUpdateDiv"),
                area:['800px','450px'],
                success:function(index){
                    form.val("dataFrm",data);
                    url="${pageContext.request.contextPath}/user/updateUser.action";

                    // 地址设置初始值，如果不需要，则去掉下面这行代码
                    currentPicker.setValue(data.address);
                },
                cancel: function(){
                    currentPicker.setValue('');
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
            $("#userSelectDiv").toggleClass("layui-show layui-anim layui-anim-upbit");
        });

        // 批量删除用户
        function batchDeleteUsers() {
            var checkStatus = table.checkStatus('userTable');
            var data = checkStatus.data;
            var params = '';
            $.each(data, function (i, item) {
                if (i == 0) {
                    params += 'ids=' + item.userid;
                } else {
                    params += '&ids=' + item.userid;
                }
                layer.alert(params);
            });

            layer.confirm('您确定要批量删除这些用户吗', function (index) {
                //    向服务端发送删除请求
                $.post('${pageContext.request.contextPath}/user/batchDeleteUser.action', params, function (res) {
                    layer.msg(res.msg);
                    //    重新刷新数据表格
                    tableIns.reload();
                })
            })
        }

        // 打开用户分配角色弹层
        function openSelectUserRole(data) {
            layer.open({
                type: 1,
                title: '分配角色',
                content: $('#selectUserRole'),
                offset: 'auto',
                area: ['800px', '400px'],
                btn: ['确定分配', '取消分配'],
                btnAlign: 'c',
                anim: 5,
                yes: function(index) {
                    var checkStatus = table.checkStatus('roleTable');
                    var checkData = checkStatus.data;
                    var params = "userid=" + data.userid;
                    $.each(checkData, function (i, item) {
                        params += '&ids=' + item.roleid;
                    })
                    console.log(params);
                    $.post('${pageContext.request.contextPath}/user/saveUserRole.action', params, function (res) {
                        layer.msg(res.msg);
                    })
                  //  点击确定分配角色之后，关闭弹层
                  layer.close(index);
                },
                success: function () {
                    //渲染数据表格
                    mainIndex = table.render({
                        elem: '#roleTable'   //渲染的目标对象
                        ,url:'${pageContext.request.contextPath}/user/loadUserRole.action?userid=' + data.userid //数据接口
                        ,title: '角色数据表'//数据导出来的标题

                        ,cellMinWidth:100 //设置列的最小默认宽度
                        ,cols: [[   //列表数据
                            {type: 'checkbox', fixed: 'left'}
                            ,{field:'roleid', title:'ID',align:'center'}
                            ,{field:'rolename', title:'角色名称',align:'center'}
                            ,{field: 'roledesc', title: '角色描述', align: 'center'}
                        ]]
                    })
                }
            })
        }

    });

    function reloadTable(id){
        tableIns.reload({
            url:"${pageContext.request.contextPath}/user/loadAllUser.action?id="+id
        })
    }


</script>
</body>
</html>
