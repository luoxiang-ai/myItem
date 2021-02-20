<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>资源上传管理</title>
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
        <div class="layui-inline layui-col-md4">
            <label class="layui-form-label">上传标题:</label>
            <div class="layui-input-inline">
                <input type="text" name="name"  autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-row" style="padding-bottom: 20px;">
        <div class="layui-col-md3 layui-col-md-offset5">
            <button type="button" class="layui-btn layui-btn-normal  layui-icon layui-icon-search" id="doSearch">查询</button>
            <button type="reset" class="layui-btn layui-btn-warm  layui-icon layui-icon-refresh">重置</button>
        </div>
    </div>
</form>
<!-- 搜索条件结束 -->

<!-- 数据表格开始 -->
<table class="layui-hide" id="resourceTable" lay-filter="resourceTable"></table>
<div style="display: none;" id="resourceToolBar">
    <button type="button" class="layui-btn layui-btn-sm" lay-event="add">上传</button>
</div>
<div  id="resourceBar" style="display: none;">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="downloadResource">下载</a>
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="recommend">推荐</a>
</div>
<!-- 数据表格结束 -->

<!-- 添加弹出层开始 -->
<div style="display: none;padding: 20px" id="addDiv" >
    <form class="layui-form">
        <div class="layui-form-item">
            <label class="layui-form-label">资源分类:</label>
            <div class="layui-input-block">
                <select name="sId" id="sid"></select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">资源标题:</label>
            <div class="layui-input-block">
                <input type="text" name="name" id="resourceName" placeholder="请输入资源上传标题" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <button type="button" class="layui-btn layui-btn-normal" id="selectFile">选择文件</button>
        <button type="button" class="layui-btn" id="upload">开始上传</button>
    </form>
</div>
<!-- 添加弹出层结束 -->

<%-- 修改弹出层开始 --%>
<div style="display: none;padding: 20px" id="updateDiv" >
    <form class="layui-form" lay-filter="dataFrm" id="dataFrm">
        <div class="layui-form-item">
            <label class="layui-form-label">资源分类:</label>
            <div class="layui-input-block">
                <select name="sId" id="sid2"></select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">资源标题:</label>
            <div class="layui-input-block">
                <input type="hidden" id="hiddenId" name="rId">
                <input type="text" name="name" id="resourceName2" placeholder="请输入资源上传标题" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item" style="text-align: center;">
            <div class="layui-input-block">
                <button type="button" class="layui-btn layui-btn-normal layui-btn-sm layui-icon layui-icon-release" lay-filter="doSubmit" lay-submit="">提交</button>
            </div>
        </div>
    </form>
</div>
<%-- 修改弹出层结束 --%>

