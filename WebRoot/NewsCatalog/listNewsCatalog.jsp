<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.shengsiyuan.imis.model.*" %>
<%@ page import="java.util.*" %>

<%@ include file="../tabsidebar.jsp" %>
<%
List<NewsCatalog> list = (List<NewsCatalog>) request.getAttribute("list");
String parentId = (String) request.getAttribute("parentId");
String upperId = (String) request.getAttribute("upperId");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% String title = "新闻分类列表"; %>
<%@ include file="../title.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<table width="400" border="0" align="center"  >
    
        <tr>
            <td align="left">
            </td>
        </tr>
        
    </table>
 
<table width="90%" align="center" >

    <tr>
        <td align="left">
           <a href="addNewsCatalog.jsp?parentId=<%= parentId%>"><img src="<%=request.getContextPath()%>/Images/btn_add.gif" border="0"/></a>
           <% if (null != upperId) { %>
           <a href="ListNewsCatalog?parentId=<%= upperId%>"><img src="<%=request.getContextPath() %>/Images/btn_back.gif" border="0" /></a>
           <%} %>
        <td>
    </tr>
    
</table>

<table width="90%" align="center" cellpadding="3" cellspacing="1" border="0" class="table" >
    <tr align="center" class="tr"> 
        <td bgcolor="eoeoeo" nowrap="nowrap">名称</td>
        <td bgcolor="eoeoeo" nowrap="nowrap">子分类</td>
        <td bgcolor="eoeoeo" nowrap="nowrap">新闻条目</td>
        <td bgcolor="eoeoeo" nowrap="nowrap">修改</td>
        <td bgcolor="eoeoeo" nowrap="nowrap">删除</td>
    </tr>
    <%
    for (int i = 0; list != null && i < list.size(); i++) {
        NewsCatalog bean = list.get(i);
    %>
    <tr align="center" class="tr">
        <td><%= bean.getName()%></td>
        <td><a href="ListNewsCatalog?parentId=<%= bean.getId()%>">子分类</a></td>
        <td>新闻条目</td>
        <td><a href="UpdatePNewCatalog?id=<%= bean.getId()%>">修改</a></td>
        <td>删除</td>
    </tr>
    <%} %>
</table>
<%@ include file="../footer.jsp" %>
</body>
</html>

