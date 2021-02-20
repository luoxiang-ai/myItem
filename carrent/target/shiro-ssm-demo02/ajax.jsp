<%--
  Created by IntelliJ IDEA.
  User: luoxiang
  Date: 2020/11/13
  Time: 9:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <input type="text" id="text">
    <input type="button" id="btn" value="提交">
    <script src="resources/js/jquery-3.3.1.min.js"></script>
    <script>
        $('#btn').click(function () {
            var o = $.ajax({
                <%--url: '${pageContext.request.contextPath}/test/login.action?name=' + $('#text').val(),--%>
                contentType: 'application/x-www-form-urlencoded',   // 发送数据到服务器时所使用的内容类型。默认是："application/x-www-form-urlencoded"
                type: "GET",    // 请求方式
                cache: true,    // 	布尔值，表示浏览器是否缓存被请求页面。默认是 true。
                scriptCharset: 'ISO8859-1',         // 规定请求的字符集。
                url: '${pageContext.request.contextPath}/test/login.action',
                async: true,  // 是否异步，默认为true
                data: {name: $('#text').val()},
                processData: false,       // 布尔值，规定通过请求发送的数据是否转换为查询字符串。默认是 true
                //dataType: 'json',       // 预期的服务器响应的数据类型，如果服务器响应的数据类型和设置的不一样，发送请求会失败
                success: function () {      // 请求成功调用
                    alert('请求成功了');
                },
                error: function () {
                    alert('请求失败了');
                },
                beforeSend: function () {
                    alert('发送请求前运行的函数');
                },
                complete: function () {         // 请求完成时运行的函数（在请求成功或失败之后均调用，即在 success 和 error 函数之后）
                    alert('请求完成时运行的函数');
                },
                timeout: 200     // 设置本地的请求超时时间（以毫秒计）。

            })
        })
    </script>
</body>
</html>
