/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2021-02-13 11:47:24 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.pages.system.user;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class userManager_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("    <meta charset=\"utf-8\">\r\n");
      out.write("    <title>用户管理</title>\r\n");
      out.write("    <meta name=\"renderer\" content=\"webkit\">\r\n");
      out.write("    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\">\r\n");
      out.write("    <meta http-equiv=\"Access-Control-Allow-Origin\" content=\"*\">\r\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, maximum-scale=1\">\r\n");
      out.write("    <meta name=\"apple-mobile-web-app-status-bar-style\" content=\"black\">\r\n");
      out.write("    <meta name=\"apple-mobile-web-app-capable\" content=\"yes\">\r\n");
      out.write("    <meta name=\"format-detection\" content=\"telephone=no\">\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/resources/layui/css/layui.css\" media=\"all\" />\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/resources/css/public.css\" media=\"all\" />\r\n");
      out.write("    <link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/resources/layui_ext/city-picker/city-picker.css\" rel=\"stylesheet\" />\r\n");
      out.write("</head>\r\n");
      out.write("<body class=\"childrenBody\">\r\n");
      out.write("<!-- 搜索条件开始 -->\r\n");
      out.write("<fieldset class=\"layui-elem-field layui-field-title\" style=\"margin-top: 20px;\">\r\n");
      out.write("    <legend>查询条件</legend>\r\n");
      out.write("</fieldset>\r\n");
      out.write("<form class=\"layui-form\" method=\"post\" id=\"searchFrm\">\r\n");
      out.write("    <div class=\"layui-form-item\">\r\n");
      out.write("        <div class=\"layui-inline\">\r\n");
      out.write("            <label class=\"layui-form-label\">客户名称:</label>\r\n");
      out.write("            <div class=\"layui-input-inline\">\r\n");
      out.write("                <input type=\"text\" name=\"custname\"  autocomplete=\"off\" class=\"layui-input\">\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("        <div class=\"layui-inline\">\r\n");
      out.write("            <label class=\"layui-form-label\">身份证:</label>\r\n");
      out.write("            <div class=\"layui-input-inline\">\r\n");
      out.write("                <input type=\"text\" name=\"identity\" lay-verify=\"number\"  autocomplete=\"off\" class=\"layui-input\">\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("        <div class=\"layui-inline\">\r\n");
      out.write("            <label class=\"layui-form-label\">工作:</label>\r\n");
      out.write("            <div class=\"layui-input-inline\">\r\n");
      out.write("                <input type=\"text\" name=\"career\"  autocomplete=\"off\" class=\"layui-input\">\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("        <div class=\"layui-inline\">\r\n");
      out.write("            <label class=\"layui-form-label\">地址:</label>\r\n");
      out.write("            <div class=\"layui-input-inline\">\r\n");
      out.write("                <input type=\"text\" name=\"address\"  autocomplete=\"off\" class=\"layui-input\">\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("        <div class=\"layui-inline\">\r\n");
      out.write("            <label class=\"layui-form-label\">电话号码:</label>\r\n");
      out.write("            <div class=\"layui-input-inline\">\r\n");
      out.write("                <input type=\"text\" name=\"phone\" lay-verify=\"number\"  autocomplete=\"off\" class=\"layui-input\">\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("        <div class=\"layui-inline\">\r\n");
      out.write("            <label class=\"layui-form-label\">性别:</label>\r\n");
      out.write("            <div class=\"layui-input-inline\">\r\n");
      out.write("                <input type=\"radio\" value=\"1\" title=\"男\" name=\"sex\"  autocomplete=\"off\" class=\"layui-input\">\r\n");
      out.write("                <input type=\"radio\" value=\"0\" title=\"女\" name=\"sex\"  autocomplete=\"off\" class=\"layui-input\">\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("        <div class=\"layui-col-md-3 layui-col-md-offset5\">\r\n");
      out.write("            <button type=\"button\" class=\"layui-btn layui-btn-normal  layui-icon layui-icon-search\" id=\"doSearch\">查询</button>\r\n");
      out.write("            <button type=\"reset\" class=\"layui-btn layui-btn-warm  layui-icon layui-icon-refresh\">重置</button>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("</form>\r\n");
      out.write("<!-- 搜索条件结束 -->\r\n");
      out.write("\r\n");
      out.write("<!-- 数据表格开始 -->\r\n");
      out.write("<table class=\"layui-hide\" id=\"userTable\" lay-filter=\"userTable\"></table>\r\n");
      out.write("<div style=\"display: none;\" id=\"userToolBar\">\r\n");
      out.write("    <button type=\"button\" class=\"layui-btn layui-btn-sm\" lay-event=\"add\">增加</button>\r\n");
      out.write("    <button type=\"button\" class=\"layui-btn layui-btn-sm layui-btn-danger\" lay-event=\"batchDelete\">批量删除</button>\r\n");
      out.write("</div>\r\n");
      out.write("<div id=\"userBar\" style=\"display: none;\">\r\n");
      out.write("    <a class=\"layui-btn layui-btn-xs\" lay-event=\"edit\">编辑</a>\r\n");
      out.write("    <a class=\"layui-btn layui-btn-xs layui-btn-normal\" lay-event=\"selectUserRole\">分配角色</a>\r\n");
      out.write("    <a class=\"layui-btn layui-btn-xs layui-btn-warm\" lay-event=\"resetPwd\">重置密码</a>\r\n");
      out.write("    <a class=\"layui-btn layui-btn-danger layui-btn-xs\" lay-event=\"del\">删除</a>\r\n");
      out.write("</div>\r\n");
      out.write("<!-- 数据表格结束 -->\r\n");
      out.write("\r\n");
      out.write("<!-- 添加和修改的弹出层开始 -->\r\n");
      out.write("<div style=\"display: none;padding: 20px\" id=\"saveOrUpdateDiv\" class=\"layui-fluid\">\r\n");
      out.write("    <form class=\"layui-form\"  lay-filter=\"dataFrm\" id=\"dataFrm\">\r\n");
      out.write("        <div class=\"layui-form-item\">\r\n");
      out.write("            <div class=\"layui-col-md5\">\r\n");
      out.write("                <label class=\"layui-form-label\">客户身份证:</label>\r\n");
      out.write("                <div class=\"layui-input-block\">\r\n");
      out.write("                    <input type=\"text\" lay-verify=\"required|identity\" name=\"identity\" placeholder=\"请输入客户身份证号\" autocomplete=\"off\" class=\"layui-input\">\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"layui-col-md5 layui-col-md-offset1\">\r\n");
      out.write("                <label class=\"layui-form-label\">客户名称:</label>\r\n");
      out.write("                <div class=\"layui-input-block\">\r\n");
      out.write("                    <input type=\"text\" lay-verify=\"required\" name=\"custname\" class=\"layui-input\" placeholder=\"请输入客户名称\">\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("\r\n");
      out.write("        <div class=\"layui-form-item\">\r\n");
      out.write("            <div class=\"layui-col-md11\">\r\n");
      out.write("                <label class=\"layui-form-label\">地址:</label>\r\n");
      out.write("                <div class=\"layui-input-block\">\r\n");
      out.write("                    <input type=\"text\" lay-verify=\"required\" autocomplete=\"on\" class=\"layui-input\" id=\"city-picker\" name=\"address\" readonly=\"readonly\" data-toggle=\"city-picker\" placeholder=\"请选择\">\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("\r\n");
      out.write("        <div class=\"layui-form-item\">\r\n");
      out.write("            <div class=\"layui-col-md4\">\r\n");
      out.write("                <label class=\"layui-form-label\">电话号码:</label>\r\n");
      out.write("                <div class=\"layui-input-block\">\r\n");
      out.write("                    <input type=\"text\" lay-verify=\"required|phone\" name=\"phone\" class=\"layui-input\" placeholder=\"请输入客户电话\">\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"layui-col-md4\">\r\n");
      out.write("                <label class=\"layui-form-label\">工作职位:</label>\r\n");
      out.write("                <div class=\"layui-input-block\">\r\n");
      out.write("                    <input type=\"text\" name=\"career\" class=\"layui-input\" placeholder=\"请输入客户职位\">\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div class=\"layui-col-md4\">\r\n");
      out.write("                <label class=\"layui-form-label\">性别:</label>\r\n");
      out.write("                <div class=\"layui-input-block\">\r\n");
      out.write("                    <input type=\"radio\" title=\"男\" value=\"1\" name=\"sex\">\r\n");
      out.write("                    <input type=\"radio\" title=\"女\" value=\"0\" name=\"sex\">\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("\r\n");
      out.write("        <div class=\"layui-form-item\" style=\"margin-top: 40px\">\r\n");
      out.write("            <button type=\"button\" class=\"layui-btn layui-btn-normal layui-btn-sm layui-icon layui-icon-release layui-col-md1 layui-col-md-offset5\" lay-filter=\"doSubmit\" lay-submit=\"\">提交</button>\r\n");
      out.write("            <button type=\"reset\" id=\"dataFrmResetBtn\" class=\"layui-btn layui-btn-warm layui-btn-sm layui-icon layui-icon-refresh layui-col-md1\" >重置</button>\r\n");
      out.write("        </div>\r\n");
      out.write("    </form>\r\n");
      out.write("</div>\r\n");
      out.write("<!-- 添加和修改的弹出层结束 -->\r\n");
      out.write("\r\n");
      out.write("<!-- 用户权限设置的弹出层开始 -->\r\n");
      out.write("<div style=\"display: none;\" id=\"selectUserRole\">\r\n");
      out.write("    <table class=\"layui-hide\" id=\"roleTable\" lay-filter=\"roleTable\"></table>\r\n");
      out.write("</div>\r\n");
      out.write("<!-- 用户权限设置的弹出层结束 -->\r\n");
      out.write("\r\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/resources/layui/layui.js\"></script>\r\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/resources/layui_ext/city-picker/city-picker.data.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("    var tableIns;\r\n");
      out.write("    layui.extend({\r\n");
      out.write("        dtree:'");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/resources/layui_ext/dist/dtree',\r\n");
      out.write("        citypicker: '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/resources/layui_ext/city-picker/city-picker'\r\n");
      out.write("    }).use([ 'jquery', 'layer', 'form', 'table', 'citypicker'], function() {\r\n");
      out.write("        var $ = layui.jquery;\r\n");
      out.write("        var layer = layui.layer;\r\n");
      out.write("        var form = layui.form;\r\n");
      out.write("        var table = layui.table;\r\n");
      out.write("        var cityPicker = layui.citypicker;\r\n");
      out.write("\r\n");
      out.write("        var currentPicker = new cityPicker(\"#city-picker\", {\r\n");
      out.write("            provincename:\"provinceId\",\r\n");
      out.write("            cityname:\"cityId\",\r\n");
      out.write("            districtname: \"districtId\",\r\n");
      out.write("            level: 'districtId',// 级别\r\n");
      out.write("        });\r\n");
      out.write("\r\n");
      out.write("        //渲染数据表格\r\n");
      out.write("        tableIns=table.render({\r\n");
      out.write("            elem: '#userTable'   //渲染的目标对象\r\n");
      out.write("            ,url:'");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/user/loadAllUser.action' //数据接口\r\n");
      out.write("            ,title: '用户数据表'//数据导出来的标题\r\n");
      out.write("            ,toolbar:\"#userToolBar\"   //表格的工具条\r\n");
      out.write("            ,height:'full-150'\r\n");
      out.write("            ,cellMinWidth:100 //设置列的最小默认宽度\r\n");
      out.write("            ,page: true  //是否启用分页\r\n");
      out.write("            ,cols: [[   //列表数据\r\n");
      out.write("                {type: 'checkbox', fixed: 'left'}\r\n");
      out.write("                ,{field:'userid', title:'ID',align:'center'}\r\n");
      out.write("                ,{field:'loginname', title:'用户名称',align:'center', width: 120}\r\n");
      out.write("                ,{field: 'position', title: '描述', align: 'center', width: 120}\r\n");
      out.write("                ,{field:'identity', title:'身份证',align:'center', width: 180}\r\n");
      out.write("                ,{field: 'phone', title: '电话', align: 'center', width: 130}\r\n");
      out.write("                ,{field: 'address', title: '地址', align: 'center', width: 200}\r\n");
      out.write("                ,{field: 'sex', title: '性别', align: 'center', width: 80,sort: true, templet: function (d) {\r\n");
      out.write("                    return d.sex == '1' ? '男' : '女';\r\n");
      out.write("                }}\r\n");
      out.write("                ,{field:'available', title:'是否可用',align:'center',width: 120,sort:true,templet:function(d){\r\n");
      out.write("                        return d.available=='1'?'<font color=green>可用</font>':'<font color=red>不可用</font>';\r\n");
      out.write("                    }},\r\n");
      out.write("                {fixed: 'right', title: '操作', toolbar: '#userBar', align: 'center', width: 260}\r\n");
      out.write("            ]]\r\n");
      out.write("            ,done: function(res, curr, count) {\r\n");
      out.write("                // res：接口返回的信息\r\n");
      out.write("                // curr：当前页码数\r\n");
      out.write("                // count：数据总记录数\r\n");
      out.write("\r\n");
      out.write("                // 如果不是第一页，并且数据量为0的情况下，就返回上一页\r\n");
      out.write("                if(curr != 1 && res.data.length == 0) {\r\n");
      out.write("                    tableIns.reload({\r\n");
      out.write("                        page: {\r\n");
      out.write("                            curr: curr - 1\r\n");
      out.write("                        }\r\n");
      out.write("                    })\r\n");
      out.write("                }\r\n");
      out.write("            }\r\n");
      out.write("        })\r\n");
      out.write("\r\n");
      out.write("        //模糊查询\r\n");
      out.write("        $(\"#doSearch\").click(function(){\r\n");
      out.write("            var params=$(\"#searchFrm\").serialize();\r\n");
      out.write("            alert(params);\r\n");
      out.write("            tableIns.reload({\r\n");
      out.write("                url:\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/user/loadAllUser.action?\"+params\r\n");
      out.write("            })\r\n");
      out.write("        });\r\n");
      out.write("\r\n");
      out.write("        //监听头部工具栏事件\r\n");
      out.write("        table.on(\"toolbar(userTable)\",function(obj){\r\n");
      out.write("            switch(obj.event){\r\n");
      out.write("                case 'add':\r\n");
      out.write("                    openAddUser();\r\n");
      out.write("                    break;\r\n");
      out.write("                case 'batchDelete':\r\n");
      out.write("                    batchDeleteUsers();\r\n");
      out.write("                    break;\r\n");
      out.write("            };\r\n");
      out.write("        })\r\n");
      out.write("        //监听行工具事件\r\n");
      out.write("        table.on('tool(userTable)', function(obj){\r\n");
      out.write("            var data = obj.data; //获得当前行数据\r\n");
      out.write("            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）\r\n");
      out.write("            if(layEvent === 'del'){ //删除\r\n");
      out.write("                layer.confirm('您确定删除【' + data.loginname + '】这个用户吗', function (index) {\r\n");
      out.write("                    console.log(index);\r\n");
      out.write("                    // 向服务端发送删除请求\r\n");
      out.write("                    $.post('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/user/deleteUser.action', {userId: data.userid}, function (res) {\r\n");
      out.write("                        layer.msg(res.msg);\r\n");
      out.write("                        // 重新刷新数据表格\r\n");
      out.write("                        tableIns.reload();\r\n");
      out.write("                    })\r\n");
      out.write("                });\r\n");
      out.write("            } else if(layEvent === 'edit'){ //编辑\r\n");
      out.write("                openUpdateUser(data);\r\n");
      out.write("            } else if (layEvent == 'selectUserRole') {\r\n");
      out.write("                openSelectUserRole(data);\r\n");
      out.write("            }\r\n");
      out.write("        });\r\n");
      out.write("\r\n");
      out.write("        var url;\r\n");
      out.write("        var mainIndex;\r\n");
      out.write("//打开添加页面\r\n");
      out.write("        function openAddUser(){\r\n");
      out.write("            mainIndex=layer.open({\r\n");
      out.write("                type:1,\r\n");
      out.write("                title:'添加用户',\r\n");
      out.write("                content:$(\"#saveOrUpdateDiv\"),\r\n");
      out.write("                area:['800px','450px'],\r\n");
      out.write("                success:function(index){\r\n");
      out.write("                    //清空表单数据\r\n");
      out.write("                    $(\"#dataFrm\")[0].reset();\r\n");
      out.write("                    url=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/user/addUser.action\";\r\n");
      out.write("                }\r\n");
      out.write("            });\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        // 用户修改函数\r\n");
      out.write("        function openUpdateUser(data){\r\n");
      out.write("            console.log(data);\r\n");
      out.write("            mainIndex = layer.open({\r\n");
      out.write("                type:1,\r\n");
      out.write("                title:'修改用户',\r\n");
      out.write("                content:$(\"#saveOrUpdateDiv\"),\r\n");
      out.write("                area:['800px','450px'],\r\n");
      out.write("                success:function(index){\r\n");
      out.write("                    form.val(\"dataFrm\",data);\r\n");
      out.write("                    url=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/user/updateUser.action\";\r\n");
      out.write("\r\n");
      out.write("                    // 地址设置初始值，如果不需要，则去掉下面这行代码\r\n");
      out.write("                    currentPicker.setValue(data.address);\r\n");
      out.write("                },\r\n");
      out.write("                cancel: function(){\r\n");
      out.write("                    currentPicker.setValue('');\r\n");
      out.write("                }\r\n");
      out.write("            });\r\n");
      out.write("        }\r\n");
      out.write("//保存\r\n");
      out.write("        form.on(\"submit(doSubmit)\",function(obj){\r\n");
      out.write("//序列化表单数据\r\n");
      out.write("            var params=$(\"#dataFrm\").serialize();\r\n");
      out.write("            $.post(url,params,function(obj){\r\n");
      out.write("                layer.msg(obj.msg);\r\n");
      out.write("//关闭弹出层\r\n");
      out.write("                layer.close(mainIndex)\r\n");
      out.write("//刷新数据 表格\r\n");
      out.write("                tableIns.reload();\r\n");
      out.write("            })\r\n");
      out.write("        });\r\n");
      out.write("\r\n");
      out.write("        $(\"#pid_div\").on(\"click\",function(){\r\n");
      out.write("            $(this).toggleClass(\"layui-form-selected\");\r\n");
      out.write("            $(\"#userSelectDiv\").toggleClass(\"layui-show layui-anim layui-anim-upbit\");\r\n");
      out.write("        });\r\n");
      out.write("\r\n");
      out.write("        // 批量删除用户\r\n");
      out.write("        function batchDeleteUsers() {\r\n");
      out.write("            var checkStatus = table.checkStatus('userTable');\r\n");
      out.write("            var data = checkStatus.data;\r\n");
      out.write("            var params = '';\r\n");
      out.write("            $.each(data, function (i, item) {\r\n");
      out.write("                if (i == 0) {\r\n");
      out.write("                    params += 'ids=' + item.userid;\r\n");
      out.write("                } else {\r\n");
      out.write("                    params += '&ids=' + item.userid;\r\n");
      out.write("                }\r\n");
      out.write("                layer.alert(params);\r\n");
      out.write("            });\r\n");
      out.write("\r\n");
      out.write("            layer.confirm('您确定要批量删除这些用户吗', function (index) {\r\n");
      out.write("                //    向服务端发送删除请求\r\n");
      out.write("                $.post('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/user/batchDeleteUser.action', params, function (res) {\r\n");
      out.write("                    layer.msg(res.msg);\r\n");
      out.write("                    //    重新刷新数据表格\r\n");
      out.write("                    tableIns.reload();\r\n");
      out.write("                })\r\n");
      out.write("            })\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("        // 打开用户分配角色弹层\r\n");
      out.write("        function openSelectUserRole(data) {\r\n");
      out.write("            layer.open({\r\n");
      out.write("                type: 1,\r\n");
      out.write("                title: '分配角色',\r\n");
      out.write("                content: $('#selectUserRole'),\r\n");
      out.write("                offset: 'auto',\r\n");
      out.write("                area: ['800px', '400px'],\r\n");
      out.write("                btn: ['确定分配', '取消分配'],\r\n");
      out.write("                btnAlign: 'c',\r\n");
      out.write("                anim: 5,\r\n");
      out.write("                yes: function(index) {\r\n");
      out.write("                    var checkStatus = table.checkStatus('roleTable');\r\n");
      out.write("                    var checkData = checkStatus.data;\r\n");
      out.write("                    var params = \"userid=\" + data.userid;\r\n");
      out.write("                    $.each(checkData, function (i, item) {\r\n");
      out.write("                        params += '&ids=' + item.roleid;\r\n");
      out.write("                    })\r\n");
      out.write("                    console.log(params);\r\n");
      out.write("                    $.post('");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/user/saveUserRole.action', params, function (res) {\r\n");
      out.write("                        layer.msg(res.msg);\r\n");
      out.write("                    })\r\n");
      out.write("                  //  点击确定分配角色之后，关闭弹层\r\n");
      out.write("                  layer.close(index);\r\n");
      out.write("                },\r\n");
      out.write("                success: function () {\r\n");
      out.write("                    //渲染数据表格\r\n");
      out.write("                    mainIndex = table.render({\r\n");
      out.write("                        elem: '#roleTable'   //渲染的目标对象\r\n");
      out.write("                        ,url:'");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/user/loadUserRole.action?userid=' + data.userid //数据接口\r\n");
      out.write("                        ,title: '角色数据表'//数据导出来的标题\r\n");
      out.write("\r\n");
      out.write("                        ,cellMinWidth:100 //设置列的最小默认宽度\r\n");
      out.write("                        ,cols: [[   //列表数据\r\n");
      out.write("                            {type: 'checkbox', fixed: 'left'}\r\n");
      out.write("                            ,{field:'roleid', title:'ID',align:'center'}\r\n");
      out.write("                            ,{field:'rolename', title:'角色名称',align:'center'}\r\n");
      out.write("                            ,{field: 'roledesc', title: '角色描述', align: 'center'}\r\n");
      out.write("                        ]]\r\n");
      out.write("                    })\r\n");
      out.write("                }\r\n");
      out.write("            })\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("    });\r\n");
      out.write("\r\n");
      out.write("    function reloadTable(id){\r\n");
      out.write("        tableIns.reload({\r\n");
      out.write("            url:\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/user/loadAllUser.action?id=\"+id\r\n");
      out.write("        })\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("</script>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
