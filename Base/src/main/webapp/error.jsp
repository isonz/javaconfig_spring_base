<%@ page language="java" pageEncoding="UTF-8" import="java.util.*, java.io.*, java.text.SimpleDateFormat"%>
<%@ page isErrorPage="true" %>  
<%  
response.setStatus(HttpServletResponse.SC_OK);  
String path = request.getContextPath();  
String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path;  
%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">  
<script type="text/javascript" src="<%=path%>/js/jquery.js"></script>  
<head>  
        <title>错误页面.</title>  
</head>  
    <body>  
        <table width="100%">  
            <tr>  
                <td style="border-bottom:dotted 1px Gray;" colspan="2" >  
                      错误提示                                 
                </td><td></td>  
            </tr>  
            <tr>  
                <td style="width: 130px" >  
                    <img src="<%=path%>/images/error.png" id="error_img" />  
                </td>  
                <td>尊敬的用户：<br />系统出现了异常，请重新登录或刷新页面。  
                    <br />如果问题重复出现，请向信管部反馈。<br /><br />  
                    <a id="showErrorMessageButton" href="javascript:showErrorMessage();">详细错误信息</a>  
                </td>  
            </tr>  
        </table>  
        <div id="errorMessageDiv">  
            <pre>  
                <%  
                    try {  
                        //全部内容先写到内存，然后分别从两个输出流再输出到页面和文件  
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();  
                        PrintStream printStream = new PrintStream(byteArrayOutputStream);  
  
                        printStream.println();  
                        printStream.println("用户信息");  
                        printStream.println("访问的路径: " + request.getAttribute("javax.servlet.forward.request_uri"));  
                        printStream.println();  
  
                        printStream.println("异常信息");  
                        printStream.println(exception.getClass() + " : " + exception.getMessage());  
                        printStream.println();  
  
                        Enumeration<String> e = request.getParameterNames();  
                        if (e.hasMoreElements()) {  
                            printStream.println("请求中的Parameter包括：");  
                            while (e.hasMoreElements()) {  
                                String key = e.nextElement();  
                                printStream.println(key + "=" + request.getParameter(key));  
                            }  
                            printStream.println();  
                        }  
  
                        printStream.println("堆栈信息");  
                        exception.printStackTrace(printStream);  
                        printStream.println();  
  
                        out.print(byteArrayOutputStream);    //输出到网页  
  
                    } catch (Exception ex) {  
                        ex.printStackTrace();  
                    }  
                %>  
            </pre>  
        </div>  
         <script>  
            function showErrorMessage(){  
                $("#errorMessageDiv").toggle();  
            }  
            $(document).ready(function(){  
                showErrorMessage();  
            });  
        </script>  
    </body>  
</html>  