<%--
  Created by IntelliJ IDEA.
  User: luoxiang
  Date: 2020/12/11
  Time: 12:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/jquery_ext/cropper/cropper.min.css">
</head>
<body style="margin-top: 50px;">
<div class="box">
    <img src="${pageContext.request.contextPath}/resources/images/userface3.jpg" id="image" alt="图片失效">
</div>
<div class="small" style="width: 200px;height: 200px;overflow: hidden;"></div>
<hr>
<button id="reset">重置</button>
<button id="clear">清除</button>
<button id="replace">替换</button>
<button id="enable">解锁</button>
<button id="disable">锁定</button>
<button id="destory">销毁</button>

<button id="moveX">移动x</button>
<button id="moveY">移动y</button>
<button id="moveToX">移动到x</button>
<button id="moveToY">移动到y</button>

<button id="zoom">放大</button>
<button id="shrink">缩小</button>
<button id="zoomTo">放大到</button>
<button id="shrinkTo">缩小到</button>

<button id="rotateL">逆时针旋转</button>
<button id="rotateR">顺时针旋转</button>
<button id="rotateLTo">逆时针旋转到</button>
<button id="rotateRTo">顺时针旋转到</button>

<button id="getData">获取裁剪框数据</button>
<button id="setData">设置裁剪框数据</button>

<hr>
<button id="getCroppedCanvas">获取裁剪后的图片</button>

<script src="${pageContext.request.contextPath}/resources/js/jquery-3.4.1.js"></script>
<script src="${pageContext.request.contextPath}/resources/jquery_ext/cropper/cropper.min.js"></script>
<script>
    var img = document.getElementById('image');

    var cropper = new Cropper(img, {
        aspectRatio: 1/1,   //
        viewMode: 0, // 视图的模式
        preview: '.small', // 开启
        dragMode: 'move',
        guides: true,  // 是否显示裁剪的虚线
        modal: true,   // 是否开启遮罩层，将未选中的区域，暗色处理
        restore: true,
        zoomable: true,    // 是否允许缩放图片
        zoomOnTouch: true,   // 是否允许通过触摸的形式去放大缩小图片（移动端生效）
        zoomOnWheel: true,    // 是否允许使用鼠标来控制图片大小
        wheelZommRatio: 0.8,    // 设置鼠标控制缩放的比例
        cropBoxMovable: true,    // 是否可以移动裁剪框（如果设置成false，则鼠标控制图片移动来进行裁剪）
        // minContainerWidth: 0,
        // minContainerHeight: 500,
        // minCropBoxWidth: 0,
        // minCropBoxHeight: 100
    })

    // 重置剪裁区域的图片到初始状态
    $('#reset').on('click', function() {
        cropper.reset();
    })

    // 清空剪裁区域
    $('#clear').on('click', function() {
        cropper.clear();
    })

    // 替换图片
    $('#replace').on('click', function() {
        cropper.replace('img/bg2.jpg', false);
    })

    // 使cropper可用（使锁定的裁剪框解锁）
    $('#enable').on('click', function() {
        cropper.enable();
    })

    // 冻结cropper（锁定裁剪框）
    $('#disable').on('click', function() {
        cropper.disable();
    })

    // 有问题
    $('#destory').on('click', function() {
        cropper.destory();
    })

    // 放大
    $('#zoom').on('click', function() {
        cropper.zoom(0.1);
    })

    // 缩小
    $('#shrink').on('click', function() {
        cropper.zoom(-0.1);
    })

    // 放大到
    $('#zoomTo').on('click', function() {
        cropper.zoomTo(2);
    })

    // 缩小到
    $('#shrinkTo').on('click', function() {
        cropper.zoomTo(0.5);
    })

    // 逆时针旋转
    $('#rotateL').on('click', function() {
        cropper.rotate(-10);
    })

    // 顺时针旋转
    $('#rotateR').on('click', function() {
        cropper.rotate(10);
    })

    // 逆时针旋转到
    $('#rotateLTo').on('click', function() {
        cropper.rotateTo(-90);
    })

    // 顺时针旋转到
    $('#rotateRTo').on('click', function() {
        cropper.rotateTo(90);
    })

    // 获取裁剪框数据
    $('#getData').on('click', function() {
        console.log(cropper.getData());
    })

    // 设置裁剪框数据
    $('#setData').on('click', function() {
        cropper.setData({width: 50, height: 50});
    })

    // 获取裁剪后的图片（重要）
    $('#getCroppedCanvas').on('click', function() {
        console.log(cropper.getCroppedCanvas().toDataURL('image/jpeg'));
    })
</script>
</body>
</html>