<script src="${pageContext.request.contextPath}/resources/layui/layui.js"></script>
<script type="text/javascript">
    var tableIns;
    layui.use([ 'jquery', 'layer', 'form', 'table', 'upload'], function() {
        var $ = layui.jquery;
        var layer = layui.layer;
        var form = layui.form;
        var table = layui.table;
        var upload = layui.upload;


        //渲染数据表格
        tableIns=table.render({
            elem: '#resourceTable'   //渲染的目标对象
            ,url:'${pageContext.request.contextPath}/resource/loadAllResource.action' //数据接口
            ,title: '资源数据表'//数据导出来的标题
            ,toolbar:"#resourceToolBar"   //表格的工具条
            ,height:'full-200'
            ,cellMinWidth:100 //设置列的最小默认宽度
            ,page: true  //是否启用分页
            ,cols: [[   //列表数据
                {type: 'numbers', fixed: 'left'}
                ,{field:'id', title:'ID',align:'center', hide: true}
                ,{field:'name', title:'资源名称',align:'center'}
                ,{field:'flag', title:'是否推荐',align:'center', templet: function (d) {
                        return d.flag == 1 ? '<font color=green>是</font>' : '<font color=red>否</font>';
                    }}
                ,{field:'cname', title:'课程名称',align:'center'}
                ,{field:'sname', title:'分类名称',align:'center'}
                ,{fixed: 'right', title:'操作', toolbar: '#resourceBar', width:220,align:'center'}
            ]]
        })
        //模糊查询
        $("#doSearch").click(function(){
            var params=$("#searchFrm").serialize();
            tableIns.reload({
                url:"${pageContext.request.contextPath}/resource/fuzzyQueryResource.action?"+params,
                page:{curr:1}
            })
        });

        //监听头部工具栏事件
        table.on("toolbar(resourceTable)",function(obj){
            switch(obj.event){
                case 'add':
                    openAddResource();
                    break;
            };
        })
        //监听行工具事件
        table.on('tool(resourceTable)', function(obj){
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            if(layEvent === 'del'){ //删除
                layer.confirm('真的删除【'+data.name+'】这个资源吗', function(index){
                    //向服务端发送删除指令
                    $.post("${pageContext.request.contextPath}/resource/deleteResource.action",{id: data.rId, path: data.path},function(res){
                        layer.msg(res.msg);
                        //刷新数据 表格
                        tableIns.reload();
                    })
                });
            } else if(layEvent === 'edit'){ //编辑
                openUpdateResource(data);
            }else if(layEvent ==='downloadResource'){
                downloadResource(data);
            } else if(layEvent == 'recommend') {
                recommend(data);
            }
        });

        var url;
        var mainIndex;
        //打开添加页面
        function openAddResource(){
            mainIndex=layer.open({
                type:1,
                title:'添加资源上传',
                content:$("#addDiv"),
                area:['800px','400px'],
                success:function(index){
                    //清空表单数据
                    $("#dataFrm")[0].reset();
                    // 清空隐藏表单域的值
                    $('#hiddenId').val('');
                    url="${pageContext.request.contextPath}/resource/addResource.action";

                //    渲染下拉列表框
                    $("#sid").find("option").remove();
                    $('#sid').append(new Option("请选择分类", ""));

                    //    加载分类列表
                    $.get('${pageContext.request.contextPath}/resourceSort/loadAllResourceSort.action', function (res) {
                        // 遍历填充数据
                        $.each(res.data, function (index, item) {
                            $('#sid').append(new Option(item.name, item.id));// 下拉菜单里添加元素
                        });

                        // 更新select框
                        form.render("select");
                    })

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
                                var sort = $('#sid').val();

                                // 在数据库中保存资源路径
                                $.post('${pageContext.request.contextPath}/resource/addResource.action', {name: name, path: res.data.src, sId: sort}, function(r) {
                                    layer.msg(r.msg);
                                })
                            }
                            layer.close(mainIndex);
                        }
                    })
                },
                cancel: function () {
                    $("#sid").find("option").remove();
                }
            });
        }

        //打开修改页面
        function openUpdateResource(data){
            mainIndex=layer.open({
                type:1,
                title:'修改资源信息',
                content:$("#updateDiv"),
                area:['800px','400px'],
                success:function(index){
                    form.val("dataFrm",data);
                    url="${pageContext.request.contextPath}/resource/updateResource.action";

                    //    渲染下拉列表框
                    $("#sid2").find("option").remove();
                    $('#sid2').append(new Option("请选择分类", ""));

                    //    加载分类列表
                    $.get('${pageContext.request.contextPath}/resourceSort/loadAllResourceSort.action', function (res) {
                        // 遍历填充数据
                        $.each(res.data, function (index, item) {
                            $('#sid2').append(new Option(item.name, item.id));// 下拉菜单里添加元素
                        });

                        // select下拉框回显
                        $('#sid2').val(data.sId);

                        // 更新select框
                        form.render("select");
                    })
                }
            });
        }

        // 修改保存
        form.on("submit(doSubmit)",function(obj){
            //序列化表单数据
            var params=$("#dataFrm").serialize();
            console.log("sfsf" + params);
            $.post(url,params,function(obj){
                layer.msg(obj.msg);
                //关闭弹出层
                layer.close(mainIndex)
                //刷新数据 表格
                tableIns.reload();
            })
        });

        // 下载资料
        function downloadResource(data){
            window.location.href = '${pageContext.request.contextPath}/file/download.action?path=' + data.path + '&fileName=' + data.name;
        }

        // 推荐资源
        function recommend(data) {
            layer.confirm('是否将【' + data.name + '】资源推荐给大家', function() {
                $.post('${pageContext.request.contextPath}/resource/updateResource.action', {rId: data.rId, flag: 1}, function (res) {
                    if(res.code == 0) {
                        layer.msg('资源推荐成功');
                        tableIns.reload();
                    } else {
                        layer.msg('资源推荐失败');
                    }
                })
            })
        }

    });
</script>
</body>
</html>