<%@ page import="java.util.*,java.util.regex.*" pageEncoding="UTF-8"%>
<html> 
<head>
<Meta http-equiv="progma" content="no-cache">
<title>圣思园信息管理与整合系统</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<%@ include file="global.css" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/style.css">

<script type="text/javascript">

    function del() {
    	if (confirm("您真的要删除吗？")) {
    		return true;
    	}
    	return false;
    }

</script>
 
</head>

<body bgcolor="#ffffff" style="margin:0px" text="#000000" link="#0000ff" vlink="#0000ff" alink="#6699cc">

<table width="100%" height="100%" cellpadding="0" cellspacing="0">
<tr>
	<td height="77" colspan="2">
		<%@ include file="header.jsp" %>
	</td>
</tr>
<tr>



<td width="157" valign="top" background="<%=request.getContextPath()%>/Images/slidbg.jpg">

<img src="<%=request.getContextPath()%>/Images/slidtop.jpg">
	<br>

<table width="100%" align="center" cellpadding="0" cellspacing="0">
<tr><td>

	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<font size="-1" color="#333333"><b>常用管理</b></font>
	
	<br><br>
	<img src="<%=request.getContextPath()%>/Images/slidsplit.jpg">
	<br>

	<div align="right"  width="141">
		<table cellpadding="0" cellspacing="0" border="0" width="141" >
				
			<tr>
				<td height="22" align="center">
					<a href="<%= request.getContextPath() %>/NewsCatalog/ListNewsCatalog?parentId=-1&start=0&range=10">新闻管理</a>
				</td>
			</tr>

			<tr>
				<td height="22" align="center">
					<a href="<%= request.getContextPath() %>/DocumentCatalog/documentCatalogTree.jsp?type=1&parentId=-1">公司文档</a>
				</td>
			</tr>
			
			<tr>
				<td height="22" align="center">
					<a href="<%= request.getContextPath() %>/DocumentCatalog/documentCatalogTree.jsp?type=2&parentId=-1">部门文档</a>
				</td>
			</tr>
			
			
			<tr>
				<td height="22" align="center">
					<a href="<%= request.getContextPath() %>/DocumentCatalog/documentCatalogTree.jsp?type=3&parentId=-1">协作文档</a>
				</td>
			</tr>
			
			<tr>
                <td height="22" align="center">
                    <a href="<%= request.getContextPath() %>/DocumentCatalog/documentCatalogTree.jsp?type=4&parentId=-1">共享文档</a>
                </td>
            </tr>
            
                        
            <tr>
                <td height="22" align="center">
                    <a href="<%= request.getContextPath() %>/DocumentCatalog/documentCatalogTree.jsp?type=5&parentId=-1">培训文档</a>
                </td>
            </tr>       
            
			
			
			
		</table>
	</div>
	<br>

	
	
</td></tr>
</table>
	
</td>

<td valign="top" width="92%">
