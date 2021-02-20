<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>修改密码</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layui/css/layui.css" media="all" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/public.css" media="all" />
</head>
<body>
<form class="layui-form layui-row changePwd" id="dataFrm">
    <div class="layui-col-xs12 layui-col-sm6 layui-col-md6 layui-col-md-offset3">
        <div class="layui-input-block layui-red pwdTips">新密码必须两次输入一致才能提交</div>
        <div class="layui-form-item">
            <label class="layui-form-label">用户名</label>
            <div class="layui-input-block">
                <input type="hidden" name="userid" value="${sessionScope.sysUser.userid}">
                <input type="text" value="${sessionScope.sysUser.loginname}" disabled class="layui-input layui-disabled">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">旧密码</label>
            <div class="layui-input-block">
                <input type="password" name="pwd" placeholder="请输入旧密码" lay-verify="required" class="layui-input pwd">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">新密码</label>
            <div class="layui-input-block">
                <input type="password" name="newPwd" placeholder="请输入新密码" lay-verify="required" id="newPwd" class="layui-input pwd">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">确认密码</label>
            <div class="layui-input-block">
                <input type="password" value="" placeholder="请确认密码" lay-verify="required" id="determinePwd" class="layui-input pwd">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit="" lay-filter="changePwd">立即修改</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </div>
</form>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/layui/layui.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.4.1.js"></script>
<script type="text/javascript">
    layui.use(['jquery', 'form', 'layer', 'upload'], function () {
        var form = layui.form;
        var $ = layui.jquery;
        var layer = layui.layer;
        var upload = layui.upload;

        form.on('submit(changePwd)', function (obj) {
            var newPwd = $('#newPwd').val();
            var determinePwd = $('#determinePwd').val();
            var params = obj.field;
            if(newPwd != determinePwd) {
                layer.alert('两次密码不匹配');
                return false;
            }

            // 验证旧密码是否正确
            $.post('${pageContext.request.contextPath}/user/queryUserByUserIdAndPassword.action', params, function(res) {
                // 正确的情况
                if(res.code == 0) {

                    url = '${pageContext.request.contextPath}/user/updatePwd.action';
                    // 真正的修改密码
                    $.post(url, params, function (r) {
                        if(r.code == 0) {
                            layer.msg('密码修改成功，请稍后')
                            // 登出操作
                           setTimeout(function () {
                               window.parent.location.href = '${pageContext.request.contextPath}/logout.action';
                           }, 1000)
                        } else {
                            layer.alert(res.msg);
                            return false;
                        }
                    })
                } else {
                    layer.alert('旧密码不匹配');
                }
            })

            return false;
        })
    })
</script>
</body>
</html>
