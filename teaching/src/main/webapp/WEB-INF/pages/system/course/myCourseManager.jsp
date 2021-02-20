<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>课程管理</title>
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
            <label class="layui-form-label">课程名称:</label>
            <div class="layui-input-inline">
                <input type="text" name="name" autocomplete="off" class="layui-input">
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
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="showResourceList">查看资料</a>
    <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="showWork">查看作业</a>
    <a class="layui-btn layui-btn-xs" lay-event="outline">课程大纲</a>
    <a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="courseTable">课程表</a>
</div>
<!-- 数据表格结束 -->

<!-- 添加和修改的弹出层开始 -->
<div style="display: none;padding: 20px" id="saveOrUpdateDiv">
    <form class="layui-form"  lay-filter="dataFrm" id="dataFrm">
        <div class="layui-form-item">
            <label class="layui-form-label">课程名称:</label>
            <div class="layui-input-block">
                <input type="hidden" id="hiddenId" name="id">
                <input type="text" lay-verify="required" name="name" class="layui-input" placeholder="请输入课程名称">
            </div>
        </div>

        <div class="layui-form-item layui-col-md-offset3 layui-col-md-offset4" style="margin-top: 40px">
            <button type="button" class="layui-btn layui-btn-normal layui-btn-sm layui-icon layui-icon-release" lay-filter="doSubmit" lay-submit="">提交</button>
            <button type="reset" id="dataFrmResetBtn" class="layui-btn layui-btn-warm layui-btn-sm layui-icon layui-icon-refresh" >重置</button>
        </div>
    </form>

</div>
<!-- 添加和修改的弹出层结束 -->

<%-- 查看课程的资料列表开始 --%>
<div style="display: none;padding: 10px;" id="showResource">
    <table class="layui-hide" id="resourceTable" lay-filter="resourceTable"></table>
</div>
<%-- 查看课程的资料列表结束 --%>
<div id="resourceBar" style="display: none;">
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="delres">删除</a>
</div>

<%-- 布置作业开始 --%>
<div id="showWork" style="display: none;padding: 10px;">
    <table class="layui-hide" id="workTable" lay-filter="workTable"></table>
</div>
<div id="workBar" style="display: none;">
    <a class="layui-btn layui-btn-xs" lay-event="showContent">查看内容</a>
    <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="uploadWork">上传作业</a>
    <a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="myWork">我的作业</a>
</div>
<%-- 布置作业结束 --%>

<%-- 作业查看开始 --%>
<div id="showContent" style="display: none;padding: 10px;">
    <div id="content"></div>
</div>
<%-- 作业查看结束 --%>

<%-- 文件上传开始 --%>
<div style="display: none;" id="uploadWork">
    <label class="layui-form-label">作业名称</label>
    <input type="text" lay-verify="required" class="layui-input" id="workName">
    <button type="button" class="layui-btn layui-btn-normal" id="selectFile">选择文件</button>
    <button type="button" class="layui-btn" id="upload">开始上传</button>
</div>
<%-- 文件上传结束 --%>

<%-- 我提交作业的列表开始 --%>
<div style="display: none;padding: 10px;" id="myWork">
    <table class="layui-hide" id="myWorkTable" lay-filter="myWorkTable"></table>
</div>
<%-- 我提交作业的列表结束 --%>

<%-- 查看课程表开始 --%>
<div style="display: none;padding: 10px;" id="courseTableDiv">
    <table class="layui-hide" id="courseTable" lay-filter="courseTable"></table>
</div>
<%-- 查看课程表结束 --%>

