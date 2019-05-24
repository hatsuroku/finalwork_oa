<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="myModel.UserBean" %>
<%@ page import="java.util.List" %>
<%@ page import="myDAO.UserDAO" %>
<%@ page import="myModel.DepartmentBean" %>
<%@ page import="myDAO.DepartmentDAO" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>查看所有用户</title>
</head>
<body>
	<%
		UserDAO userQuery = new UserDAO();
		DepartmentDAO department = new DepartmentDAO();
		List<UserBean> userList = userQuery.getAllUsers();
	%>
	<table>
		<tr>
			<th>用户名</th>
			<th>用户归属部门</th>
			<th>用户权限</th>
		</tr>
		<%
			for(int i = 0; i < userList.size(); i++) {
			    UserBean user = userList.get(i);
		%>
			<tr>
				<td><%=user.getUsername()%></td>
				<td><%=department.getNameById(user.getDepartment_id())%></td>
				<td><%=user.getPriority()%></td>
				<td><a href="<%=request.getContextPath()%>/DeleteUser?id=<%=user.getId()%>">删除</a></td>
			</tr>
		<%
			}
		%>
	</table>
	<a href="main.jsp">回到首页</a>
</body>
</html>