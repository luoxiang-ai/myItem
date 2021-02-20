<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>个人资料</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <style>
        .head{
            width: 250px;
            height: 250px;
        }
        .preview{
            width: 250px;
            height: 250px;
            background: red;
            overflow: hidden;
        }

    </style>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layui/css/layui.css" media="all" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/public.css" media="all" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/jquery_ext/cropper/cropper.min.css">
</head>
<body>
<!-- 头像设置开始 -->
<div class="layui-row">
    <a class="layui-btn layui-col-md1 layui-col-md-offset10" style="margin-top: 20px" id="updateMyData">编辑</a>
</div>
<div class="layui-fluid">
    <div class="layui-row">
        <div class="layui-col-md3 layui-col-xs12 user_right">
            <div class="layui-upload-list">
                <img src="${pageContext.request.contextPath}/file/downloadShowFile.action?path=${sessionScope.sysUser.userface}" class="layui-upload-img layui-circle userFaceBtn userAvatar" id="userFace" class="userFace">
            </div>
        </div>
        <div class="layui-col-md9 layui-col-xs12">

            <div class="layui-row layui-col-space30" style="font-size: 20px;margin-top: 20px">
                <span class="layui-col-md5"><strong>帐号：</strong>${sessionScope.sysUser.loginname}</span>
                <span class="layui-col-md5"><strong>帐号级别：</strong>${sessionScope.sysUser.type == 1 ? "超级管理员" : "普通用户"}</span>
            </div>
            <div class="layui-row layui-col-space30" style="font-size: 20px">
                <span class="layui-col-md5"><strong>姓名：</strong>${sessionScope.sysUser.realname}</span>
                <span class="layui-col-md5"><strong>性别：</strong>${sessionScope.sysUser.sex == 1 ? "男" : "女"}</span>
            </div>
            <div class="layui-row layui-col-space30" style="font-size: 20px">
                <span class="layui-col-md5"><strong>手机号码：</strong>${sessionScope.sysUser.phone}</span>
                <span class="layui-col-md5"><strong>地址：</strong>${sessionScope.sysUser.address}</span>
            </div>
            <div class="layui-row layui-col-space30" style="font-size: 20px">
                <span class="layui-col-md5"><strong>身份证号码：</strong>${sessionScope.sysUser.identity}</span>
                <span class="layui-col-md5"><strong>职位：</strong>${sessionScope.sysUser.position}</span>
            </div>
        </div>
    </div>
</div>
<!-- 头像设置结束 -->


<!-- 个人信息修改开始 -->
<div class="layui-fluid">
    <form id="dataFrm" lay-filter="dataFrm" class="layui-form layui-row" style="display: none;margin-top: 20px">
        <div class="layui-col-md11 layui-col-xs12">
            <div class="layui-form-item">
                <label class="layui-form-label">姓名</label>
                <div class="layui-input-block">
                    <!-- 隐藏表单域，用户id -->
                    <input type="hidden" name="userid">
                    <input type="text" name="realname" placeholder="请输入真实姓名" lay-verify="required" class="layui-input realName">
                </div>
            </div>
            <div class="layui-form-item" pane="">
                <label class="layui-form-label">性别</label>
                <div class="layui-input-block userSex">
                    <input type="radio" name="sex" value="1" title="男">
                    <input type="radio" name="sex" value="0" title="女">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">手机号码</label>
                <div class="layui-input-block">
                    <input type="tel" name="phone" placeholder="请输入手机号码" lay-verify="phone" class="layui-input userPhone">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">身份证号</label>
                <div class="layui-input-block">
                    <input type="text" name="identity" placeholder="请输入身份证号码" lay-verify="identity" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">地址</label>
                <div class="layui-input-block">
                    <input type="text" name="address" placeholder="请输入地址" lay-verify="required" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-col-md4 layui-col-md-offset5">
                    <button class="layui-btn layui-btn-lg" lay-submit="" lay-filter="updateUser">保存</button>
                </div>
            </div>
        </div>
    </form>
</div>
<!-- 个人信息修改结束 -->

