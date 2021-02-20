<%--
  Created by IntelliJ IDEA.
  User: luoxiang
  Date: 2021/1/5
  Time: 15:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layui/css/layui.css" media="all" />
<link href="${pageContext.request.contextPath}/resources/layui_ext/city-picker/city-picker.css" rel="stylesheet" />
<body>

<%-- 有效果 --%>
<div class="layui-inline">
    <label class="layui-form-label width_auto text-r" style="margin-top:2px">省市县：</label>
    <div class="layui-input-inline" style="width:400px">
        <input type="text" autocomplete="on" class="layui-input" id="city-picker" name="city-picker" readonly="readonly" data-toggle="city-picker" placeholder="请选择">
    </div>
</div>

<%--<div class="layui-fluid">--%>
<%--    <div class="layui-row">--%>
<%--        <div class="layui-col-md12">--%>
<%--            <label class="layui-form-label width_auto text-r" style="margin-top:2px">省市县：</label>--%>
<%--            <div class="layui-input-block">--%>
<%--                <input type="text" autocomplete="on" class="layui-input" id="city-picker" name="city-picker" readonly="readonly" data-toggle="city-picker" placeholder="请选择">--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>


<%--<div id="saveOrUpdateDiv" class="layui-fluid">--%>
<%--    <form class="layui-form"  lay-filter="dataFrm" id="dataFrm">--%>
<%--        <div class="layui-form-item">--%>
<%--            <div class="layui-col-md5">--%>
<%--                <label class="layui-form-label">客户身份证:</label>--%>
<%--                <div class="layui-input-block">--%>
<%--                    <input type="text" name="identity" placeholder="请输入客户身份证号" autocomplete="off" class="layui-input">--%>
<%--                </div>--%>
<%--            </div>--%>
<%--            <div class="layui-col-md5 layui-col-md-offset1">--%>
<%--                <label class="layui-form-label">客户名称:</label>--%>
<%--                <div class="layui-input-block">--%>
<%--                    <input type="text" name="custname" class="layui-input" placeholder="请输入客户名称">--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>

<%--        <div class="layui-form-item">--%>
<%--            <div class="layui-col-md8">--%>
<%--                <label class="layui-form-label">地址:</label>--%>
<%--                <div class="layui-input-block">--%>
<%--                    <input type="text" autocomplete="on" class="layui-input" id="city-picker" name="address" readonly="readonly" data-toggle="city-picker" placeholder="请选择">--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>


<%--        <div class="layui-form-item">--%>
<%--            <div class="layui-col-md4">--%>
<%--                <label class="layui-form-label">电话号码:</label>--%>
<%--                <div class="layui-input-block">--%>
<%--                    <input type="text" name="phone" class="layui-input" placeholder="请输入客户电话">--%>
<%--                </div>--%>
<%--            </div>--%>
<%--            <div class="layui-col-md4">--%>
<%--                <label class="layui-form-label">工作职位:</label>--%>
<%--                <div class="layui-input-block">--%>
<%--                    <input type="text" name="career" class="layui-input" placeholder="请输入客户职位">--%>
<%--                </div>--%>
<%--            </div>--%>
<%--            <div class="layui-col-md4">--%>
<%--                <label class="layui-form-label">性别:</label>--%>
<%--                <div class="layui-input-block">--%>
<%--                    <input type="radio" title="男" value="1" name="sex">--%>
<%--                    <input type="radio" title="女" value="0" name="sex">--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>

<%--        <div class="layui-form-item" style="text-align: center;">--%>
<%--            <div class="layui-input-block">--%>
<%--                <button type="button" class="layui-btn layui-btn-normal layui-btn-sm layui-icon layui-icon-release" lay-filter="doSubmit" lay-submit="">提交</button>--%>
<%--                <button type="reset" id="dataFrmResetBtn" class="layui-btn layui-btn-warm layui-btn-sm layui-icon layui-icon-refresh" >重置</button>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </form>--%>

<%--</div>--%>

<button id="test"></button>



<script src="${pageContext.request.contextPath}/resources/layui/layui.js"></script>
<script src="${pageContext.request.contextPath}/resources/layui_ext/city-picker/city-picker.data.js"></script>
<script>
    layui.extend({
        citypicker: '${pageContext.request.contextPath}/resources/layui_ext/city-picker/city-picker' // {/}的意思即代表采用自有路径，即不跟随 base 路径
    }).use(['jquery', 'table', 'citypicker'], function () {
        var $ = layui.$
            , table = layui.table
            , form = layui.form
            , cityPicker = layui.citypicker;

        var currentPicker = new cityPicker("#city-picker", {
            provincename:"provinceId",
            cityname:"cityId",
            districtname: "districtId",
            level: 'districtId',// 级别
        });

        <%--$('#test').on('click', openUpdateCustomer())--%>
        <%--function openUpdateCustomer(){--%>
        <%--    mainIndex=layer.open({--%>
        <%--        type:1,--%>
        <%--        title:'修改客户',--%>
        <%--        content:$("#saveOrUpdateDiv"),--%>
        <%--        area:['800px','450px'],--%>
        <%--        success:function(index){--%>
        <%--            &lt;%&ndash;editIndex=layedit.build('content'); //建立编辑器&ndash;%&gt;--%>
        <%--            &lt;%&ndash;form.val("dataFrm",data);&ndash;%&gt;--%>
        <%--            &lt;%&ndash;url="${pageContext.request.contextPath}/customer/updateCustomer.action";&ndash;%&gt;--%>

        <%--            // 设置初始值，如果不需要，则去掉下面这行代码--%>
        <%--            // currentPicker.setValue(data.address);--%>
        <%--        }--%>
        <%--    });--%>
        <%--}--%>

        <%--// 设置初始值，如果不需要，则去掉下面这行代码--%>
        <%--currentPicker.setValue("河南省/信阳市/新县");--%>
    });

</script>
</body>
</html>
