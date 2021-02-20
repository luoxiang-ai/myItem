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
    <title>头像截取上传demo</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layui/css/layui.css" media="all" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/public.css" media="all" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layui_ext/easyCropper/cropper.css" media="all" />
</head>
<body>

<img src="${pageContext.request.contextPath}/file/downloadShowFile.action?path=/default/defaultUserFaceImg.jpg" id="preFace">
<hr>

<div class="layui-form-item">
    <label class="layui-form-label">头像</label>
    <div class="layui-input-inline">
        <input type="text" name="head" lay-verify="required" id="inputimgurl" placeholder="图片地址" class="layui-input">
    </div>
    <div class="layui-input-inline">
        <div class="layui-upload-list" style="margin:0">
            <img src="123.jpg" id="srcimgurl" class="layui-upload-img">
        </div>
    </div>
    <div class="layui-input-inline layui-btn-container" style="width: auto;">
        <button class="layui-btn layui-btn-primary" id="editimg">修改图片</button >
    </div>
    <div class="layui-form-mid layui-word-aux">头像的尺寸限定150x150px,大小在50kb以内</div>
</div>

<script src="${pageContext.request.contextPath}/resources/layui/layui.js"></script>
<%--<script src="${pageContext.request.contextPath}/resources/layui_ext/cropper/cropper.js"></script>--%>
<%--<script src="${pageContext.request.contextPath}/resources/layui_ext/cropper/croppers.js"></script>--%>
<script>
    layui.extend({
        croppers: '${pageContext.request.contextPath}/resources/layui_ext/cropper/cropper' //layui自定义layui组件目录
    }).use(['form','croppers'], function () {
        var $ = layui.jquery
            ,form = layui.form
            ,croppers = layui.croppers
            ,layer= layui.layer;

        //创建一个头像上传组件
        croppers.render({
            elem: '#editimg'
            ,saveW:150     //保存宽度
            ,saveH:150
            ,mark:1/1    //选取比例
            ,area:'900px'  //弹窗宽度
            ,url: '${pageContext.request.contextPath}/file/uploadUserFace.action'  //图片上传接口返回和（layui 的upload 模块）返回的JOSN一样
            ,fixed: 'mf'
            ,done: function(url){ //上传完毕回调
                $("#inputimgurl").val(url);
                $("#srcimgurl").attr('src',url);
            }
        });

    });
</script>
</body>
</html>
