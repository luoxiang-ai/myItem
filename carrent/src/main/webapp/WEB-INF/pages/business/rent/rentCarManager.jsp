<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>出租管理</title>
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
            <label class="layui-form-label">身份证号:</label>
            <div class="layui-input-inline">
                <input type="text" name="identity" placeholder="请输入身份证号" autocomplete="off" id="identity" class="layui-input">
            </div>
            <button type="button" class="layui-btn layui-btn-normal  layui-icon layui-icon-search" id="doSearch">查询</button>
        </div>
    </div>
</form>

<!-- 搜索条件结束 -->

<!-- 数据表格开始 -->
<div id="content" style="display: none">
    <table id="rentTable" lay-filter="rentTable"></table>
    <div  id="rentBar" style="display: none;">
        <a class="layui-btn layui-btn-xs" lay-event="rentCar">出租汽车</a>
        <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="viewBigImg">查看大图</a>
    </div>
</div>
<!-- 数据表格结束 -->

<!-- 添加和修改的弹出层开始 -->
<div style="display: none;padding: 20px" id="saveOrUpdateDiv" >
    <form class="layui-form"  lay-filter="dataFrm" id="dataFrm">
        <div class="layui-form-item">
            <label class="layui-form-label">起租时间：</label>
            <div class="layui-input-inline">
                <input type="text" name="begindate" id="startTime" class="layui-input">
            </div>
            <label class="layui-form-label">归还时间：</label>
            <div class="layui-input-inline">
                <input type="text" name="returndate" id="endTime" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">出租单号：</label>
            <div class="layui-input-block">
                <input type="text" readonly="readonly" class="layui-input" name="rentid" placeholder="请输入出租单号">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">身份证号：</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" readonly="readonly" name="identity" placeholder="请输入身份证号">
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">出租价格：</label>
                <div class="layui-input-inline">
                    <input type="text" name="price" class="layui-input" placeholder="请输入出租的价格">
                </div>

                <label class="layui-form-label">车牌号：</label>
                <div class="layui-input-inline">
                    <input type="text" name="carnumber" placeholder="请输入车牌号" readonly="readonly" class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">操作员</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" readonly="readonly" name="opername" placeholder="请输入操作员">
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


<!-- 查看出租的div -->
<div id="viewRentDiv" style="padding: 10px;display: none;">
    <h2 id="view_title" align="center"></h2>
    <hr>
    <div style="text-align: right;">
        发布人:<span id="view_opername"></span>  <span style="display: inline-block;width: 20px" ></span>
        发布时间:<span id="view_createtime"></span>
    </div>
    <hr>
    <div id="view_content"></div>
</div>

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
        function initCarData() {
            tableIns=table.render({
                elem: '#rentTable'   //渲染的目标对象
                ,url:'${pageContext.request.contextPath}/car/loadAllCar.action' //数据接口
                ,title: '用户数据表'//数据导出来的标题
                ,height:'full-200'
                ,cellMinWidth:100 //设置列的最小默认宽度
                ,page: true  //是否启用分页
                ,cols: [[   //列表数据
                    {type: 'numbers', fixed: 'left', title: '序号'}        //  // 设置列类型（序号）
                    ,{field:'carnumber', title:'车牌号',align:'center'}
                    ,{field:'cartype', title:'类型',align:'center'}
                    ,{field: 'deposit', title: '押金', align: 'center'}
                    ,{field: 'price', title: '价格', align: 'center'}
                    ,{field: 'rentprice', title: '租金', align: 'center'}
                    ,{filed: 'isrenting', title: '是否出租', align: 'center', templet: function (d) {
                        return d.isrenting == 1 ? '<font color=red>已出租</font>' : '<font color=green>未出租</font>';
                     }}
                    ,{field: 'description', title: '描述', align: 'center'}
                    ,{field:'carimg', title:'缩略图',align:'center', templet: function(d) {
                            return '<img width=40 height=30 src=${pageContext.request.contextPath}/file/downloadShowFile.action?path=' + d.carimg + '>';
                        }}
                    ,{field:'createtime', title:'发布时间',align:'center'}
                    ,{fixed:'right',title:'操作',toolbar:'#rentBar',align:'center',width:160}
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
        }

        //模糊查询
        $("#doSearch").click(function(){
            var params=$("#searchFrm").serialize();
            $.post('${pageContext.request.contextPath}/rent/checkCustomerExist.action', params, function (obj) {
                if(obj.code == 0) {
                    $('#content').show();
                    // 初始化数据
                    initCarData();
                } else {
                    $('#content').hide();
                    layer.msg('客户身份证号不存在,请更正后再查询')
                }
            })
        });

        //监听行工具事件
        table.on('tool(rentTable)', function(obj){
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            if(layEvent === 'del'){ //删除
                layer.confirm('真的删除【'+data.rentnumber+'】这个出租吗', function(index){
                    //向服务端发送删除指令
                    $.post("${pageContext.request.contextPath}/rent/deleteRent.action",{rentnumber:data.rentnumber},function(res){
                        layer.msg(res.msg);
                        //刷新数据 表格
                        tableIns.reload();
                    })
                });
            } else if(layEvent === 'rentCar'){ //编辑
                openRentCar(data);
            }else if(layEvent==='viewRent'){
                viewRent(data);
            }
        });

        var url;
        var mainIndex;
        //打开添加页面
        function openAddRent(){
            mainIndex=layer.open({
                type:1,
                title:'添加出租',
                content:$("#saveOrUpdateDiv"),
                area:['1100px','500px'],
                success:function(index){
                    $('#showRentImg').attr('src', '${pageContext.request.contextPath}/file/downloadShowFile.action?path=default/defaultRentImg.png');
                    $('#rentimg').val('default/defaultRentImg.png');
                    //清空表单数据
                    $("#dataFrm")[0].reset();
                    url="${pageContext.request.contextPath}/rent/addRent.action";
                }
            });
        }

        //打开出租页面
        function openRentCar(data){
            mainIndex=layer.open({
                type:1,
                title:'汽车出租',
                content:$("#saveOrUpdateDiv"),
                area:['700px','400px'],
                success:function(index){
                //    清空表单数据
                    $('#dataFrm')[0].reset();
                //    请示数据
                    var identity = $('#identity').val();
                    var price = data.rentprice;
                    var carnumber = data.carnumber;
                    $.get('${pageContext.request.contextPath}/rent/initRentForm.action', {
                        identity: identity,
                        price: price,
                        carnumber: carnumber
                    }, function (obj) {
                    //    赋值
                        form.val('dataFrm', obj);
                    })
                }
            });
        }
        //保存
        form.on("submit(doSubmit)",function(obj){
            //序列化表单数据
            var params=$("#dataFrm").serialize();
            $.post('${pageContext.request.contextPath}/rent/saveRent.action',params,function(obj){
                layer.msg(obj.msg);
                //关闭弹出层
                layer.close(mainIndex);
            })
        });

        //查看
        function viewRent(data){
            mainIndex=layer.open({
                type:1,
                title:'查看出租',
                content:$("#viewRentDiv"),
                area:['800px','550px'],
                success:function(index){
                    $("#view_title").html(data.title);
                    $("#view_opername").html(data.opername);
                    $("#view_createtime").html(data.createtime);
                    $("#view_content").html(data.content);
                }
            });
        }

    });
</script>
</body>
</html>