<!-- 头像裁剪区域开始 -->
<div id="cut" style="display: none;margin-top: 30px" class="cut layui-fluid">
    <div class="layui-row">
        <div class="head layui-col-md5 layui-col-md-offset1">
            <img src="" id="preFace">
        </div>
        <div class="preview layui-col-md5 layui-col-md-offset1"></div>
    </div>
    <div class="layui-row" style="margin-top: 40px">
        <button class="layui-btn layui-btn-normal layui-col-md2 layui-col-md-offset5" id="getCroppedCanvas">保存</button>
    </div>
</div>
<!-- 头像裁剪区域结束 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/layui/layui.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.4.1.js"></script>
<script src="${pageContext.request.contextPath}/resources/jquery_ext/cropper/cropper.min.js"></script>
<script type="text/javascript">
    var cropper;
    var mainIndex;
    var url;
    layui.use(['jquery', 'form', 'layer', 'upload'], function () {
        var form = layui.form;
        var $ = layui.jquery;
        var layer = layui.layer;
        var upload = layui.upload;

        $('#updateMyData').on('click', function () {
            layer.open({
                type:1,
                title:'修改个人信息',
                content:$("#dataFrm"),
                area:['340px','400px'],
                success: function () {
                    url = '${pageContext.request.contextPath}/user/queryUserByUserId.action'
                    $.post(url, {'userId': ${sessionScope.sysUser.userid}}, function(obj) {
                        form.val('dataFrm', obj);
                    })
                }
            })
        })


        form.on('submit(updateUser)', function (obj) {
            // 序列化表单数据
            var params=$("#dataFrm").serialize();
            url = '${pageContext.request.contextPath}/user/updateUser.action';
            $.post(url, params, function (res) {
                // 成功
                if(res.code == 0) {
                    layer.msg(res.msg + '，正在玩命更新中...');
                    // 1.5秒后刷新页面
                    setTimeout(function () {
                        window.location.reload();
                    }, 1500)
                } else {  // 失败
                    layer.msg(res.msg);
                }
            })

            return false;       // 禁止表单自动跳转
        })

        upload.render({
            elem: '#userFace',
            url: '${pageContext.request.contextPath}/file/uploadUserFace.action',  // 上传url
            accept: 'images',  // 设置只能上传图片类型
            auto: true,  // 设置自动上传
            field: 'mf',
            size: 512,  // 文件大小：512kb
            drag: true,  // 支持拖拽上传
            error: function () {
                layer.msg('上传失败');
            }
            ,done: function(data) {
                console.log(data);
                mainIndex = layer.open({
                    type:1,
                    title:'头像裁剪',
                    content:$("#cut"),
                    area:['800px','440px'],
                    success:function(index){
                        $('#preFace').attr('src', '${pageContext.request.contextPath}/file/downloadShowFile.action?path=' + data.data.src);
                        var img = document.getElementById('preFace');
                        cropper = new Cropper(img, {
                            aspectRatio: 1/1,   //
                            viewMode: 1, // 视图的模式
                            preview: '.preview', // 开启
                            dragMode: 'move',
                            guides: true,  // 是否显示裁剪的虚线
                            modal: true,   // 是否开启遮罩层，将未选中的区域，暗色处理
                            restore: true,
                            zoomable: true,    // 是否允许缩放图片
                            zoomOnTouch: true,   // 是否允许通过触摸的形式去放大缩小图片（移动端生效）
                            zoomOnWheel: true,    // 是否允许使用鼠标来控制图片大小
                            wheelZoomRatio: 0.2,    // 设置鼠标控制缩放的比例
                            cropBoxMovable: true,    // 是否可以移动裁剪框（如果设置成false，则鼠标控制图片移动来进行裁剪）
                        })
                    }
                    ,cancel: function(index, layero){
                        $('#preFace').attr('src', '');
                        $('.cropper-wrap-box > .cropper-canvas > .cropper-hide').attr('src', '');
                    }
                });
            }
        })

    })

    $('#getCroppedCanvas').on('click', function() {
        console.log(cropper.getCroppedCanvas().toDataURL('image/jpg'));
        $.post('${pageContext.request.contextPath}/user/updateUserFace.action', {croppedCanvas: cropper.getCroppedCanvas().toDataURL('image/jpeg')}, function (data) {
            // 提示信息
            layer.msg(data.msg);
            // 关闭弹层
            layer.close(mainIndex);
            // 重新刷新页面
            setTimeout(function () {
                // 页面刷新
                window.parent.location.reload();
            }, 1000);
        })
    })
</script>
</body>
</html>
