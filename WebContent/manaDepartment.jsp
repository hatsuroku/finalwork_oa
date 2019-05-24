<%@ page import="myDAO.DepartmentDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="myModel.DepartmentBean" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>我的公告</title>
</head>
<body>
	<%
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charSet=utf-8");
	%>
	<%
		DepartmentDAO departmentDAO = new DepartmentDAO();

		ArrayList<DepartmentBean> departmentList = departmentDAO.getAllDepartment();

		request.setAttribute("departmentList", departmentList);

	%>

	<table>
		<tr>
			<th>部门id</th>
			<th>部门名称</th>
		</tr>
		<%for(int i=0;i<departmentList.size();i++){
			DepartmentBean department = (DepartmentBean)departmentList.get(i);%>
		<tr>
			<td><%=department.getId()%></td>
			<td><%=department.getName()%></td>
			<td><a href="<%=request.getContextPath()%>/DeleteDepartment?id=<%=department.getId()%>">删除</a></td>
		</tr>
		<%
			}%>
	</table>

	<form name="myForm"
		  action="<%=request.getContextPath()%>/AddDepartment" method="post">
		<span>用户部门</span>
		<input type="text" name="name" required="required" style="width: 50%; height: auto;">
		<input type="submit" value="确定增加">
	</form>
</body>
</html>