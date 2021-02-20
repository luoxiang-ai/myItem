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
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="uploadResource">上传资料</a>
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="showResourceList">查看资料</a>
    <a class="layui-btn layui-btn layui-btn-xs" lay-event="homework">布置作业</a>
    <a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="courseTable">编辑课程表</a>
</div>
<!-- 数据表格结束 -->

<!-- 添加和修改的弹出层开始 -->
<div style="display: none;padding: 20px" id="saveOrUpdateDiv">
    <form class="layui-form"  lay-filter="dataFrm" id="dataFrm">
        <div class="layui-form-item">
            <label class="layui-form-label">课程分类:</label>
            <div class="layui-input-block">
                <select name="sid" id="sid"></select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">课程名称:</label>
            <div class="layui-input-block">
                <input type="hidden" id="hiddenId" name="id">
                <input type="text" lay-verify="required" id="courseName" name="name" class="layui-input" placeholder="请输入课程名称">
            </div>
        </div>

        <h2 style="text-align: center; padding: 10px;">授课教师列表</h2>
        <%-- 老师数据表格 --%>
        <table class="layui-hide" id="teacherTable" lay-filter="teacherTable"></table>

        <div class="layui-form-item layui-col-md-offset3 layui-col-md-offset4" style="margin-top: 40px">
            <button type="button" class="layui-btn layui-btn-normal layui-btn-sm layui-icon layui-icon-release" id="btn" data-type="getCheckData">提交</button>
            <button type="reset" id="dataFrmResetBtn" class="layui-btn layui-btn-warm layui-btn-sm layui-icon layui-icon-refresh" >重置</button>
        </div>
    </form>

</div>
<!-- 添加和修改的弹出层结束 -->

<%-- 文件上传开始 --%>
<div style="display: none;" id="uploadResource">
    <label class="layui-form-label">资源名称</label>
    <input type="text" lay-verify="required" class="layui-input" id="resourceName">
    <button type="button" class="layui-btn layui-btn-normal" id="selectFile">选择文件</button>
    <button type="button" class="layui-btn" id="upload">开始上传</button>
</div>
<%-- 文件上传结束 --%>

<%-- 查看课程的资料列表开始 --%>
<div style="display: none;padding: 10px;" id="showResource">
    <table class="layui-hide" id="resourceTable" lay-filter="resourceTable"></table>
</div>
<%-- 查看课程的资料列表结束 --%>
<div id="resourceBar" style="display: none;">
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="delres">删除</a>
</div>

<%-- 布置作业开始 --%>
<div id="homework" style="display: none;padding: 10px;">
    <form class="layui-form" lay-filter="dataFrm" id="dataFrm2">
        <div class="layui-form-item">
            <label class="layui-form-label">截止日期:</label>
            <div class="layui-input-block">
                <input type="hidden" name="cid" id="cid">
                <input type="text" lay-verify="required" readonly="readonly" id="endTime" name="endTime" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">作业内容:</label>
            <div class="layui-input-block">
                <textarea id="workContent" class="layui-textarea" name="content"></textarea>
            </div>
        </div>
        <div class="layui-form-item layui-col-md-offset5">
            <button type="button" class="layui-btn layui-btn-primary" lay-filter="doSubmit" lay-submit="">提交</button>
            <button type="reset" class="layui-btn layui-btn-warm">重置</button>
        </div>
    </form>
</div>
<%-- 布置作业结束 --%>

<%-- 添加课程表开始 --%>
<div id="courseTable" style="display: none;padding: 10px;">
    <form class="layui-form" lay-filter="courseTableFrm" id="courseTableFrm">
        <div class="layui-form-item">
            <label class="layui-form-label">开始时间:</label>
            <div class="layui-input-block">
                <input type="text" lay-verify="required" readonly="readonly" id="courseStartTime" name="starttime" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">截止时间:</label>
            <div class="layui-input-block">
                <input type="text" lay-verify="required" readonly="readonly" id="courseEndTime" name="endtime" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">课程标题:</label>
            <div class="layui-input-block">
                <input type="text" name="title" id="title" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item layui-col-md-offset5">
            <button type="button" class="layui-btn layui-btn-primary" id="addBtn">提交</button>
            <button type="reset" class="layui-btn layui-btn-warm">重置</button>
        </div>
    </form>
