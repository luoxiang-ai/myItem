<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>流程管理</title>
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
                <div class="layui-col-md4">
                    <label class="layui-form-label">登陆帐号:</label>
                    <div class="layui-input-inline">
                        <input type="text" name="loginname"  autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-col-md4">
                    <label class="layui-form-label">登陆IP:</label>
                    <div class="layui-input-inline">
                        <input type="text" name="loginip"  autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-col-md4">
                    <label class="layui-form-label">登录时间:</label>
                    <div class="layui-input-inline">
                        <input type="text" name="logintime" id="startTime" readonly="readonly"  autocomplete="off" class="layui-input">
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-col-md2 layui-col-md-offset5">
                    <button type="button" class="layui-btn layui-btn-normal" id="doSearch">查询</button>
                    <button type="reset" class="layui-btn layui-btn-warm">重置</button>
                </div>
            </div>
        </form>
        <!-- 搜索条件结束 -->

        <!-- 数据表格开始 -->
        <table class="layui-hide" id="workFlowTable" lay-filter="workFlowTable"></table>
        <div style="display: none;" id="workFlowToolBar">
            <button type="button" class="layui-btn layui-btn-sm" lay-event="addProcessDefinition">添加流程部署</button>
            <button type="button" class="layui-btn layui-btn-danger layui-btn-sm" lay-event="deleteBatch">批量删除</button>
        </div>
        <div  id="workFlowBar" style="display: none;">
            <a class="layui-btn layui-btn-xs" lay-event="viewProcessImage">查看流程图片</a>
            <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
        </div>
        <!-- 数据表格结束 -->

        <!-- 流程部署开始 -->
        <div class="layui-row" id="deployment" style="display:none;">
            <div class="layui-col-md10">
                <div class="layui-block">
                    <label class="layui-form-label">部署名称：</label>
                    <div class="layui-input-block">
                        <input type="text" class="layui-input" id="deploymentName">
                    </div>
                </div>
            </div>
            <div class="layui-block">
                <button type="button" class="layui-btn layui-btn-normal" id="addProcessDefinition">选择文件</button>
            </div>
            <div class="layui-block">
                <button type="button" class="layui-btn" id="start">开始部署</button>
            </div>
        </div>
        <!-- 流程部署结束 -->

        <!-- 查看流程图片开始 -->
        <div id="viewProcessImage" sytle="display:none;">
            <img src="" id="processImage" alt="图片无法加载">
        </div>
        <!-- 查看流程图片结束 -->


        <script src="${pageContext.request.contextPath}/resources/layui/layui.js"></script>
        <script type="text/javascript">
            var tableIns;
            layui.use([ 'jquery', 'layer', 'form', 'table','laydate', 'upload'], function() {
                var $ = layui.jquery;
                var layer = layui.layer;
                var form = layui.form;
                var table = layui.table;
                var laydate=layui.laydate;
                var upload = layui.upload;
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
                    elem: '#workFlowTable'   //渲染的目标对象
                    ,url:'${pageContext.request.contextPath}/workFlow/loadAllProcessDeployment.action' //数据接口
                    ,title: '流程部署表'//数据导出来的标题
                    ,toolbar:"#workFlowToolBar"   //表格的工具条
                    ,height:'full-150'
                    ,cellMinWidth:100 //设置列的最小默认宽度
                    ,limit: 10
                    ,page: true  //是否启用分页
                    ,cols: [[   //列表数据
                        {type: 'checkbox', fixed: 'left'}
                        ,{field:'id', title:'ID',align:'center'}
                        ,{field:'name', title:'部署名称',align:'center'}
                        ,{field:'category', title:'登录IP',align:'center'}
                        ,{field:'key', title: 'key', align: 'center'}
                        ,{field:'deploymentTime', title:'部署时间',align:'center'}
                        ,{fixed: 'right', title:'操作', toolbar: '#workFlowBar', width:220,align:'center'}
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
                        url:"${pageContext.request.contextPath}/workFlow/loadAllWorkFlow.action?"+params,
                        page:{curr:1}

                    })
                });

                //监听头部工具栏事件
                table.on("toolbar(workFlowTable)",function(obj){
                    switch(obj.event){
                        case 'deleteBatch':
                            deleteBatch();
                            break;
                        case 'addProcessDefinition':
                            addProcessDefinition();
                            break;
                    };
                })
                //监听行工具事件
                table.on('tool(workFlowTable)', function(obj){
                    var data = obj.data; //获得当前行数据
                    var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
                    if(layEvent === 'del'){ //删除
                        console.log(data);
                        layer.confirm('确定删除【'+data.name+'】这个流程吗', function(index){
                            //向服务端发送删除指令
                            $.post("${pageContext.request.contextPath}/workFlow/deleteWorkFlow.action",{deploymentId:data.id},function(res){
                                // 提示信息
                                layer.msg(res.msg);
                                //刷新数据 表格
                                tableIns.reload();
                            })
                        });
                    } else if(layEvent === 'viewProcessImage') {
                        viewProcessImage(data);
                    }
                });
                //批量删除
                function deleteBatch(){
                    //得到选中的数据行
                    var checkStatus = table.checkStatus('workFlowTable');
                    var data = checkStatus.data;
                    var params="";
                    $.each(data,function(i,item){
                        if(i==0){
                            params+="ids="+item.id;
                        }else{
                            params+="&ids="+item.id;
                        }
                    });
                    layer.confirm('确定删除选中的这些工作流吗', function(index){
                        //向服务端发送删除指令
                        $.post("${pageContext.request.contextPath}/workFlow/batchDeleteWorkFlow.action",params,function(res){
                            layer.msg(res.msg);
                            //刷新数据 表格
                            tableIns.reload();
                        })
                    });
                }

                function addProcessDefinition() {
                    layer.open({
                        type:1,
                        title:'添加部署信息',
                        content:$("#deployment"),
                        area:['400px','300px'],
                        success:function(index){
                            //清空表单数据
                            url="${pageContext.request.contextPath}/user/addUser.action";
                        }
                    });
                    //选完文件后不自动上传
                    upload.render({
                        elem: '#addProcessDefinition'
                        ,url: '${pageContext.request.contextPath}/workFlow/addWorkFlow.action'
                        ,auto: false
                        //,multiple: true
                        ,bindAction: '#start'
                        ,accept: 'file'
                        ,acceptMime: 'application/zip'
                        ,exts: 'zip'
                        ,size: 5024
                        ,field: 'mf'
                        ,done: function(res){
                            layer.msg('上传成功');
                            console.log(res)
                        }
                        ,data: {
                            deploymentName: function () {
                                return $('#deploymentName').val();
                            }
                        }
                    });
                }

                // 查看流程图片
                function viewProcessImage(data) {
                    console.log(data);
                    layer.open({
                        type: 1,
                        title:'正在要查看【' + data.name + '】流程图片',
                        content:$("#viewProcessImage"),
                        area:['700px','400px'],
                        success:function(index){
                            $('#processImage').attr('src', '${pageContext.request.contextPath}/workFlow/viewProcessImage.action?deploymentId=' + data.id)
                        }
                    })
                }
            });
        </script>
    </body>
</html>