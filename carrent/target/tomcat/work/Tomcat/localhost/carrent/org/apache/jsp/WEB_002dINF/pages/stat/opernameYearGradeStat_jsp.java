/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2020-12-06 02:20:16 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.pages.stat;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class opernameYearGradeStat_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html style=\"height: 100%\">\r\n");
      out.write("<head>\r\n");
      out.write("    <meta charset=\"utf-8\">\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/resources/layui/css/layui.css\" media=\"all\" />\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/resources/css/public.css\" media=\"all\" />\r\n");
      out.write("</head>\r\n");
      out.write("<body style=\"height: 100%; margin: 0\">\r\n");
      out.write("<!-- 搜索条件开始 -->\r\n");
      out.write("<fieldset class=\"layui-elem-field layui-field-title\" style=\"margin-top: 20px;\">\r\n");
      out.write("    <legend>查询条件</legend>\r\n");
      out.write("</fieldset>\r\n");
      out.write("<form class=\"layui-form\" method=\"post\" id=\"searchFrm\">\r\n");
      out.write("    <div class=\"layui-form-item\">\r\n");
      out.write("        <div class=\"layui-inline\">\r\n");
      out.write("            <div class=\"layui-input-inline\">\r\n");
      out.write("                <input type=\"text\" name=\"year\" placeholder=\"请输入要查询的年份\" autocomplete=\"off\" id=\"year\" class=\"layui-input\">\r\n");
      out.write("            </div>\r\n");
      out.write("            <button type=\"button\" class=\"layui-btn layui-btn-normal  layui-icon layui-icon-search\" id=\"doSearch\">查询</button>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("</form>\r\n");
      out.write("\r\n");
      out.write("<!-- 搜索条件结束 -->\r\n");
      out.write("<div id=\"container\" style=\"height: 100%\"></div>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/resources/echarts/echarts.min.js\"></script>\r\n");
      out.write("<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/resources/layui/layui.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("    var $;\r\n");
      out.write("    layui.use(['jquery', 'laydate'], function () {\r\n");
      out.write("        var laydate = layui.laydate;\r\n");
      out.write("        $ = layui.jquery;\r\n");
      out.write("\r\n");
      out.write("        var url = '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/stat/opernameYearGradeStatJson.action';\r\n");
      out.write("\r\n");
      out.write("        laydate.render({\r\n");
      out.write("            elem: '#year',\r\n");
      out.write("            type: 'year',\r\n");
      out.write("            value: new Date()\r\n");
      out.write("        })\r\n");
      out.write("\r\n");
      out.write("        $('#doSearch').click(function() {\r\n");
      out.write("            var year = $('#year').val();\r\n");
      out.write("            console.log(year);\r\n");
      out.write("            $.get(url, {year: year}, function (data) {\r\n");
      out.write("                getData(data);\r\n");
      out.write("            })\r\n");
      out.write("        })\r\n");
      out.write("    })\r\n");
      out.write("\r\n");
      out.write("    $(function() {\r\n");
      out.write("        getData($('#year').val());\r\n");
      out.write("    })\r\n");
      out.write("    \r\n");
      out.write("    // 获取数据\r\n");
      out.write("    function getData(data) {\r\n");
      out.write("        var dom = document.getElementById(\"container\");\r\n");
      out.write("        var myChart = echarts.init(dom);\r\n");
      out.write("        var app = {};\r\n");
      out.write("        option = null;\r\n");
      out.write("        option = {\r\n");
      out.write("            color: ['#3398DB'],\r\n");
      out.write("            tooltip: {\r\n");
      out.write("                trigger: 'axis',\r\n");
      out.write("                axisPointer: {            // 坐标轴指示器，坐标轴触发有效\r\n");
      out.write("                    type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'\r\n");
      out.write("                }\r\n");
      out.write("            },\r\n");
      out.write("            grid: {\r\n");
      out.write("                left: '3%',\r\n");
      out.write("                right: '4%',\r\n");
      out.write("                bottom: '3%',\r\n");
      out.write("                containLabel: true\r\n");
      out.write("            },\r\n");
      out.write("            xAxis: [\r\n");
      out.write("                {\r\n");
      out.write("                    type: 'category',\r\n");
      out.write("                    data: data.name,\r\n");
      out.write("                    axisTick: {\r\n");
      out.write("                        alignWithLabel: true\r\n");
      out.write("                    }\r\n");
      out.write("                }\r\n");
      out.write("            ],\r\n");
      out.write("            yAxis: [\r\n");
      out.write("                {\r\n");
      out.write("                    type: 'value'\r\n");
      out.write("                }\r\n");
      out.write("            ],\r\n");
      out.write("            series: [\r\n");
      out.write("                {\r\n");
      out.write("                    name: '直接访问',\r\n");
      out.write("                    type: 'bar',\r\n");
      out.write("                    barWidth: '60%',\r\n");
      out.write("                    data: data.value\r\n");
      out.write("                }\r\n");
      out.write("            ]\r\n");
      out.write("        };\r\n");
      out.write("\r\n");
      out.write("        if (option && typeof option === \"object\") {\r\n");
      out.write("            myChart.setOption(option, true);\r\n");
      out.write("        }\r\n");
      out.write("    }\r\n");
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