<script src="${pageContext.request.contextPath}/resources/layui/layui.js"></script>
<script type="text/javascript">
    var tableIns;
    layui.use([ 'jquery', 'layer', 'form', 'table' ,'upload', 'layedit', 'laydate'], function() {
        var $ = layui.jquery;
        var layer = layui.layer;
        var form = layui.form;
        var table = layui.table;
        var upload = layui.upload;
        var layedit = layui.layedit;
        var laydate = layui.laydate;

        //执行一个laydate实例
        laydate.render({
            elem: '#endTime' //指定元素
            ,type: 'datetime'
        });

        //渲染数据表格
        tableIns=table.render({
            elem: '#classTable'   //渲染的目标对象
            ,url:'${pageContext.request.contextPath}/course/loadCourseByUserId.action?userid=' + ${sessionScope.sysUser.userid} //数据接口
            ,title: '课程信息数据表'//数据导出来的标题
            ,height:'full-200'
            ,cellMinWidth:100 //设置列的最小默认宽度
            ,page: true  //是否启用分页
            ,cols: [[   //列表数据
                {type: 'numbers', fixed: 'left', title: '编号'}
                ,{field:'id', title:'ID',align:'center'}
                ,{field:'name', title:'课程名称',align:'center'}
                ,{fixed: 'right', title:'操作', toolbar: '#classBar', width:400,align:'center'}
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
                url:"${pageContext.request.contextPath}/course/fuzzyQueryCourse.action?"+params,
                page:{curr:1}
            })
        });

        //监听行工具事件
        table.on('tool(classTable)', function(obj){
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）

            if(layEvent === 'showResourceList') {
                showResourceList(data);
            } else if(layEvent === 'showWork') {
                showWork(data);
            } else if(layEvent == 'outline') {
                showOutline(data);
            } else if(layEvent == 'courseTable') {
                courseTable(data);
            }
        });

        // 监听资源列表的table
        table.on('tool(resourceTable)', function (obj) {
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event;
            if(layEvent === 'delres') {   // 删除资源
                console.log(data);
                $.post('${pageContext.request.contextPath}/resource/deleteResource.action', {id: data.rId, path: data.path}, function (res) {
                    // 刷新表格
                    tableIns.reload();
                    // 提示信息
                    layer.msg(res.msg);
                })
            }
        })

        //监听行工具事件
        table.on('tool(workTable)', function(obj){
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）

            if(layEvent === 'showContent') {
                showContent(data);
            } else if(layEvent === 'uploadWork') {
                uploadWork(data);
            } else if(layEvent == 'myWork') {
                myWork(data);
            }
        });

        var url;
        var mainIndex;

        //保存
        form.on("submit(doSubmit)",function(obj){
            console.log(obj.field);
            var params = obj.field;
            //序列化表单数据
            // var params=$("#dataFrm").serialize();
            $.post(url,params,function(obj){
                layer.msg(obj.msg);
                //关闭弹出层
                layer.close(mainIndex);
                //刷新数据 表格
                tableIns.reload();
            })
        });


        // 查看该课程资料列表
        function showResourceList(data) {
            layer.open({
                type:1,
                title:'正在要查看【' + data.name + '】的资料',
                content:$("#showResource"),
                area:['700px','400px'],
                success:function(index){
                    tableIns = table.render({
                        elem: '#resourceTable'   //渲染的目标对象
                        ,url:'${pageContext.request.contextPath}/resource/loadResourceByCourseId.action?cId=' + data.id //数据接口
                        ,title: '课程信息数据表'//数据导出来的标题
                        ,height:'300'
                        ,cellMinWidth:100 //设置列的最小默认宽度
                        ,page: true  //是否启用分页
                        ,cols: [[   //列表数据
                            {type: 'numbers', fixed: 'left', title: '编号'}
                            ,{field:'rId', title:'ID',align:'center'}
                            ,{field:'name', title:'资料名称',align:'center'}
                            ,{fixed: 'right', title:'操作', toolbar: '#resourceBar', width:100,align:'center'}
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

        // 布置作业
        function showWork(data) {
            console.log(data);
            layer.open({
                type:1,
                title:'正在查看【' + data.name + '】的作业列表',
                content:$("#showWork"),
                area:['700px','400px'],
                success:function(index){
                    table.render({
                        elem: '#workTable'   //渲染的目标对象
                        ,url:'${pageContext.request.contextPath}/work/loadWorkByCourseId.action?cid=' + data.id //数据接口
                        ,height:'full-200'
                        ,cellMinWidth:100 //设置列的最小默认宽度
                        ,page: true  //是否启用分页
                        ,cols: [[   //列表数据
                            {type: 'numbers', fixed: 'left', title: '编号'}
                            ,{field:'wid', title:'ID',align:'center', hide: true}
                            ,{field:'startTime', title:'发布时间',align:'center'}
                            ,{field:'endTime', title:'截止时间',align:'center'}
                            ,{fixed: 'right', title:'操作', toolbar: '#workBar', width:250,align:'center'}
                        ]]
                    })
                }
            })
        }

        // 查看作业内容
        function showContent(data) {
            layer.open({
                type: 1,
                title: '正在查看作业内容',
                content: $('#showContent'),
                area:['400px','300px'],
                success: function() {
                    $('#content').html(data.content);
                }
            })
        }

        // 上传作业
        function uploadWork(data) {
            url = '${pageContext.request.contextPath}/work/verificationDate.action'
            // 验证上传日期是否超过的截止日期
            $.post(url, {date: data.endTime}, function (res){
                if(res.code == -1) {
                    layer.alert(res.msg);
                    return;
                } else {
                    //    完成验证的情况（作业上传）
                    mainIndex = layer.open({
                        type:1,
                        title:'上传作业',
                        content:$("#uploadWork"),
                        area:['420px','230px'],
                        success:function(index){
                            //清空
                            $('#workName').val('');

                            upload.render({
                                elem: '#selectFile'
                                ,url: '${pageContext.request.contextPath}/file/upload.action'
                                ,auto: false
                                ,field: 'mf'
                                ,accept: 'file'
                                ,bindAction: '#upload'
                                ,error: function () {
                                    layer.msg('上传失败');
                                }
                                ,done: function(res){
                                    console.log(res)
                                    if(res.code == 0) {
                                        var name = $('#workName').val();
                                        url = '${pageContext.request.contextPath}/studentWork/addStudentWork.action';
                                        $.post(url, {
                                            name: name,
                                            path: res.data.src,
                                            uid: '${sessionScope.sysUser.userid}',   // 用户id
                                            wid: data.wid       // 作业id
                                        }, function(r) {
                                            layer.msg(r.msg);
                                        })
                                    }
                                    layer.close(mainIndex);
                                }
                            })
                        }
                    })
                }
            })
        }

        // 查看我提交的作业
        function myWork(data) {
            layer.open({
                type:1,
                title:'我提交的作业',
                content:$("#myWork"),
                area:['700px','400px'],
                success:function(index){
                    console.log(data);
                    tableIns = table.render({
                        elem: '#myWorkTable'   //渲染的目标对象
                        ,url:'${pageContext.request.contextPath}/studentWork/loadMyWork.action?wid=' + data.wid + '&uid=${sessionScope.sysUser.userid}' //数据接口
                        ,height:'300'
                        ,cellMinWidth:100 //设置列的最小默认宽度
                        ,page: true  //是否启用分页
                        ,cols: [[   //列表数据
                            {type: 'numbers', fixed: 'left', title: '编号'}
                            ,{field:'wid', title:'ID',align:'center', hide: true}
                            ,{field:'name', title:'作业名称',align:'center'}
                            ,{field:'flag', title:'是否批阅',align:'center', templet: function(d) {
                                    return d.flag == 1 ? '<font color=green>已批阅</font>' : '<font color=red>未批阅</font>';
                                }}
                            ,{field:'commonts', title:'批语',align:'center'}
                            ,{field:'score', title:'成绩',align:'center'}
                            ,{fixed:'right', title:'操作', align: 'center', width: 120, toolbar: '#studentWorkBar'}
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

        // 查看课程大纲
        function showOutline(data) {
            layer.open({
                type:1,
                title:'正在查看【'+ data.name +'】课程大纲',
                content:$("#showContent"),
                area:['800px','400px'],
                success:function(index){
                    $("#content").html(data.outline);
                }
            });
        }

        // 查看课程表
        function courseTable(data) {
            layer.open({
                type:1,
                title:'正在要查看【' + data.name + '】的课程表',
                content:$("#courseTableDiv"),
                area:['700px','400px'],
                success:function(index){
                    tableIns = table.render({
                        elem: '#courseTable'   //渲染的目标对象
                        ,url:'${pageContext.request.contextPath}/courseTable/loadCourseTableByCid.action?cid=' + data.id //数据接口
                        ,height:'300'
                        ,cellMinWidth:100 //设置列的最小默认宽度
                        ,page: true  //是否启用分页
                        ,cols: [[   //列表数据
                            {type: 'numbers', fixed: 'left', title: '编号'}
                            ,{field:'title', title:'上课主题',align:'center'}
                            ,{field:'starttime', title:'上课时间',align:'center'}
                            ,{field:'endtime', title:'下课时间',align:'center'}
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
