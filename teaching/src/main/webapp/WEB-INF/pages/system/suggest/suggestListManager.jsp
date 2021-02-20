<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>建议管理</title>
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

<!-- 数据表格开始 -->
<h2 style="text-align: center;padding: 10px;">用户建议数据</h2>
<table class="layui-hide" id="suggestTable" lay-filter="suggestTable"></table>
<hr style="margin: 50px 0 50px 0;">
<h2 style="text-align: center;padding: 10px;">加星建议数据</h2>
<table class="layui-hide" id="importantSuggestTable" lay-filter="suggestTable"></table>

<div id="suggestBar1" style="display: none;">
    <a class="layui-btn layui-btn-xs" lay-event="add">加星</a>
</div>

<div id="suggestBar2" style="display: none;">
    <a class="layui-btn layui-btn-xs" lay-event="move">移除</a>
</div>
<!-- 数据表格结束 -->


<script src="${pageContext.request.contextPath}/resources/layui/layui.js"></script>
<script type="text/javascript">
    var tableIns;
    layui.use(['jquery', 'layer','table'], function() {
        var $ = layui.jquery;
        var layer = layui.layer;
        var table = layui.table;

        dataTable('${pageContext.request.contextPath}/suggest/loadAllSuggest.action?flag=0', '#suggestTable','#suggestBar1');
        dataTable('${pageContext.request.contextPath}/suggest/loadAllSuggest.action?flag=1', '#importantSuggestTable','#suggestBar2');

        function dataTable(url, targetObj, toolId) {
            //渲染数据表格
            tableIns=table.render({
                elem: targetObj   //渲染的目标对象
                ,url: url //数据接口
                ,title: '建议信息数据表'//数据导出来的标题
                ,height:'full-200'
                ,cellMinWidth:100 //设置列的最小默认宽度
                ,page: true  //是否启用分页
                ,cols: [[   //列表数据
                    {type: 'numbers', fixed: 'left', title: '编号'}
                    ,{field:'content', title:'建议内容',align:'center'}
                    ,{fixed: 'right', title: '操作', toolbar: toolId, align: 'center', width: 120}
                ]]
            })
        }

        //监听行工具事件
        table.on('tool(suggestTable)', function(obj){
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            if(layEvent == 'add') {
                url = '${pageContext.request.contextPath}/suggest/starred.action';
                $.post(url, {id: data.id ,flag: 1},function(res) {
                    if(res.code == 0) {
                        layer.msg(res.msg);
                        // 刷新数据表格
                        table.reload('suggestTable');
                        table.reload('importantSuggestTable');
                    } else {
                        layer.msg(res.msg);
                    }
                })
            } else if(layEvent == 'move') {
                layer.confirm('确定将该加星建议移出？', function() {
                    url = '${pageContext.request.contextPath}/suggest/starred.action';
                    $.post(url, {id: data.id ,flag: 0},function(res) {
                        if(res.code == 0) {
                            layer.msg('移出成功');
                            // 刷新数据表格
                            table.reload('suggestTable');
                            table.reload('importantSuggestTable');
                        } else {
                            layer.msg('移出失败');
                        }
                    })
                })
            }
        });

    });
</script>
</body>
</html>
