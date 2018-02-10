<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.shengsiyuan.imis.model.*" %>
<%@ page import="java.util.*" %>

<%@ include file="../tabsidebar.jsp" %>
<%
List<NewsCatalog> list = (List<NewsCatalog>) request.getAttribute("list");
String parentId = request.getParameter("parentId");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% String title = "新闻分类列表"; %>
<%@ include file="../title.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
    <script type="text/javascript" src="../lib/jquery.js"></script>
    <script type="text/javascript">
        function validateForm() {
        	if($("#name").val() == "") {
        		alert("请先输入内容!");
        		return false;
        	}
        }
    </script>
</head>
<body>

<table width="400" border="0" align="center"  >
    
        <tr>
            <td align="left">
            </td>
        </tr>
        
    </table>
<form action="AddNewsCatalog" method="post" onsubmit="return validateForm();">
    <input type="hidden" name="parentId" value="<%= parentId %>" />
	<table width="30%" align="center" class="table" >
	    <tr class="tr">
	        <td bgcolor="eoeoeo" nowrap="nowrap"><input style="width: 100%" type="text" id="name" name="name" /></td>
	    </tr>
	
	    <tr class="tr">
	        <td bgcolor="eoeoeo" nowrap="nowrap" align="center">
	            <input type="submit" value="提交"/>&nbsp;
	            <input type="button" value="返回" onclick="javascript:history.go(-1)" />
	        </td>
	    </tr>
	    
	    
	</table>
</form>

<%@ include file="../footer.jsp" %>
</body>
</html>

