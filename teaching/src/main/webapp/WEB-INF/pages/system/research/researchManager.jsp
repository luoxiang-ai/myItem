<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>科研资料管理</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Access-Control-Allow-Origin" content="*">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
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
            <label class="layui-form-label">出版社:</label>
            <div class="layui-input-inline">
                <input type="text" name="press"  autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">专著:</label>
            <div class="layui-input-inline">
                <input type="text" name="treatise"  autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">专利:</label>
            <div class="layui-input-inline">
                <input type="text" name="patent" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">纵向专题:</label>
            <div class="layui-input-inline">
                <input type="text" name="verticalTopic"  autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">横向专题:</label>
            <div class="layui-input-inline">
                <input type="text" name="horizontalIssues" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-col-md-3 layui-col-md-offset5">
            <button type="button" class="layui-btn layui-btn-normal  layui-icon layui-icon-search" id="doSearch">查询</button>
            <button type="reset" class="layui-btn layui-btn-warm  layui-icon layui-icon-refresh">重置</button>
        </div>
    </div>
</form>
<!-- 搜索条件结束 -->

<!-- 数据表格开始 -->
<table class="layui-hide" id="researchTable" lay-filter="researchTable"></table>
<div style="display: none;" id="researchToolBar">
    <button type="button" class="layui-btn layui-btn-sm" lay-event="add">增加</button>
    <button type="button" class="layui-btn layui-btn-sm layui-btn-danger" lay-event="batchDelete">批量删除</button>
</div>
<div id="researchBar" style="display: none;">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="upload">上传资料</a>
    <a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="download">下载</a>
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="showContent">查看内容</a>
</div>
<!-- 数据表格结束 -->

<!-- 添加和修改的弹出层开始 -->
<div style="display: none;padding: 20px" id="saveOrUpdateDiv" >
    <form class="layui-form"  lay-filter="dataFrm" id="dataFrm">
        <div class="layui-form-item">
            <label class="layui-form-label">分类：</label>
            <div class="layui-input-block">
                <select name="sid" id="sortId"></select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">详情：</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" name="details" placeholder="请输入详情">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">教材出版社：</label>
            <div class="layui-input-block">
                <input type="hidden" name="id" id="researchid">
                <input type="text" name="press"  placeholder="请输入教材出版社" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">专著：</label>
            <div class="layui-input-block">
                <input type="text" name="treatise" class="layui-input" placeholder="请输入专著">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">专利：</label>
            <div class="layui-input-block">
                <input type="text" name="patent" placeholder="请输入专利" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">纵向专题：</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" name="verticalTopic" placeholder="请输入纵向专题">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">横向专题：</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" name="horizontalIssues" placeholder="请输入横向专题">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">论文内容：</label>
            <div class="layui-input-block">
                <textarea class="layui-textarea" name="content" placeholder="请输入论文内容"></textarea>
            </div>
        </div>
        <div class="layui-form-item" style="text-align: center;">
            <div class="layui-input-block">
                <button type="button" class="layui-btn layui-btn-normal layui-btn-sm layui-icon layui-icon-release" lay-filter="doSubmit" lay-submit="">提交</button>
                <button type="reset" class="layui-btn layui-btn-warm layui-btn-sm layui-icon layui-icon-refresh" >重置</button>
            </div>
        </div>
    </form>
</div>
<!-- 添加和修改的弹出层结束 -->

<%-- 文件上传开始 --%>
<div style="display: none;" id="uploadResource">
    <button type="button" class="layui-btn layui-btn-normal" id="selectFile">选择文件</button>
    <button type="button" class="layui-btn" id="upload">开始上传</button>
</div>
<%-- 文件上传结束 --%>

<%-- 查看论文内容开始 --%>
<div id="showContent" style="display: none;padding: 10px;">
    <div id="content"></div>
</div>
<%-- 查看论文内容结束 --%>


