<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>教师课程管理</title>
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
<div id="classBar" style="display: none;">
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="showResourceList">查看资料</a>
    <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="showWork">查看作业</a>
    <a class="layui-btn layui-btn-xs" lay-event="studentNum">查看学生人数</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="editOutline">编辑课程大纲</a>
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
    <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="showData">查看提交情况</a>
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

<%-- 查看作业提交列表开始 --%>
<div style="display: none;padding: 10px;" id="showData">
    <table class="layui-hide" id="studentWorkTable" lay-filter="studentWorkTable"></table>
</div>
<div id="studentWorkBar" style="display: none;">
    <a class="layui-btn layui-btn-xs" lay-event="review">批阅</a>
    <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="downloadWork">下载</a>
</div>
<%-- 查看作业提交列表结束 --%>

<%-- 查看课程报名人数开始 --%>
<div style="display: none;padding: 10px;" id="courseStudentNum">
    <table class="layui-hide" id="courseStudentNumTable" lay-filter="courseStudentNumTable"></table>
</div>
<div id="studentCourseBar" style="display: none;">
    <a class="layui-btn layui-btn-xs" lay-event="score">打分</a>
</div>
<%-- 查看课程报名人数结束 --%>

<%-- 打分页面开始 --%>
<div id="score" style="display: none;">
    <form class="layui-form" method="post" lay-filter="scoreFrm" id="scoreFrm">
        <div class="layui-form-item">
            <div class="layui-col-md4">
                <label class="layui-form-label">分数:</label>
                <div class="layui-input-inline">
                    <input type="hidden" name="cid" id="cid">
                    <input type="hidden" name="userid" id="sid">
                    <input type="text" name="score" id="studentScore" autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-inline layui-col-md-offset5">
                <button type="button" class="layui-btn layui-btn-normal  layui-icon layui-icon-search" id="doScore">确定</button>
            </div>
        </div>
    </form>
</div>
<%-- 打分页面结束 --%>

<%-- 编辑课程大纲开始 --%>
<div id="outline" style="display: none;padding: 20px;">
    <form class="layui-form" method="post" lay-filter="outlineFrm" id="outlineFrm">
        <div class="layui-form-item">
            <label class="layui-form-label">大纲:</label>
            <div class="layui-input-block">
                <input type="hidden" name="id">
                <textarea class="layui-textarea layui-hide" name="outline" id="outlineContent"></textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline layui-col-md-offset5">
                <button type="button" class="layui-btn layui-btn-normal" lay-filter="doOutline" id="doOutline">确定</button>
            </div>
        </div>
    </form>
</div>
<%-- 编辑课程大纲结束 --%>

