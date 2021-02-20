<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>汽车管理</title>
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layui_ext/dtree/dtree.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layui_ext/dtree/font/dtreefont.css">
</head>
<body class="childrenBody">
<!-- 搜索条件开始 -->
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>查询条件</legend>
</fieldset>
<form class="layui-form" method="post" id="searchFrm">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">车牌号：</label>
            <div class="layui-input-inline">
                <input type="text" name="carnumber"  autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">类型：</label>
            <div class="layui-input-inline">
                <input type="text" name="cartype"  autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">描述：</label>
            <div class="layui-input-inline">
                <input type="text" name="description"  autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">开始时间：</label>
            <div class="layui-input-inline">
                <input type="text" name="startTime" id="startTime" readonly="readonly"  autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">结束时间：</label>
            <div class="layui-input-inline">
                <input type="text" name="endTime" id="endTime" readonly="readonly" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item layui-col-md-offset5">
            <button type="button" class="layui-btn layui-btn-normal  layui-icon layui-icon-search" id="doSearch">查询</button>
            <button type="reset" class="layui-btn layui-btn-warm  layui-icon layui-icon-refresh">重置</button>
        </div>
    </div>
</form>
<!-- 搜索条件结束 -->

<!-- 数据表格开始 -->
<table class="layui-hide" id="carTable" lay-filter="carTable"></table>
<div style="display: none;" id="carToolBar">
    <button type="button" class="layui-btn layui-btn-sm" lay-event="add">增加</button>
    <button type="button" class="layui-btn layui-btn-danger layui-btn-sm" lay-event="deleteBatch">批量删除</button>
</div>
<div  id="carBar" style="display: none;">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="viewCar">查看大图</a>
</div>
<!-- 数据表格结束 -->

<!-- 添加和修改的弹出层开始 -->
<div style="display: none;padding: 20px" id="saveOrUpdateDiv" >
    <form class="layui-form"  lay-filter="dataFrm" id="dataFrm">
        <div class="layui-col-md12 layui-col-xs12">
            <div class="layui-row layui-col-space10">
                <div class="layui-col-md9 layui-col-xs7">
                    <div class="layui-form-item">
                        <label class="layui-form-label">车牌号:</label>
                        <div class="layui-input-block">
                            <input type="text" name="carnumber"  placeholder="请输入车牌号" autocomplete="off"
                                   class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">汽车类型:</label>
                        <div class="layui-input-block">
                            <input type="text" name="cartype" class="layui-input" placeholder="请输入车辆类型">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">描述：</label>
                        <div class="layui-input-block">
                            <input type="text" class="layui-input" name="description" placeholder="请输入描述信息">
                        </div>
                    </div>
                </div>

                <div class="layui-col-md3 layui-col-xs5">
                    <div class="layui-upload-list thumbBox mag0 magt3" id="uploadCarImg">
                        <!-- 显示上传的图片 -->
                        <img class="layui-upload-img thumbImg" id="showCarImg">
                        <!-- 保存当前显示图片的地址 -->
                        <input type="hidden" name="carimg" id="carimg">
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">汽车价格：</label>
                    <div class="layui-input-inline">
                        <input type="text" name="price" class="layui-input" placeholder="请输入汽车的价格">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">租金：</label>
                    <div class="layui-input-inline">
                        <input type="text" class="layui-input" name="rentprice" placeholder="请输入汽车的租金">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">定金：</label>
                    <div class="layui-input-inline">
                        <input type="text" class="layui-input" name="deposit" placeholder="请输入汽车的定金">
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">颜色：</label>
                <div class="layui-input-block">
                    <input type="text" name="color" class="layui-input" placeholder="请输入汽车颜色">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">是否出租：</label>
                <div class="layui-input-block">
                    <input type="radio" class="layui-input" value="1" title="已出租" name="isrenting">
                    <input type="radio" class="layui-input" value="0" title="未出租" name="isrenting" checked="checked">
                </div>
            </div>
            <div class="layui-form-item" style="text-align: center;">
                <div class="layui-input-block">
                    <button type="button" class="layui-btn layui-btn-normal layui-btn-sm layui-icon layui-icon-release" lay-filter="doSubmit" lay-submit="">提交</button>
                    <button type="reset" id="dataFrmResetBtn" class="layui-btn layui-btn-warm layui-btn-sm layui-icon layui-icon-refresh" >重置</button>
                </div>
            </div>
        </div>
    </form>

</div>
<!-- 添加和修改的弹出层结束 -->


<!-- 查看汽车的div -->
<div id="viewCarDiv" style="display: none;padding: 7px">
    <img width="485" height="342" alt="图片失效" id="carBigImg">
</div>

