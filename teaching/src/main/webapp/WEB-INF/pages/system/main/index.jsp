<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>首页</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Access-Control-Allow-Origin" content="*">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="icon" href="${pageContext.request.contextPath}/resources/favicon.ico">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layui/css/layui.css" media="all" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/index.css" media="all" />
    <link rel="stylesheet" href="http://at.alicdn.com/t/font_2309252_h9jy95dlsjh.css">
</head>
<body class="main_body">
<div class="layui-layout layui-layout-admin">
    <!-- 顶部 -->
    <div class="layui-header header">
        <div class="layui-main mag0">
            <a href="#" class="logo">教学管理</a>
            <!-- 显示/隐藏菜单 -->
            <a href="javascript:;" class="seraph hideMenu icon-caidan"></a>
            <!-- 顶部右侧菜单 -->
            <ul class="layui-nav top_menu">
                <li class="layui-nav-item lockcms" pc>
                    <a href="javascript:;"><i class="seraph icon-lock"></i><cite>锁屏</cite></a>
                </li>
                <li class="layui-nav-item" id="userInfo">
                    <a href="javascript:;"><img src="${pageContext.request.contextPath}/file/downloadShowFile.action?path=${sessionScope.sysUser.userface}" class="layui-nav-img userAvatar" width="35" height="35"><cite class="adminName">超级管理员</cite></a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" data-url="${pageContext.request.contextPath}/sys/toPersonalManager.action"><i class="seraph icon-ziliao" data-icon="icon-ziliao"></i><cite>个人资料</cite></a></dd>
                        <dd><a href="javascript:;" data-url="${pageContext.request.contextPath}/sys/toChangePwdManager.action"><i class="seraph icon-xiugai" data-icon="icon-xiugai"></i><cite>修改密码</cite></a></dd>
                        <dd><a href="javascript:;" class="showNotice"><i class="layui-icon">&#xe645;</i><cite>系统公告</cite><span class="layui-badge-dot"></span></a></dd>
                        <dd pc><a href="javascript:;" class="functionSetting"><i class="layui-icon">&#xe620;</i><cite>功能设定</cite><span class="layui-badge-dot"></span></a></dd>
                        <dd pc><a href="javascript:;" class="changeSkin"><i class="layui-icon">&#xe61b;</i><cite>更换皮肤</cite></a></dd>
                        <dd><a href="${pageContext.request.contextPath}/logout.action" class="signOut"><i class="seraph icon-tuichu"></i><cite>退出</cite></a></dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>
    <!-- 左侧导航 -->
    <div class="layui-side layui-bg-black">
        <div class="user-photo">
            <a class="img" title="我的头像" ><img src="${pageContext.request.contextPath}/file/downloadShowFile.action?path=${sessionScope.sysUser.userface}" class="userAvatar"></a>
            <p>你好！<span class="userName">${sessionScope.sysUser.realname}</span>, 欢迎登录</p>
        </div>

        <div class="navBar layui-side-scroll" id="navBar" style="margin-top: 20px">
            <ul class="layui-nav layui-nav-tree">
                <li class="layui-nav-item layui-this">
                    <a href="javascript:;" data-url="page/main.html"><i class="layui-icon" data-icon=""></i><cite>后台首页</cite></a>
                </li>
            </ul>
        </div>
    </div>
    <!-- 右侧内容 -->
    <div class="layui-body layui-form">
        <div class="layui-tab mag0" lay-filter="bodyTab" id="top_tabs_box">
            <ul class="layui-tab-title top_tab" id="top_tabs">
                <li class="layui-this" lay-id=""><i class="layui-icon">&#xe68e;</i> <cite>后台首页</cite></li>
            </ul>
            <ul class="layui-nav closeBox">
                <li class="layui-nav-item">
                    <a href="javascript:;"><i class="layui-icon caozuo">&#xe643;</i> 页面操作</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" class="refresh refreshThis"><i class="layui-icon">&#xe669;</i> 刷新当前</a></dd>
                        <dd><a href="javascript:;" class="closePageOther"><i class="iconfont icon-jinyong"></i> 关闭其他</a></dd>
                        <dd><a href="javascript:;" class="closePageAll"><i class="iconfont icon-guanbi"></i> 关闭全部</a></dd>
                    </dl>
                </li>
            </ul>
            <div class="layui-tab-content clildFrame">
                <div class="layui-tab-item layui-show">
                    <iframe src="${pageContext.request.contextPath}/desk/toDeskManager.action"></iframe>
                </div>
            </div>
        </div>
    </div>
    <!-- 底部 -->
    <div class="layui-footer footer">
        <p><span></span></p>
    </div>
