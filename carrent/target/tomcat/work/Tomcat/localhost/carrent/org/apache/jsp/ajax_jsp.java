/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2020-11-13 11:56:16 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class ajax_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("    <meta charset=\"UTF-8\">\r\n");
      out.write("    <title>Title</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("    <input type=\"text\" id=\"text\">\r\n");
      out.write("    <input type=\"button\" id=\"btn\" value=\"提交\">\r\n");
      out.write("    <script src=\"resources/js/jquery-3.3.1.min.js\"></script>\r\n");
      out.write("    <script>\r\n");
      out.write("        $('#btn').click(function () {\r\n");
      out.write("            var o = $.ajax({\r\n");
      out.write("                ");
      out.write("\r\n");
      out.write("                contentType: 'application/x-www-form-urlencoded',   // 发送数据到服务器时所使用的内容类型。默认是：\"application/x-www-form-urlencoded\"\r\n");
      out.write("                type: \"GET\",    // 请求方式\r\n");
      out.write("                cache: true,    // \t布尔值，表示浏览器是否缓存被请求页面。默认是 true。\r\n");
      out.write("                scriptCharset: 'ISO8859-1',         // 规定请求的字符集。\r\n");
      out.write("                url: '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/test/login.action',\r\n");
      out.write("                async: true,  // 是否异步，默认为true\r\n");
      out.write("                data: {name: $('#text').val()},\r\n");
      out.write("                processData: false,\r\n");
      out.write("                //dataType: 'json',       // 预期的服务器响应的数据类型，如果服务器响应的数据类型和设置的不一样，发送请求会失败\r\n");
      out.write("                success: function () {      // 请求成功调用\r\n");
      out.write("                    alert('请求成功了');\r\n");
      out.write("                },\r\n");
      out.write("                error: function () {\r\n");
      out.write("                    alert('请求失败了');\r\n");
      out.write("                },\r\n");
      out.write("                beforeSend: function () {\r\n");
      out.write("                    alert('发送请求前运行的函数');\r\n");
      out.write("                },\r\n");
      out.write("                complete: function () {         // 请求完成时运行的函数（在请求成功或失败之后均调用，即在 success 和 error 函数之后）\r\n");
      out.write("                    alert('请求完成时运行的函数');\r\n");
      out.write("                },\r\n");
      out.write("                timeout: 200     // 设置本地的请求超时时间（以毫秒计）。\r\n");
      out.write("\r\n");
      out.write("            })\r\n");
      out.write("        })\r\n");
      out.write("    </script>\r\n");
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
