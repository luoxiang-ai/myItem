<%--
  Created by IntelliJ IDEA.
  User: luoxiang
  Date: 2020/12/5
  Time: 20:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html style="height: 100%">
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layui/css/layui.css" media="all" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/public.css" media="all" />
</head>
<body style="height: 100%; margin: 0">
<!-- 搜索条件开始 -->
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>查询条件</legend>
</fieldset>
<form class="layui-form" method="post" id="searchFrm">
    <div class="layui-form-item">
        <div class="layui-inline">
            <div class="layui-input-inline">
                <input type="text" name="year" placeholder="请输入要查询的年份" autocomplete="off" id="year" class="layui-input">
            </div>
            <button type="button" class="layui-btn layui-btn-normal  layui-icon layui-icon-search" id="doSearch">查询</button>
        </div>
    </div>
</form>

<!-- 搜索条件结束 -->
<div id="container" style="height: 100%"></div>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/echarts/echarts.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/layui/layui.js"></script>
<script type="text/javascript">
    var $;
    layui.use(['jquery', 'laydate'], function () {
        var laydate = layui.laydate;
        $ = layui.jquery;

        var url = '${pageContext.request.contextPath}/stat/opernameYearGradeStatJson.action';

        laydate.render({
            elem: '#year',
            type: 'year',
            value: new Date()
        })

        $('#doSearch').click(function() {
            var year = $('#year').val();
            console.log(year);
            $.get(url, {year: year}, function (data) {
                getData(data);
            })
        })
    })

    $(function() {
        getData($('#year').val());
    })
    
    // 获取数据
    function getData(data) {
        var dom = document.getElementById("container");
        var myChart = echarts.init(dom);
        var app = {};
        option = null;
        option = {
            color: ['#3398DB'],
            tooltip: {
                trigger: 'axis',
                axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                    type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: [
                {
                    type: 'category',
                    data: data.name,
                    axisTick: {
                        alignWithLabel: true
                    }
                }
            ],
            yAxis: [
                {
                    type: 'value'
                }
            ],
            series: [
                {
                    name: '直接访问',
                    type: 'bar',
                    barWidth: '60%',
                    data: data.value
                }
            ]
        };

        if (option && typeof option === "object") {
            myChart.setOption(option, true);
        }
    }
</script>
</body>
</html>
