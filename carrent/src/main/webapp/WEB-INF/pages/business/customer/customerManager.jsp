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
    <script src="${pageContext.request.contextPath}/resources/layui_ext/city-picker/city-picker.data.js"></script>
    <link href="${pageContext.request.contextPath}/resources/layui_ext/city-picker/city-picker.css" rel="stylesheet" />
</head>
<body class="childrenBody">
<!-- 搜索条件开始 -->
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>查询条件</legend>
</fieldset>
<form class="layui-form" method="post" id="searchFrm">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">客户名称:</label>
            <div class="layui-input-inline">
                <input type="text" name="custname"  autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">身份证:</label>
            <div class="layui-input-inline">
                <input type="text" name="identity" lay-verify="number"  autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">工作:</label>
            <div class="layui-input-inline">
                <input type="text" name="career"  autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">地址:</label>
            <div class="layui-input-inline">
                <input type="text" name="address"  autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">电话号码:</label>
            <div class="layui-input-inline">
                <input type="text" name="phone" lay-verify="number"  autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">性别:</label>
            <div class="layui-input-inline">
                <input type="radio" value="1" title="男" name="sex"  autocomplete="off" class="layui-input">
                <input type="radio" value="0" title="女" name="sex"  autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-col-md-3 layui-col-md-offset5">
            <button type="button" class="layui-btn layui-btn-normal  layui-icon layui-icon-search" id="doSearch">查询</button>
            <button type="reset" class="layui-btn layui-btn-warm  layui-icon layui-icon-refresh">重置</button>
            <button type="button" class="layui-btn" id="doExport">导出</button>
        </div>
    </div>
</form>
<!-- 搜索条件结束 -->

<!-- 数据表格开始 -->
<table class="layui-hide" id="customerTable" lay-filter="customerTable"></table>
<div style="display: none;" id="customerToolBar">
    <button type="button" class="layui-btn layui-btn-sm" lay-event="add">增加</button>
    <button type="button" class="layui-btn layui-btn-danger layui-btn-sm" lay-event="deleteBatch">批量删除</button>
</div>
<div  id="customerBar" style="display: none;">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    <a class="layui-btn layui-btn-xs layui-btn-warm" lay-event="addBlacklist">移入黑名单</a>
</div>
<!-- 数据表格结束 -->

<!-- 添加和修改的弹出层开始 -->
<div style="display: none;padding: 20px" id="saveOrUpdateDiv" class="layui-fluid">
    <form class="layui-form"  lay-filter="dataFrm" id="dataFrm">
        <div class="layui-form-item">
            <div class="layui-col-md5">
                <label class="layui-form-label">客户身份证:</label>
                <div class="layui-input-block">
                    <input type="text" lay-verify="required|identity" name="identity" placeholder="请输入客户身份证号" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-col-md5 layui-col-md-offset1">
                <label class="layui-form-label">客户名称:</label>
                <div class="layui-input-block">
                    <input type="text" lay-verify="required" name="custname" class="layui-input" placeholder="请输入客户名称">
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-col-md11">
                <label class="layui-form-label">地址:</label>
                <div class="layui-input-block">
                    <input type="text" lay-verify="required" autocomplete="on" class="layui-input" id="city-picker" name="address" readonly="readonly" data-toggle="city-picker" placeholder="请选择">
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-col-md4">
                <label class="layui-form-label">电话号码:</label>
                <div class="layui-input-block">
                    <input type="text" lay-verify="required|phone" name="phone" class="layui-input" placeholder="请输入客户电话">
                </div>
            </div>
            <div class="layui-col-md4">
                <label class="layui-form-label">工作职位:</label>
                <div class="layui-input-block">
                    <input type="text" name="career" class="layui-input" placeholder="请输入客户职位">
                </div>
            </div>
            <div class="layui-col-md4">
                <label class="layui-form-label">性别:</label>
                <div class="layui-input-block">
                    <input type="radio" title="男" value="1" name="sex">
                    <input type="radio" title="女" value="0" name="sex">
                </div>
            </div>
        </div>

        <div class="layui-form-item" style="margin-top: 40px">
            <button type="button" class="layui-btn layui-btn-normal layui-btn-sm layui-icon layui-icon-release layui-col-md1 layui-col-md-offset5" lay-filter="doSubmit" lay-submit="">提交</button>
            <button type="reset" id="dataFrmResetBtn" class="layui-btn layui-btn-warm layui-btn-sm layui-icon layui-icon-refresh layui-col-md1" >重置</button>
        </div>
    </form>
</div>
<!-- 添加和修改的弹出层结束 -->

