<%--
  Created by IntelliJ IDEA.
  User: luoxiang
  Date: 2020/12/11
  Time: 12:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>上传文件进度条</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layui/css/layui.css" media="all" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/public.css" media="all" />
</head>
<body>
<div class="layui-progress layui-progress-big" lay-filter="demo" lay-showPercent="true">
    <div class="layui-progress-bar" lay-percent="0%"></div>
</div>

<div class="layui-upload">
    <button type="button" class="layui-btn layui-btn-normal" id="test8">选择文件</button>
    <button type="button" class="layui-btn" id="test9">开始上传</button>
</div>

<script src="${pageContext.request.contextPath}/resources/layui/layui.js"></script>
<script>
    layui.use(['jquery', 'upload', 'element', 'layer'], function () {
        var $ = layui.jquery;
        var upload = layui.upload;
        var element = layui.element;
        var layer = layui.layer;

        //选完文件后不自动上传
        upload.render({
            elem: '#test8'
            ,url: '${pageContext.request.contextPath}/file/upload.action' //改成您自己的上传接口
            ,auto: false
            ,progress: function(n, elem){
                alert();
                var percent = n + '%' //获取进度百分比
                element.progress('demo', percent); //可配合 layui 进度条元素使用

                //以下系 layui 2.5.6 新增
                console.log('好子'); //得到当前触发的元素 DOM 对象。可通过该元素定义的属性值匹配到对应的进度条。
                alert();
            }
            ,field: 'mf'
            ,accept: 'file'
            ,bindAction: '#test9'
            ,done: function(res){
                layer.msg('上传成功');
                console.log(res)
            }
        });
    })
</script>
</body>
</html>