</div>

<!-- 移动导航 -->
<div class="site-tree-mobile"><i class="layui-icon">&#xe602;</i></div>
<div class="site-mobile-shade"></div>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/layui/layui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/cache.js"></script>
<script type="text/javascript">
    var $,tab,dataStr,layer;
    layui.config({
        base : "${pageContext.request.contextPath}/resources/js/"
    }).extend({
        "bodyTab" : "bodyTab"
    })
    layui.use(['bodyTab','form','element','layer','jquery'],function(){
        var form = layui.form,
            element = layui.element;
        $ = layui.$;
        layer = parent.layer === undefined ? layui.layer : top.layer;
        tab = layui.bodyTab({
            openTabNum : "50",  //最大可打开窗口数量
            url : "${pageContext.request.contextPath}/menu/loadIndexLeftMenuJson.action" //获取菜单json地址
        });

        //通过顶部菜单获取左侧二三级菜单   注：此处只做演示之用，实际开发中通过接口传参的方式获取导航数据
        function getData(json){
            $.getJSON(tab.tabConfig.url,function(data){
                dataStr = data;
                //重新渲染左侧菜单
                tab.render();
            })
        }
        //页面加载时判断左侧菜单是否显示
        //通过顶部菜单获取左侧菜单
        $(".topLevelMenus li,.mobileTopLevelMenus dd").click(function(){
            if($(this).parents(".mobileTopLevelMenus").length != "0"){
                $(".topLevelMenus li").eq($(this).index()).addClass("layui-this").siblings().removeClass("layui-this");
            }else{
                $(".mobileTopLevelMenus dd").eq($(this).index()).addClass("layui-this").siblings().removeClass("layui-this");
            }
            $(".layui-layout-admin").removeClass("showMenu");
            $("body").addClass("site-mobile");
            getData($(this).data("menu"));
            //渲染顶部窗口
            tab.tabMove();
        })

        //隐藏左侧导航
        $(".hideMenu").click(function(){
            if($(".topLevelMenus li.layui-this a").data("url")){
                layer.msg("此栏目状态下左侧菜单不可展开");  //主要为了避免左侧显示的内容与顶部菜单不匹配
                return false;
            }
            $(".layui-layout-admin").toggleClass("showMenu");
            //渲染顶部窗口
            tab.tabMove();
        })

        //通过顶部菜单获取左侧二三级菜单   注：此处只做演示之用，实际开发中通过接口传参的方式获取导航数据
        getData("contentManagement");

        //手机设备的简单适配
        $('.site-tree-mobile').on('click', function(){
            $('body').addClass('site-mobile');
        });
        $('.site-mobile-shade').on('click', function(){
            $('body').removeClass('site-mobile');
        });

        // 添加新窗口
        $("body").on("click",".layui-nav .layui-nav-item a:not('.mobileTopLevelMenus .layui-nav-item a')",function(){
            //如果不存在子级
            if($(this).siblings().length == 0){
                addTab($(this));
                $('body').removeClass('site-mobile');  //移动端点击菜单关闭菜单层
            }
            $(this).parent("li").siblings().removeClass("layui-nav-itemed");
        })

        //刷新后还原打开的窗口
        if(cacheStr == "true") {
            if (window.sessionStorage.getItem("menu") != null) {
                menu = JSON.parse(window.sessionStorage.getItem("menu"));
                curmenu = window.sessionStorage.getItem("curmenu");
                var openTitle = '';
                for (var i = 0; i < menu.length; i++) {
                    openTitle = '';
                    if (menu[i].icon) {
                        if (menu[i].icon.split("-")[0] == 'icon') {
                            openTitle += '<i class="seraph ' + menu[i].icon + '"></i>';
                        } else {
                            openTitle += '<i class="layui-icon">' + menu[i].icon + '</i>';
                        }
                    }
                    openTitle += '<cite>' + menu[i].title + '</cite>';
                    openTitle += '<i class="layui-icon layui-unselect layui-tab-close" data-id="' + menu[i].layId + '">&#x1006;</i>';
                    element.tabAdd("bodyTab", {
                        title: openTitle,
                        content: "<iframe src='" + menu[i].href + "' data-id='" + menu[i].layId + "'></frame>",
                        id: menu[i].layId
                    })
                    //定位到刷新前的窗口
                    if (curmenu != "undefined") {
                        if (curmenu == '' || curmenu == "null") {  //定位到后台首页
                            element.tabChange("bodyTab", '');
                        } else if (JSON.parse(curmenu).title == menu[i].title) {  //定位到刷新前的页面
                            element.tabChange("bodyTab", menu[i].layId);
                        }
                    } else {
                        element.tabChange("bodyTab", menu[menu.length - 1].layId);
                    }
                }
                //渲染顶部窗口
                tab.tabMove();
            }
        }else{
            window.sessionStorage.removeItem("menu");
            window.sessionStorage.removeItem("curmenu");
        }


        //判断是否web端打开
        // if(!/http(s*):\/\//.test(location.href)){
        //     layer.alert("请先将项目部署到 localhost 下再进行访问【建议通过tomcat、webstorm、hb等方式运行，不建议通过iis方式运行】，否则部分数据将无法显示");
        // }else{    //判断是否处于锁屏状态【如果关闭以后则未关闭浏览器之前不再显示】
        //     if(window.sessionStorage.getItem("lockcms") != "true" && window.sessionStorage.getItem("showNotice") != "true"){
        //         showNotice();
        //     }
        // }
        //
        // // 判断是否设置过头像，如果设置过则修改顶部、左侧和个人资料中的头像，否则使用默认头像
        // if(window.sessionStorage.getItem('userFace') &&  $(".userAvatar").length > 0){
        //     $("#userFace").attr("src",window.sessionStorage.getItem('userFace'));
        //     $(".userAvatar").attr("src",$(".userAvatar").attr("src").split("images/")[0] + "images/" + window.sessionStorage.getItem('userFace').split("images/")[1]);
        // }else{
        //     $("#userFace").attr("src","../../images/face.jpg");
        // }

        //锁屏
        function lockPage(){
            layer.open({
                title : false,
                type : 1,
                content : '<div class="admin-header-lock" id="lock-box">'+
                    '<div class="admin-header-lock-img"><img src="" class="userAvatar"/></div>'+
                    '<div class="admin-header-lock-name" id="lockUserName"></div>'+
                    '<div class="input_btn">'+
                    '<input type="password" class="admin-header-lock-input layui-input" autocomplete="off" placeholder="请输入密码解锁.." name="lockPwd" id="lockPwd" />'+
                    '<button class="layui-btn" id="unlock">解锁</button>'+
                    '</div>'+
                    '<p>请输入密码，否则不会解锁成功哦！！！</p>'+
                    '</div>',
                closeBtn : 0,
                shade : 0.9,
                success : function(){
                    //判断是否设置过头像，如果设置过则修改顶部、左侧和个人资料中的头像，否则使用默认头像
                    if(window.sessionStorage.getItem('userFace') &&  $(".userAvatar").length > 0){
                        $(".userAvatar").attr("src",$(".userAvatar").attr("src").split("images/")[0] + "images/" + window.sessionStorage.getItem('userFace').split("images/")[1]);
                    }
                }
            })
            $(".admin-header-lock-input").focus();
        }
        $(".lockcms").on("click",function(){
            window.sessionStorage.setItem("lockcms",true);
            lockPage();
        })
        // 判断是否显示锁屏
        if(window.sessionStorage.getItem("lockcms") == "true"){
            lockPage();
        }
        // 解锁
        $("body").on("click","#unlock",function(){
            if($(this).siblings(".admin-header-lock-input").val() == ''){
                layer.msg("请输入解锁密码！");
                $(this).siblings(".admin-header-lock-input").focus();
            }else{
                if($(this).siblings(".admin-header-lock-input").val() == "123456"){
                    window.sessionStorage.setItem("lockcms",false);
                    $(this).siblings(".admin-header-lock-input").val('');
                    layer.closeAll("page");
                }else{
                    layer.msg("密码错误，请重新输入！");
                    $(this).siblings(".admin-header-lock-input").val('').focus();
                }
            }
        });
        $(document).on('keydown', function(event) {
            var event = event || window.event;
            if(event.keyCode == 13) {
                $("#unlock").click();
            }
        });
    })

    //打开新窗口
    function addTab(_this){
        tab.tabAdd(_this);
    }

    //图片管理弹窗
    function showImg(){
        $.getJSON('json/images.json', function(json){
            var res = json;
            layer.photos({
                photos: res,
                anim: 5
            });
        });
    }

</script>
</body>
</html>