<script src="${pageContext.request.contextPath}/resources/layui/layui.js"></script>
<script type="text/javascript">
    var tableIns;
    layui.extend({
        dtree:'${pageContext.request.contextPath}/resources/layui_ext/dist/dtree'
    }).use([ 'jquery', 'layer', 'form', 'table', 'upload'], function() {
        var $ = layui.jquery;
        var layer = layui.layer;
        var form = layui.form;
        var table = layui.table;
        var upload = layui.upload;

        //渲染数据表格
        tableIns=table.render({
            elem: '#researchTable'   //渲染的目标对象
            ,url:'${pageContext.request.contextPath}/research/loadResearchByTid.action?tid=' + ${sessionScope.sysUser.userid} //数据接口
            ,title: '科研资料数据表'//数据导出来的标题
            ,toolbar:"#researchToolBar"   //表格的工具条
            ,height:'full-150'
            ,cellMinWidth:100 //设置列的最小默认宽度
            ,page: true  //是否启用分页
            ,cols: [[   //列表数据
                {type: 'checkbox', fixed: 'left'}
                ,{field:'details', title:'详情',align:'center', width: 250}
                ,{field: 'press', align:'center', title: '教材出版社',width:150}
                ,{field: 'treatise', title: '专著', align:'center',width:150}
                ,{field:'patent', title:'专利',align:'center', width: 180}
                ,{field: 'verticalTopic', title: '纵向专题', align: 'center', width: 130}
                ,{field: 'horizontalIssues', title: '横向专题', align: 'center', width: 200}
                ,{field: 'cname', title: '分类名称', align: 'center', width: 80}
                ,{fixed: 'right', title: '操作', toolbar: '#researchBar', align: 'center', width: 360}
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
                url:"${pageContext.request.contextPath}/research/fuzzyQueryResearch.action?"+params
            })
        });

        //监听头部工具栏事件
        table.on("toolbar(researchTable)",function(obj){
            switch(obj.event){
                case 'add':
                    openAddResearch();
                    break;
                case 'batchDelete':
                    batchDeleteResearch();
                    break;
            };
        })
        //监听行工具事件
        table.on('tool(researchTable)', function(obj){
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            if(layEvent === 'del'){ //删除
                layer.confirm('您确定删除这个科研资料吗', function (index) {
                    // 向服务端发送删除请求
                    $.post('${pageContext.request.contextPath}/research/deleteResearch.action', {id: data.id}, function (res) {
                        layer.msg(res.msg);
                        // 重新刷新数据表格
                        tableIns.reload();
                    })
                });
            } else if(layEvent === 'edit'){ //编辑
                openUpdateResearch(data);
            } else if (layEvent === 'upload') {
                uploadRes(data);
            } else if(layEvent === 'download') {
                console.log(data);
                var path = data.path;
                if(path != null && path != undefined && path != '') {
                    window.location.href = '${pageContext.request.contextPath}/file/download.action?path=' + data.path + '&fileName=' + data.name;
                } else {
                    layer.msg('暂无资源');
                }
            } else if(layEvent == 'showContent') {
                showContent(data);
            }
        });

        var url;
        var mainIndex;

        //打开添加页面
        function openAddResearch(){
            mainIndex=layer.open({
                type:1,
                title:'添加科研资料',
                content:$("#saveOrUpdateDiv"),
                area:['800px','450px'],
                success:function(index){
                    //清空表单数据
                    $("#dataFrm")[0].reset();
                    url="${pageContext.request.contextPath}/research/addResearch.action";

                    $("#sortId").find("option").remove();
                    $('#sortId').append(new Option("请选择分类", ""));

                    //    加载分类列表
                    $.get('${pageContext.request.contextPath}/resourceSort/loadAllResourceSort.action', function (res) {
                        // 遍历填充数据
                        $.each(res.data, function (index, item) {
                            $('#sortId').append(new Option(item.name, item.id));// 下拉菜单里添加元素
                        });

                        // 更新select框
                        form.render("select");
                    })
                },
                cancel: function () {
                    $("#sortId").find("option").remove();
                }
            });
        }

        // 科研资料修改函数
        function openUpdateResearch(data){
            mainIndex = layer.open({
                type:1,
                title:'修改科研资料',
                content:$("#saveOrUpdateDiv"),
                area:['800px','450px'],
                success:function(index){
                    form.val("dataFrm",data);
                    url="${pageContext.request.contextPath}/research/updateResearch.action";

                    $("#sortId").find("option").remove();
                    $('#sortId').append(new Option("请选择分类", ""));


                    //    加载分类列表
                    $.get('${pageContext.request.contextPath}/resourceSort/loadAllResourceSort.action', function (res) {
                        // 遍历填充数据
                        $.each(res.data, function (index, item) {
                            $('#sortId').append(new Option(item.name, item.id));// 下拉菜单里添加元素
                        });

                        // select下拉框回显
                        $('#sortId').val(data.sid);

                        // 更新select框
                        form.render("select");
                    })
                },
                cancel: function () {
                    $("#sortId").find("option").remove();
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
                layer.close(mainIndex)
//刷新数据 表格
                tableIns.reload();
            })
        });

        $("#pid_div").on("click",function(){
            $(this).toggleClass("layui-form-selected");
            $("#researchSelectDiv").toggleClass("layui-show layui-anim layui-anim-upbit");
        });

        // 批量删除科研资料
        function batchDeleteResearch() {
            var checkStatus = table.checkStatus('researchTable');
            var data = checkStatus.data;
            var params = '';
            $.each(data, function (i, item) {
                if (i == 0) {
                    params += 'ids=' + item.id;
                } else {
                    params += '&ids=' + item.id;
                }
                layer.alert(params);
            });

            layer.confirm('您确定要批量删除这些科研资料吗', function (index) {
                //    向服务端发送删除请求
                $.post('${pageContext.request.contextPath}/research/batchDeleteResearch.action', params, function (res) {
                    layer.msg(res.msg);
                    //    重新刷新数据表格
                    tableIns.reload();
                })
            })
        }

        // 上传
        function uploadRes(data) {
            mainIndex = layer.open({
                type:1,
                title:'上传资料',
                content:$("#uploadResource"),
                area:['420px','230px'],
                success:function(index){
                    upload.render({
                        elem: '#selectFile'
                        ,url: '${pageContext.request.contextPath}/file/upload.action'
                        ,auto: false
                        ,field: 'mf'
                        ,accept: 'file'
                        ,bindAction: '#upload'
                        ,error: function () {
                            layer.msg('上传失败');
                        }
                        ,done: function(res){
                            console.log(data);
                            if(res.code == 0) {
                                url = '${pageContext.request.contextPath}/research/updateResource.action';
                                $.post(url, {
                                    id: data.id,
                                    path: data.path,    // 旧资源路径
                                    newPath: res.data.src  // 新资源路径
                                }, function(r) {
                                    layer.msg(r.msg);
                                })
                            }
                            layer.close(mainIndex);
                        }
                    })
                }
            })
        }

        // 查看论文内容
        function showContent(data) {
            layer.open({
                type:1,
                title:'正在查看该论文内容',
                content:$("#showContent"),
                area:['800px','400px'],
                success:function(index){
                    $("#content").html(data.content);
                }
            });
        }

    });

    // 刷新数据表格
    function reloadTable(id){
        tableIns.reload({
            url:"${pageContext.request.contextPath}/research/loadAllResearch.action?id="+id
        })
    }

</script>
</body>
</html>
