<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>班级管理</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Access-Control-Allow-Origin" content="*">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="icon" href="favicon.ico">
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
        <div class="layui-col-md4">
            <label class="layui-form-label">班级名称:</label>
            <div class="layui-input-inline">
                <input type="text" name="className" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline layui-col-md-offset5">
            <button type="button" class="layui-btn layui-btn-normal  layui-icon layui-icon-search" id="doSearch">查询</button>
            <button type="reset" class="layui-btn layui-btn-warm  layui-icon layui-icon-refresh">重置</button>
        </div>
    </div>
</form>
<!-- 搜索条件结束 -->

<!-- 数据表格开始 -->
<table class="layui-hide" id="classTable" lay-filter="classTable"></table>
<div style="display: none;" id="classToolBar">
    <button type="button" class="layui-btn layui-btn-sm" lay-event="add">增加</button>
    <button type="button" class="layui-btn layui-btn-danger layui-btn-sm" lay-event="deleteBatch">批量删除</button>
</div>
<div  id="classBar" style="display: none;">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-xs layui-btn-normal" lay-event="viewUser">查看成员</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</div>
<!-- 数据表格结束 -->

<!-- 添加和修改的弹出层开始 -->
<div style="display: none;padding: 20px" id="saveOrUpdateDiv">
    <form class="layui-form"  lay-filter="dataFrm" id="dataFrm">
        <div class="layui-form-item">
            <label class="layui-form-label">班级名称:</label>
            <div class="layui-input-block">
                <input type="hidden" id="hiddenId" name="classId">
                <input type="text" lay-verify="required" name="className" class="layui-input" placeholder="请输入班级名称">
            </div>
        </div>

        <div class="layui-form-item layui-col-md-offset3 layui-col-md-offset4" style="margin-top: 40px">
            <button type="button" class="layui-btn layui-btn-normal layui-btn-sm layui-icon layui-icon-release" lay-filter="doSubmit" lay-submit="">提交</button>
            <button type="reset" id="dataFrmResetBtn" class="layui-btn layui-btn-warm layui-btn-sm layui-icon layui-icon-refresh" >重置</button>
        </div>
    </form>

</div>
<!-- 添加和修改的弹出层结束 -->
<%-- 查看班级成员列表开始 --%>
<div style="display: none;padding: 10px;" id="showUser">
    <table class="layui-hide" id="userTable" lay-filter="userTable"></table>
</div>
<%-- 查看班级成员列表结束 --%>

<script src="${pageContext.request.contextPath}/resources/layui/layui.js"></script>
<script type="text/javascript">
    var tableIns;
    layui.use([ 'jquery', 'layer', 'form', 'table' ,'upload'], function() {
        var $ = layui.jquery;
        var layer = layui.layer;
        var form = layui.form;
        var table = layui.table;

        //渲染数据表格
        tableIns=table.render({
            elem: '#classTable'   //渲染的目标对象
            ,url:'${pageContext.request.contextPath}/class/loadAllClass.action' //数据接口
            ,title: '班级信息数据表'//数据导出来的标题
            ,toolbar:"#classToolBar"   //表格的工具条
            ,height:'full-200'
            ,cellMinWidth:100 //设置列的最小默认宽度
            ,page: true  //是否启用分页
            ,cols: [[   //列表数据
                {type: 'checkbox', fixed: 'left'}
                ,{field:'classId', title:'编号',align:'center'}
                ,{field:'className', title:'班级名称',align:'center'}
                ,{fixed: 'right', title:'操作', toolbar: '#classBar', width:280,align:'center'}
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
            tableIns.reload({
                url:"${pageContext.request.contextPath}/class/fuzzyQueryClass.action?"+params,
                page:{curr:1}
            })
        });

        //监听头部工具栏事件
        table.on("toolbar(classTable)",function(obj){
            switch(obj.event){
                case 'add':
                    openAddclass();
                    break;
                case 'deleteBatch':
                    deleteBatch();
                    break;
            };
        })


        //监听行工具事件
        table.on('tool(classTable)', function(obj){
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）

            if(layEvent === 'del'){ //删除
                console.log(obj);
                layer.confirm('真的删除【'+data.className+'】这个班级吗', function(index){
                    //向服务端发送删除指令
                    $.post("${pageContext.request.contextPath}/class/deleteClass.action",{id:data.classId},function(res){
                        layer.msg(res.msg);
                        //刷新数据表格
                        tableIns.reload();
                    })
                });
            } else if(layEvent === 'edit'){ //编辑
                openUpdateclass(data);
            } else if (layEvent === 'viewUser') {  // 查看班级成员
                showClassStudent(data);
            }
        });

        var url;
        var mainIndex;

        // 打开添加页面
        function openAddclass(){
            mainIndex=layer.open({
                type:1,
                title:'添加班级',
                content:$("#saveOrUpdateDiv"),
                area:['420px','230px'],
                success:function(index){
                    //清空表单数据
                    $("#dataFrm")[0].reset();
                    // 清空隐藏表单域的值
                    $('#hiddenId').val('');
                    url="${pageContext.request.contextPath}/class/addClass.action";
                }
            })
        }


        //打开修改页面
        function openUpdateclass(data){
            mainIndex=layer.open({
                type:1,
                title:'修改班级信息',
                content:$("#saveOrUpdateDiv"),
                area:['420px','230px'],
                success:function(index){
                    form.val("dataFrm",data);
                    url="${pageContext.request.contextPath}/class/updateClass.action";
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
                layer.close(mainIndex);
                //刷新数据 表格
                tableIns.reload();
            })
        });

        //批量删除
        function deleteBatch(){
            //得到选中的数据行
            var checkStatus = table.checkStatus('classTable');
            var data = checkStatus.data;
            var params="";
            $.each(data,function(i,item){
                if(i==0){
                    params+="ids="+item.classId;
                }else{
                    params+="&ids="+item.classId;
                }
            });
            layer.confirm('真的删除选中的这些班级吗', function(index){
                //向服务端发送删除指令
                $.post("${pageContext.request.contextPath}/class/batchDeleteClass.action",params,function(res){
                    layer.msg(res.msg);
                    //刷新数据 表格
                    tableIns.reload();
                })
            });
        }

        // 查看班级学生
        function showClassStudent(data) {
            console.log(data);
            layer.open({
                type:1,
                title:'正在要查看【' + data.className + '】的学生列表',
                content:$("#showUser"),
                area:['700px','400px'],
                success:function(index){
                    tableIns = table.render({
                        elem: '#userTable'   //渲染的目标对象
                        ,url:'${pageContext.request.contextPath}/student/loadStudentByClassId.action?classId=' + data.classId //数据接口
                        ,title: '学生数据表'//数据导出来的标题
                        ,height:'300'
                        ,cellMinWidth:100 //设置列的最小默认宽度
                        ,page: true  //是否启用分页
                        ,cols: [[   //列表数据
                            {type: 'numbers', fixed: 'left', title: '编号'}
                            ,{field:'loginname', title:'学号',align:'center', width: 250}
                            ,{field: 'realname', title: '姓名', align:'center',width:150}
                            ,{field:'identity', title:'身份证',align:'center', width: 180}
                            ,{field: 'phone', title: '电话', align: 'center', width: 130}
                            ,{field: 'address', title: '地址', align: 'center', width: 200}
                            ,{field: 'sex', title: '性别', align: 'center', width: 80,sort: true, templet: function (d) {
                                    return d.sex == '1' ? '男' : '女';
                             }}
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
                }
            })
        }
    });
</script>
</body>
</html>
