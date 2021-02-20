<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>意见提交</title>
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

<!-- 添加和修改的弹出层开始 -->
<form class="layui-form" lay-filter="dataFrm" id="dataFrm">
    <div class="layui-form-item">
        <label class="layui-form-label">系统建议:</label>
        <div class="layui-input-block">
            <textarea class="layui-textarea layui-hide" name="content" id="content"></textarea>
        </div>
    </div>
    <div class="layui-form-item" style="text-align: center;">
        <div class="layui-input-block">
            <button type="button" class="layui-btn layui-btn-normal layui-btn-sm layui-icon layui-icon-release" lay-filter="doSubmit" lay-submit="">提交</button>
        </div>
    </div>
</form>
<!-- 添加和修改的弹出层结束 -->


<script src="${pageContext.request.contextPath}/resources/layui/layui.js"></script>
<script type="text/javascript">
    var tableIns;
    var url;
    var editIndex;
    layui.use([ 'jquery', 'layer', 'form', 'layedit'], function() {
        var $ = layui.jquery;
        var layer = layui.layer;
        var form = layui.form;
        var layedit=layui.layedit;


        editIndex = layedit.build('content');

        //保存
        form.on("submit(doSubmit)",function(obj){
            layedit.sync(editIndex);//把富文本里面的数据同步到自己写的textarea里面
            //序列化表单数据
            var params=$("#dataFrm").serialize();
            url = '${pageContext.request.contextPath}/suggest/addSuggest.action';
            $.post(url,params,function(res){
                if(res.code == 0) {
                    layer.msg(res.msg + '，谢谢你的宝贵意见~');
                    layedit.setContent(editIndex,"");
                } else {
                    layer.msg(res.msg);
                }
            })
        });

    });
</script>
</body>
</html>