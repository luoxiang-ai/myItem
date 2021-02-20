<%--
  Created by IntelliJ IDEA.
  User: luoxiang
  Date: 2020/11/27
  Time: 10:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>客户管理</title>
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
</head>
<body class="childrenBody">
<!-- 搜索条件开始 -->
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>查询条件</legend>
</fieldset>
<form class="layui-form" method="post" id="searchFrm">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">客户标题:</label>
            <div class="layui-input-inline">
                <input type="text" name="title"  autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">客户内容:</label>
            <div class="layui-input-inline">
                <input type="text" name="content"  autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">开始时间:</label>
            <div class="layui-input-inline">
                <input type="text" name="startTime" id="startTime" readonly="readonly"  autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">结束时间:</label>
            <div class="layui-input-inline">
                <input type="text" name="endTime"  id="endTime" readonly="readonly" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <button type="button" class="layui-btn layui-btn-normal  layui-icon layui-icon-search" id="doSearch">查询</button>
            <button type="reset" class="layui-btn layui-btn-warm  layui-icon layui-icon-refresh">重置</button>
        </div>
    </div>
</form>

<!-- 搜索条件结束 -->

<!-- 数据表格开始 -->
<table class="layui-hide" id="customerTable" lay-filter="customerTable"></table>
<div style="display: none;" id="customerToolBar">
    <button type="button" class="layui-btn layui-btn-sm" lay-event="batchMoveOut">批量移出</button>
</div>
<div id="customerBar" style="display: none;">
    <a class="layui-btn layui-btn-xs" lay-event="moveOutBlacklist">移出黑名单</a>
</div>
<!-- 数据表格结束 -->

<!-- 添加和修改的弹出层开始 -->
<div style="display: none;padding: 20px" id="saveOrUpdateDiv" >
    <form class="layui-form"  lay-filter="dataFrm" id="dataFrm">
        <div class="layui-form-item">
            <label class="layui-form-label">客户身份证:</label>
            <div class="layui-input-block">
                <input type="text" name="identity" placeholder="请输入客户身份证号" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">客户名称:</label>
            <div class="layui-input-block">
                <input type="text" name="custname" class="layui-input" placeholder="请输入客户名称">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">住址:</label>
            <div class="layui-input-block">
                <input type="text" name="address" class="layui-input" placeholder="请输入客户住址">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">电话号码:</label>
            <div class="layui-input-block">
                <input type="text" name="phone" class="layui-input" placeholder="请输入客户电话">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">工作职位:</label>
            <div class="layui-input-block">
                <input type="text" name="career" class="layui-input" placeholder="请输入客户职位">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">性别:</label>
            <div class="layui-input-block">
               <input type="radio" title="男" value="1" name="sex">
               <input type="radio" title="女" value="0" name="sex">
            </div>
        </div>
        <div class="layui-form-item" style="text-align: center;">
            <div class="layui-input-block">
                <button type="button" class="layui-btn layui-btn-normal layui-btn-sm layui-icon layui-icon-release" lay-filter="doSubmit" lay-submit="">提交</button>
                <button type="reset" id="dataFrmResetBtn" class="layui-btn layui-btn-warm layui-btn-sm layui-icon layui-icon-refresh" >重置</button>
            </div>
        </div>
    </form>

</div>
<!-- 添加和修改的弹出层结束 -->


<!-- 查看客户的div -->
<div id="viewCustomerDiv" style="padding: 10px;display: none;">
    <h2 id="view_title" align="center"></h2>
    <hr>
    <div style="text-align: right;">
        发布人:<span id="view_opername"></span>  <span style="display: inline-block;width: 20px" ></span>
        发布时间:<span id="view_createtime"></span>
    </div>
    <hr>
    <div id="view_content"></div>
</div>

<!-- 批量上传客户页面开始 -->
<div class="layui-upload" id="batchAddCustomer" style="display: none;">
    <button type="button" class="layui-btn layui-btn-normal" id="testList">选择多文件</button>
    <button type="button" class="layui-btn" id="testListAction">开始上传</button>
    <div class="layui-upload-list">
        <table class="layui-table">
            <thead>
                <tr>
                    <th lay-data="{width: 300}">文件名</th>
                    <th lay-data="{width: 120}">大小</th>
                    <th lay-data="{width: 100}">上传状态</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody id="demoList"></tbody>
        </table>
    </div>

</div>
<!-- 批量上传客户页面结束 -->