<script src="${pageContext.request.contextPath}/resources/layui/layui.js"></script>
<script type="text/javascript">
    var tableIns;
    var outlineTable;
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
            ,url:'${pageContext.request.contextPath}/course/loadCourseByTeacherId.action?userid=' + ${sessionScope.sysUser.userid} //数据接口
            ,title: '课程信息数据表'//数据导出来的标题
            ,height:'full-200'
            ,cellMinWidth:100 //设置列的最小默认宽度
            ,page: true  //是否启用分页
            ,cols: [[   //列表数据
                {type: 'numbers', fixed: 'left', title: '编号'}
                ,{field:'id', title:'ID',align:'center', hide: true}
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
            console.log(params);
            tableIns.reload({
                url:"${pageContext.request.contextPath}/course/fuzzyQueryCourse.action?"+params,
                page:{curr:1}
            })
        });

        // 保存分数
        $("#doScore").click(function(){
            // var params=$("#scoreFrm").serialize();
            // console.log(params);
            var sid = $('#sid').val()
            cid = $('#cid').val()
            studentScore =$('#studentScore').val();
            url = '${pageContext.request.contextPath}/scoreInfo/saveScore.action';
            $.post(url, {
                sid: sid,
                cid: cid,
                score: studentScore
            }, function(res) {
                layer.msg(res.msg);
                layer.close(mainIndex);
                tableIns.reload();
            })
        });

        // 保存分数
        $("#doOutline").click(function(){
            layedit.sync(editIndex);//把富文本里面的数据同步到自己写的textarea里面
            var params=$("#outlineFrm").serialize();
            url = '${pageContext.request.contextPath}/course/updateCourse.action';
            $.post(url, params, function(res){
                layer.msg(res.msg);
                // 关闭弹出层
                layer.close(mainIndex);
                tableIns.reload();
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
            } else if(layEvent == 'studentNum') {
                studentNum(data);
            } else if (layEvent == 'editOutline') {
                editOutline(data);
            }
        });

        //监听行工具事件
        table.on('tool(studentWorkTable)', function(obj){
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）

           if(layEvent == 'review') {
               review(data);
           } else if(layEvent == 'downloadWork') {
               downloadWork(data);
           }
        });

        //监听行工具事件
        table.on('tool(courseStudentNumTable)', function(obj){
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）

            if(layEvent == 'score') {
                score(data);
            }
        });

        //监听单元格编辑
        table.on('edit(studentWorkTable)', function(obj){
            var value = obj.value; //得到修改后的值
            var data = obj.data; //得到所在行所有键值
            var field = obj.field; //得到字段

            var o = {};
            o.id = data.id;
            o[field] = value;
            // layer.msg('字段：' + field + '值：' + value);
            url = '${pageContext.request.contextPath}/studentWork/updateStudentWork.action';
            $.post(url, o, function(res) {
                layer.msg(res.msg);
                // 刷新数据表格
                tableIns.reload();
            })
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
            } else if(layEvent === 'showData') {
                showData(data);
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
                            ,{fixed: 'right', title:'操作', toolbar: '#workBar', width:280,align:'center'}
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

        // 查看作业提交情况
        function showData(data) {
            layer.open({
                type:1,
                title:'正在要查看作业提交列表',
                content:$("#showData"),
                area:['700px','400px'],
                success:function(index){
                    tableIns = table.render({
                        elem: '#studentWorkTable'   //渲染的目标对象
                        ,url:'${pageContext.request.contextPath}/studentWork/loadStudentWorkByWorkId.action?wid=' + data.wid //数据接口
                        ,title: '学生作业数据表'//数据导出来的标题
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
                            ,{field:'realname', title:'学生姓名', align: 'center'}
                            ,{field:'commonts', title:'批语',align:'center', edit: 'text'}
                            ,{field:'score', title:'成绩',align:'center', edit: 'text'}
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

        // 批阅作业
        function review(data) {
            url = '${pageContext.request.contextPath}/studentWork/reviewWork.action';
            $.post(url, {id: data.id}, function(res) {
                layer.msg(res.msg);
                // 状态0：表示成功
                if(res.code == 0) {
                    //    刷新数据表格
                    tableIns.reload();
                }
            })
        }

        // 下载作业
        function downloadWork(data) {
            window.location.href = '${pageContext.request.contextPath}/file/download.action?path=' + data.path + '&fileName=' + data.name;
        }

        // 查看课程报名人数
        function studentNum(data) {
            console.log(data);
            layer.open({
                type:1,
                title:'查看【' + data.name + '】课程报名人数',
                content:$("#courseStudentNum"),
                area:['700px','400px'],
                success:function(index){
                    tableIns = table.render({
                        elem: '#courseStudentNumTable'   //渲染的目标对象
                        ,url:'${pageContext.request.contextPath}/student/loadStudentNumByCourseId.action?cid=' + data.id //数据接口
                        ,title: '学生成绩数据表'//数据导出来的标题
                        ,height:'300'
                        ,cellMinWidth:100 //设置列的最小默认宽度
                        ,page: true  //是否启用分页
                        ,cols: [[   //列表数据
                            {type: 'numbers', fixed: 'left', title: '编号'}
                            ,{field:'userid', title:'ID',align:'center', hide: true}
                            ,{field:'loginname', title:'学号',align:'center'}
                            ,{field:'realname', title:'姓名', align: 'center'}
                            ,{field: 'sex', title: '性别', align: 'center', width: 80,sort: true, templet: function (d) {
                                    return d.sex == '1' ? '男' : '女';
                                }}
                            ,{field:'score', title:'成绩',align:'center'}
                            ,{fixed:'right', title:'操作', align: 'center', width: 120, toolbar: '#studentCourseBar'}
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

        // 打分
        function score(data) {
            mainIndex = layer.open({
                type: 1,
                title: '正在给【'+ data.realname +'】评分',
                content: $('#score'),
                area:['400px','300px'],
                success: function() {
                    console.log(data);
                    form.val('scoreFrm', data);
                }
            })
        }

        var editIndex;
        // 编辑课程大纲
        function editOutline(data) {
            mainIndex = layer.open({
                type: 1,
                title: '正在给【'+ data.name +'】课程定制大纲',
                content: $('#outline'),
                area:['800px','500px'],
                success: function() {
                    editIndex=layedit.build('outlineContent'); //建立编辑器
                    // 设置内容
                    layedit.setContent(editIndex, data.outline);
                    form.val('outlineFrm', data);
                }
            })
        }
    });
</script>
</body>
</html>