<script src="${pageContext.request.contextPath}/resources/layui/layui.js"></script>
<script type="text/javascript">
    var element;
    var tableIns;
    layui.use([ 'jquery', 'layer', 'form', 'table','laydate','layedit','upload', 'element'], function() {
        var $ = layui.jquery;
        var layer = layui.layer;
        var form = layui.form;
        var table = layui.table;
        var laydate=layui.laydate;
        var layedit=layui.layedit;
        var upload = layui.upload;
        element = layui.element;

        //渲染时间
        laydate.render({
            elem:'#startTime',
            type:'datetime'
        });
        laydate.render({
            elem:'#endTime',
            type:'datetime'
        });
        //初始化富文本编辑器
        var editIndex;

        //渲染数据表格
        tableIns=table.render({
            elem: '#carTable'   //渲染的目标对象
            ,url:'${pageContext.request.contextPath}/car/loadAllCar.action' //数据接口
            ,title: '车辆数据表'//数据导出来的标题
            ,toolbar:"#carToolBar"   //表格的工具条
            ,height:'full-200'
            ,cellMinWidth:100 //设置列的最小默认宽度
            ,page: true  //是否启用分页
            ,cols: [[   //列表数据
                {type: 'checkbox', fixed: 'left'}
                ,{field:'carnumber', title:'车牌号',align:'center', width: 120}
                ,{field:'cartype', title:'类型',align:'center', width: 100}
                ,{field:'color', title:'颜色',align:'center', width: 100}
                ,{field: 'deposit', title: '押金', align: 'center', width: 80}
                ,{field: 'price', title: '价格', align: 'center', width: 80}
                ,{field: 'rentprice', title: '租金', align: 'center', width: 80}
                ,{field: 'description', title: '汽车描述', align: 'center', width: 200}
                ,{field:'carimg', title:'缩略图',align:'center',width: 80, templet: function(d) {
                    return '<img width=40 height=30 src=${pageContext.request.contextPath}/file/downloadShowFile.action?path=' + d.carimg + '>';
                }}
                ,{field:'createtime', title:'发布时间',align:'center', width: 180}
                ,{fixed:'right',title:'操作',toolbar:'#carBar',align:'center',width:200}
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
                url:"${pageContext.request.contextPath}/car/fuzzyQueryCar.action?"+params,
                page:{curr:1}
            })
        });

        //监听头部工具栏事件
        table.on("toolbar(carTable)",function(obj){
            switch(obj.event){
                case 'add':
                    openAddCar();
                    break;
                case 'deleteBatch':
                    deleteBatch();
                    break;
            };
        })
        //监听行工具事件
        table.on('tool(carTable)', function(obj){
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            if(layEvent === 'del'){ //删除
                layer.confirm('真的删除【'+data.carnumber+'】这个汽车吗', function(index){
                    //向服务端发送删除指令
                    $.post("${pageContext.request.contextPath}/car/deleteCar.action",{carnumber:data.carnumber},function(res){
                        layer.msg(res.msg);
                        //刷新数据 表格
                        tableIns.reload();
                    })
                });
            } else if(layEvent === 'edit'){ //编辑
                openUpdateCar(data);
            }else if(layEvent==='viewCar'){
                viewCar(data);
            }
        });

        var url;
        var mainIndex;
        //打开添加页面
        function openAddCar(){
            mainIndex=layer.open({
                type:1,
                title:'添加汽车',
                content:$("#saveOrUpdateDiv"),
                area:['1100px','500px'],
                success:function(index){
                    $('#showCarImg').attr('src', '${pageContext.request.contextPath}/file/downloadShowFile.action?path=default/defaultCarImg.png');
                    $('#carimg').val('default/defaultCarImg.png');
                    //清空表单数据
                    $("#dataFrm")[0].reset();
                    url="${pageContext.request.contextPath}/car/addCar.action";
                }
            });
        }

        $("#dataFrmResetBtn").click(function(){
            layedit.setContent(editIndex,"");
        });

        //打开修改页面
        function openUpdateCar(data){
            mainIndex=layer.open({
                type:1,
                title:'修改汽车',
                content:$("#saveOrUpdateDiv"),
                area:['1000px','400px'],
                success:function(index){
                    form.val("dataFrm",data);
                    $('#showCarImg').attr('src', '${pageContext.request.contextPath}/file/downloadShowFile.action?path=' + data.carimg);
                    // $('#carimg').val(res.data.src);
                    url="${pageContext.request.contextPath}/car/updateCar.action";
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

        //查看汽车大图
        function viewCar(data){
            mainIndex=layer.open({
                type:1,
                title:'正在查看【' + data.carnumber + '】汽车图片',
                content:$("#viewCarDiv"),
                area:['500px','400px'],
                success:function(index){
                   $('#carBigImg').attr('src', '${pageContext.request.contextPath}/file/downloadShowFile.action?path=' + data.carimg);
                }
            });
        }

        //批量删除
        function deleteBatch(){
            //得到选中的数据行
            var checkStatus = table.checkStatus('carTable');
            var data = checkStatus.data;
            var params="";
            $.each(data,function(i,item){
                if(i==0){
                    params+="ids="+item.carnumber;
                }else{
                    params+="&ids="+item.carnumber;
                }
            });
            layer.confirm('真的删除选中的这些汽车吗', function(index){
                //向服务端发送删除指令
                $.post("${pageContext.request.contextPath}/car/batchDeleteCar.action",params,function(res){
                    layer.msg(res.msg);
                    //刷新数据 表格
                    tableIns.reload();
                })
            });
        }

    //    文件上传
        upload.render({
            elem: '#uploadCarImg',
            url: '${pageContext.request.contextPath}/file/upload.action',
            progress: function(n, elem){
                layui.use('element', function(){
                    element = layui.element;
                    var percent = n + '%' //获取进度百分比
                    //进行动态绑
                    layui.element.init();
                    //$('#demo1').attr('lay-percent',percent);
                    element.progress('demos', percent);
                });
            },
            // size: 1024,
            drag: true,
            // acceptMime: 'images/*',
            accept: 'file',
            field: 'mf',
            done: function (res, index, upload) {
                console.log(res);
                $('#showCarImg').attr('src', '${pageContext.request.contextPath}/file/downloadShowFile.action?path=' + res.data.src);
                $('#carimg').val(res.data.src);
                $('#showCarImg').css('background', '#fff')
            }
        })

    });
</script>
</body>
</html>