<script src="${pageContext.request.contextPath}/resources/layui/layui.js"></script>
<script type="text/javascript">
    var tableIns;
    layui.use([ 'jquery', 'layer', 'form', 'table','laydate'], function() {
        var $ = layui.jquery;
        var layer = layui.layer;
        var form = layui.form;
        var table = layui.table;
        var laydate=layui.laydate;

        //渲染时间
        laydate.render({
            elem:'#startTime',
            type:'datetime'
        });
        laydate.render({
            elem:'#endTime',
            type:'datetime'
        });

        //渲染数据表格
        tableIns=table.render({
            elem: '#customerTable'   //渲染的目标对象
            ,url:'${pageContext.request.contextPath}/customer/queryAllBlacklistUser.action' //数据接口
            ,title: '黑名单数据'//数据导出来的标题
            ,toolbar:"#customerToolBar"   //表格的工具条
            ,height:'full-200'
            ,cellMinWidth:100 //设置列的最小默认宽度
            ,page: true  //是否启用分页
            ,cols: [[   //列表数据
                {type: 'checkbox', fixed: 'left'}
                ,{field:'identity', title:'身份证号',align:'center', width: 180}
                ,{field:'custname', title:'客户名称',align:'center', width: 100}
                ,{field: 'sex', title: '性别', align: 'center', width: 80,sort: true, templet: function (d) {
                        return d.sex == 1 ? '男' : '女';
                    }}
                ,{field: 'address', title: '地址', align: 'center', width: 280}
                ,{field: 'phone', title: '电话号码', align: 'center', width: 120}
                ,{field: 'blacklist', title: '黑名单', align: 'center', width: 120, templet: function(d) {
                        return d.blacklist == 1 ? '<font color=red>是</font>' : '<font color=green>否</font>'
                    } }
                ,{field:'career', title:'工作',align:'center', width: 150}
                ,{field:'createtime', title:'创建时间',align:'center', width: 200}
                ,{fixed: 'right', title:'操作', toolbar: '#customerBar', width:140,align:'center'}
            ]]
            ,done: function(res, curr, count) {
                // res：接口返回的信息
                // curr：当前页码数
                // count：数据总记录数

                // 如果不是第一页，并且数据量为0的情况下，就返回上一页
                if(curr != 1 && res.data.length == 0) {
                    tableIns.reload({
                        page: {
                            curr: curr - 1
                        }
                    })
                }
            }
        })

        //模糊查询
        $("#doSearch").click(function(){
            var params=$("#searchFrm").serialize();
            tableIns.reload({
                url:"${pageContext.request.contextPath}/customer/loadAllCustomer.action?"+params,
                page:{curr:1}
            })
        });

        //监听头部工具栏事件
        table.on("toolbar(customerTable)",function(obj){
            switch(obj.event){
                case 'batchMoveOut':
                    batchMoveOut(obj);
                    break;
            };
        })

        //监听行工具事件
        table.on('tool(customerTable)', function(obj){
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）

            if(layEvent === 'moveOutBlacklist'){ // 移出黑名单
                moveOutBlacklist(data);
            }
        });

        // 批量移出黑名单
        function batchMoveOut(obj){
            var checkStatus = table.checkStatus(obj.config.id);
            var dataLength = checkStatus.data.length;
            console.log(checkStatus.data)
            if(dataLength == 0) {
                layer.msg('你还没有选中数据');
                return;
            } else {
                layer.confirm('是否将这批用户移出黑名单', function(index){
                    var params="";
                    $.each(checkStatus.data,function(i,item){
                        if(i==0){
                            params+="ids="+item.identity;
                        }else{
                            params+="&ids="+item.identity;
                        }
                    });
                    //向服务端发送删除指令
                    $.post("${pageContext.request.contextPath}/customer/batchMoveOut.action",params,function(res){
                        // 提示信息
                        layer.msg(res.msg);
                        //刷新数据 表格
                        tableIns.reload();
                    })
                });
            }
            console.log(checkStatus);
            var dataArray = checkStatus.data;
            for (var i = 0; i < dataLength; i++) {
                console.log(dataArray[i]);
            }
        }

        // 移出黑名单
        function moveOutBlacklist(data){
            $.post('${pageContext.request.contextPath}/customer/deleteBlacklist.action', {identity: data.identity}, function (obj) {
                // 提示信息
                layer.msg(obj.msg);
                //    重新刷新数据表格
                tableIns.reload();
            })
        }

    });
</script>
</body>
</html>