<script src="${pageContext.request.contextPath}/resources/layui/layui.js"></script>
<script type="text/javascript">
    var tableIns;
    layui.extend({
        citypicker: '${pageContext.request.contextPath}/resources/layui_ext/city-picker/city-picker' // {/}的意思即代表采用自有路径，即不跟随 base 路径
    }).use([ 'jquery', 'layer', 'form', 'table','laydate','layedit' ,'upload', 'citypicker'], function() {
        var $ = layui.jquery;
        var layer = layui.layer;
        var form = layui.form;
        var table = layui.table;
        var laydate=layui.laydate;
        var layedit=layui.layedit;
        var upload = layui.upload;
        var cityPicker = layui.citypicker;

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
            ,url:'${pageContext.request.contextPath}/customer/loadAllCustomer.action' //数据接口
            ,title: '客户数据表'//数据导出来的标题
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
                ,{fixed: 'right', title:'操作', toolbar: '#customerBar', width:200,align:'center'}
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
                case 'add':
                    openAddCustomer();
                    break;
                case 'deleteBatch':
                    deleteBatch();
                    break;
            };
        })

        $("#doExport").click(function(){
            var params=$("#searchFrm").serialize();
            window.location.href="${pageContext.request.contextPath}/stat/exportCustomer.action?"+params;
        });

        //监听行工具事件
        table.on('tool(customerTable)', function(obj){
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）

            if(layEvent === 'del'){ //删除
                layer.confirm('真的删除【'+data.custname+'】这个客户吗', function(index){
                    //向服务端发送删除指令
                    $.post("${pageContext.request.contextPath}/customer/deleteCustomer.action",{identity:data.identity},function(res){
                        layer.msg(res.msg);
                        //刷新数据表格
                        tableIns.reload();
                    })
                });
            } else if(layEvent === 'edit'){ //编辑
                openUpdateCustomer(data);
            } else if (layEvent === 'addBlacklist') {
                addBlacklist(data);
            }
        });

        // 添加到黑名单
        function addBlacklist(data) {
            console.log(data);
            // 判断该用户是否已经在黑名单中，如果在则提示用户不用重复添加
            if(data.blacklist == "1") {
                layer.msg('该用户已经在黑名单中~');
                return;
            }

            // 提示用户当前的操作是什么
            layer.confirm('是否将【' + data.custname + '】移入黑名单', function (index) {
                $.post('${pageContext.request.contextPath}/customer/addBlacklist.action', {identity: data.identity}, function (data) {
                    // 提示信息
                    layer.msg(data.msg);
                    //    重新刷新数据表格
                    tableIns.reload();
                })
            })
        }

        var url;
        var mainIndex;

        // 打开添加页面
        function openAddCustomer(){
            mainIndex=layer.open({
                type:1,
                title:'添加客户',
                content:$("#saveOrUpdateDiv"),
                area:['900px','380px'],
                success:function(index){
                    //清空表单数据
                    $("#dataFrm")[0].reset();
                    url="${pageContext.request.contextPath}/customer/addCustomer.action";
                }
            })
        }

        var currentPicker = new cityPicker("#city-picker", {
            provincename:"provinceId",
            cityname:"cityId",
            districtname: "districtId",
            level: 'districtId',// 级别
        });

        //打开修改页面
        function openUpdateCustomer(data){
            mainIndex=layer.open({
                type:1,
                title:'修改客户信息',
                content:$("#saveOrUpdateDiv"),
                area:['900px','380px'],
                success:function(index){
                    form.val("dataFrm",data);
                    url="${pageContext.request.contextPath}/customer/updateCustomer.action";

                    // 地址设置初始值，如果不需要，则去掉下面这行代码
                    currentPicker.setValue(data.address);
                },
                cancel: function(){
                    currentPicker.setValue('');
                }
            });
        }
        //保存
        form.on("submit(doSubmit)",function(obj){
            //序列化表单数据
            var params=$("#dataFrm").serialize();
            $.post(url,params,function(obj){
                layer.msg(obj.msg);
                //关闭弹出层
                layer.close(mainIndex);
                //刷新数据 表格
                tableIns.reload();
            })
        });

        //批量删除
        function deleteBatch(){
            //得到选中的数据行
            var checkStatus = table.checkStatus('customerTable');
            var data = checkStatus.data;
            var params="";
            $.each(data,function(i,item){
                if(i==0){
                    params+="ids="+item.identity;
                }else{
                    params+="&ids="+item.identity;
                }
            });
            layer.confirm('真的删除选中的这些客户吗', function(index){
                //向服务端发送删除指令
                $.post("${pageContext.request.contextPath}/customer/batchDeleteCustomer.action",params,function(res){
                    layer.msg(res.msg);
                    //刷新数据 表格
                    tableIns.reload();
                })
            });
        }
    });
</script>
</body>
</html>