</div>
<%-- 添加课程表结束 --%>

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

        laydate.render({
            elem: '#courseStartTime' //指定元素
            ,type: 'datetime'
        });

        laydate.render({
            elem: '#courseEndTime' //指定元素
            ,type: 'datetime'
        });

        //渲染数据表格
        tableIns=table.render({
            elem: '#classTable'   //渲染的目标对象
            ,url:'${pageContext.request.contextPath}/course/loadAllCourse.action' //数据接口
            ,title: '课程信息数据表'//数据导出来的标题
            ,toolbar:"#classToolBar"   //表格的工具条
            ,height:'full-200'
            ,cellMinWidth:100 //设置列的最小默认宽度
            ,page: true  //是否启用分页
            ,cols: [[   //列表数据
                {type: 'checkbox', fixed: 'left'}
                ,{field:'id', title:'ID',align:'center'}
                ,{field:'name', title:'课程名称',align:'center'}
                ,{fixed: 'right', title:'操作', toolbar: '#classBar', width:420,align:'center'}
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
                url:"${pageContext.request.contextPath}/course/fuzzyQueryAllCourse.action?"+params,
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
                layer.confirm('真的删除【'+data.name+'】这个课程吗', function(index){
                    //向服务端发送删除指令
                    $.post("${pageContext.request.contextPath}/course/deleteCourse.action",{id:data.id},function(res){
                        layer.msg(res.msg);
                        //刷新数据表格
                        tableIns.reload();
                    })
                });
            } else if(layEvent === 'edit'){ //编辑
                openUpdateclass(data);
            } else if(layEvent === 'uploadResource') {
                uploadResource(data);
            } else if(layEvent === 'showResourceList') {
                showResourceList(data);
            } else if(layEvent === 'homework') {
                homework(data);
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

        var url;
        var mainIndex;

        // 打开添加页面
        function openAddclass(){
            mainIndex=layer.open({
                type:1,
                title:'添加课程',
                content:$("#saveOrUpdateDiv"),
                area:['700px','400px'],
                success:function(index){
                    //清空表单数据
                    $("#dataFrm")[0].reset();
                    // 清空隐藏表单域的值
                    $('#hiddenId').val('');
                    url="${pageContext.request.contextPath}/course/addCourse.action";

                    // 加载课程分类
                    $("#sid").find("option").remove();
                    $('#sid').append(new Option("请选择分类", ""));

                    //    加载分类列表
                    $.get('${pageContext.request.contextPath}/courseSort/loadAllCourseSort.action', function (res) {
                        // 遍历填充数据
                        $.each(res.data, function (index, item) {
                            $('#sid').append(new Option(item.cname, item.id));// 下拉菜单里添加元素
                        });

                        // 更新select框
                        form.render("select");
                    })


                    // 老师表格
                    table.render({
                        elem: '#teacherTable'   //渲染的目标对象
                        ,url:'${pageContext.request.contextPath}/teacher/loadAllTeacher.action' //数据接口
                        ,height:'200'
                        ,cellMinWidth:100 //设置列的最小默认宽度
                        ,page: true  //是否启用分页
                        ,cols: [[   //列表数据
                            {type: 'checkbox', fixed: 'left'}
                            ,{field:'loginname', title:'教师帐号',align:'center'}
                            ,{field:'realname', title:'教师名称',align:'center'}
                        ]]
                    })

                    var params = '';
                    var active = {
                        getCheckData: function () { //获取选中数据
                            var checkStatus = table.checkStatus('teacherTable');
                            var data = checkStatus.data;
                            $.each(data,function(i,item){
                                if(i==0){
                                    params+="ids="+item.userid;
                                }else{
                                    params+="&ids="+item.userid;
                                }
                            });
                        }
                    }

                    $('#btn').on('click', function(){
                        var type = $(this).data('type');
                        active[type] ? active[type].call(this) : '';

                        var cname = $('#courseName').val();  // 获取课程名称
                        var sid = $('#sid').val();   // 获取分类id
                        $.post(url, {name: cname, sid: sid}, function(res) {
                            console.log(res.code);
                            var cid = res.code;   // 课程id
                            if(cid != null && cid > 0) {
                                params += '&cid=' + cid;
                                url = '${pageContext.request.contextPath}/teacherCourse/addTeacherCourse.action';
                                $.post(url, params, function(r) {
                                    layer.msg(r.msg);
                                    table.reload(tableIns);
                                    layer.close(mainIndex);
                                })
                            }
                        })
                    });
                }
            })
        }

        //打开修改页面
        function openUpdateclass(data){
            mainIndex=layer.open({
                type:1,
                title:'修改课程信息',
                content:$("#saveOrUpdateDiv"),
                area:['700px','400px'],
                success:function(index){
                    form.val("dataFrm",data);
                    url="${pageContext.request.contextPath}/course/updateCourse.action";

                    // 加载课程分类
                    $("#sid").find("option").remove();
                    $('#sid').append(new Option("请选择分类", ""));

                    //    加载分类列表
                    $.get('${pageContext.request.contextPath}/courseSort/loadAllCourseSort.action', function (res) {
                        // 遍历填充数据
                        $.each(res.data, function (index, item) {
                            $('#sid').append(new Option(item.cname, item.id));// 下拉菜单里添加元素
                        });

                        $('#sid').val(data.sid);
                        // 更新select框
                        form.render("select");
                    })


                    // 老师表格
                    table.render({
                        elem: '#teacherTable'   //渲染的目标对象
                        ,url:'${pageContext.request.contextPath}/teacherCourse/loadCourseTeacherList.action?cid=' + data.id //数据接口
                        ,height:'200'
                        ,cellMinWidth:100 //设置列的最小默认宽度
                        ,page: true  //是否启用分页
                        ,cols: [[   //列表数据
                            {type: 'checkbox', fixed: 'left'}
                            ,{field:'loginname', title:'教师帐号',align:'center'}
                            ,{field:'realname', title:'教师名称',align:'center'}
                        ]]
                    })

                    var params = '';
                    var active = {
                        getCheckData: function () { //获取选中数据
                            var checkStatus = table.checkStatus('teacherTable');
                            var data = checkStatus.data;
                            $.each(data,function(i,item){
                                if(i==0){
                                    params+="ids="+item.userid;
                                }else{
                                    params+="&ids="+item.userid;
                                }
                            });
                        }
                    }

                    $('#btn').on('click', function(){
                        var type = $(this).data('type');
                        active[type] ? active[type].call(this) : '';

                        var cname = $('#courseName').val();  // 获取课程名称
                        var sid = $('#sid').val();   // 获取分类id
                        var id = $('#hiddenId').val();
                        $.post(url, {name: cname, sid: sid, id: id}, function(res) {
                            if(res.code == 0) {
                                params += '&cid=' + data.id;
                                url = '${pageContext.request.contextPath}/teacherCourse/updateCourseTeacher.action';
                                $.post(url, params, function(r) {
                                    layer.msg(r.msg);
                                    table.reload(tableIns);
                                    layer.close(mainIndex);
                                })
                            }
                        })
                    });
                }
            });
        }


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

        //批量删除
        function deleteBatch(){
            //得到选中的数据行
            var checkStatus = table.checkStatus('classTable');
            var data = checkStatus.data;
            var params="";
            $.each(data,function(i,item){
                if(i==0){
                    params+="ids="+item.id;
                }else{
                    params+="&ids="+item.id;
                }
            });
            layer.confirm('真的删除选中的这些课程吗', function(index){
                //向服务端发送删除指令
                $.post("${pageContext.request.contextPath}/course/batchDeleteCourse.action",params,function(res){
                    layer.msg(res.msg);
                    //刷新数据 表格
                    tableIns.reload();
                })
            });
        }

        // 上传
        function uploadResource(data) {
            mainIndex = layer.open({
                type:1,
                title:'上传课件',
                content:$("#uploadResource"),
                area:['420px','230px'],
                success:function(index){
                    //清空
                    $('#resourceName').val('');

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
                                var name = $('#resourceName').val();

                                // 在数据库中保存资源路径
                                $.post('${pageContext.request.contextPath}/resource/addResource.action', {name: name, path: res.data.src, cId: data.id}, function(r) {
                                    layer.msg(r.msg);
                                })
                            }
                            layer.close(mainIndex);
                        }
                    })
                }
            })
        }

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
        function homework(data) {
            layer.open({
                type:1,
                title:'正在给【' + data.name + '】课程布置作业',
                content:$("#homework"),
                area:['700px','400px'],
                success:function(index){
                    // 建立富文本编辑器
                    // layedit.build('workContent');

                    //清空表单数据
                    $("#dataFrm2")[0].reset();
                    $('#cid').val(data.id);
                    url = '${pageContext.request.contextPath}/work/addWork.action';
                }
            })
        }

    //    编辑课程表
        function courseTable(data) {
            mainIndex = layer.open({
                type:1,
                title:'正在给【' + data.name + '】课程设置课程表',
                content:$("#courseTable"),
                area:['700px','400px'],
                success:function(index){
                    // //清空表单数据
                    $("#courseTableFrm")[0].reset();

                    $('#addBtn').on('click', function() {
                        // $('#cid').val(data.id);
                        url = '${pageContext.request.contextPath}/courseTable/addCourseTable.action';
                        var params = $("#courseTableFrm").serialize();
                        params += '&cid=' + data.id;
                        console.log(params);
                        $.post(url, params, function (res) {
                            layer.msg(res.msg);
                            // 关闭弹层
                            layer.close(mainIndex);
                        })
                    })
                }
            })
        }
    });
</script>
</body>
</html>
