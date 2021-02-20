<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>资料管理</title>
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
            <label class="layui-form-label">资料名称:</label>
            <div class="layui-input-inline">
                <input type="text" name="name" autocomplete="off" class="layui-input">
            </div>
        </div>
<%--        <div class="layui-col-md4">--%>
<%--            <label class="layui-form-label">上传用户:</label>--%>
<%--            <div class="layui-input-inline">--%>
<%--                <input type="text" name="identity"  autocomplete="off" class="layui-input">--%>
<%--            </div>--%>
<%--        </div>--%>
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
<div  id="classBar" style="display: none;">
    <a class="layui-btn layui-btn-xs" lay-event="download">下载</a>
</div>
<!-- 数据表格结束 -->

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
            ,url:'${pageContext.request.contextPath}/resource/loadAllResource.action' //数据接口
            ,title: '资料信息数据表'//数据导出来的标题
            ,height:'full-200'
            ,cellMinWidth:100 //设置列的最小默认宽度
            ,page: true  //是否启用分页
            ,cols: [[   //列表数据
                {type: 'numbers', title: '编号', fixed: 'left'}
                ,{field:'rId', title:'ID',align:'center'}
                ,{field:'name', title:'资料名',align:'center'}
                ,{field:'cname', title:'课程名称',align:'center'}
                ,{field:'sname', title:'分类名称',align:'center'}
                ,{fixed: 'right', title:'操作', toolbar: '#classBar', width:200,align:'center'}
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

        table.on('tool(classTable)', function(obj){
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            if(layEvent === 'download') { //下载
                download(data);
            }
        });

        //模糊查询
        $("#doSearch").click(function(){
            var params=$("#searchFrm").serialize();
            tableIns.reload({
                url:"${pageContext.request.contextPath}/resource/fuzzyQueryResource.action?"+params,
                page:{curr:1}
            })
        });

        var url;
        var mainIndex;

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

        function download(data) {
            console.log(data)
            // 文件下载不能使用异步请求
            <%--$.get('${pageContext.request.contextPath}/file/download.action', {path: data.path})--%>
            window.location.href = '${pageContext.request.contextPath}/file/download.action?path=' + data.path + '&fileName=' + data.name;
        }
    });
</script>
</body>
</html>
