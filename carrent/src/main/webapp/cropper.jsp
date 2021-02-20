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
    <div>
        <h2>
            <textarea></textarea>
        </h2>
    </div>
<div class="layui-form-item">
    <label class="layui-form-label"><i style="color: red">*</i>产品图片:</label>
    <input type="hidden" name="productImg" id="productImg"/>
    <div class="layui-upload">
        <button type="button" class="layui-btn" id="productImgButton">上传</button>
        <div class="layui-upload-list">
            <img class="layui-upload-img" id="productImgImg">
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/resources/layui/layui.js"></script>
<%--<script src="${pageContext.request.contextPath}/resources/layui_ext/cropper/cropper.js"></script>--%>
<%--<script src="${pageContext.request.contextPath}/resources/layui_ext/cropper/croppers.js"></script>--%>
<script>
    layui.extend({
        easyCropper: '${pageContext.request.contextPath}/resources/layui_ext/easyCropper/easyCropper' //layui自定义layui组件目录
    }).use(['easyCropper'], function(){

        var easyCropper = layui.easyCropper;
        //创建一个图片裁剪上传组件
        var productImgCropper = easyCropper.render({
            elem: '#productImgButton'
            ,saveW:280     //保存宽度
            ,saveH:160     //保存高度
            ,mark:7/4   //选取比例
            ,area:'900px'  //弹窗宽度
            ,url: '${pageContext.request.contextPath}/file/uploadUserFace.action'  //图片上传接口返回和（layui 的upload 模块）返回的JOSN一样
            ,fixed: 'mf'
            ,done: function(url){ //上传完毕回调
                $("#productImg").val(url);
                $("#productImgImg").attr('src',url);
            }
        });
    })
</script>
</body>
</html>
