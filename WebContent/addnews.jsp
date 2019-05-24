<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page import="myUtil.DateUtil"%>
<%@ page import="myModel.DepartmentBean" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="myDAO.DepartmentDAO" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" type="text/css" href="css/addnews.css">

<title>新增公告</title>
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

	<div id="head">
		<div class="logo">
			<!-- <img src="picture/logo.png"> -->
		</div>
		<div class="title">
			<span id="myTitle"
				onclick="window.location.href='<%=request.getContextPath()%>/index.jsp'">办公自动化系统</span>
		</div>
		<c:if test="${ username == null }">
			<div class="login">
				<a href="<%=request.getContextPath()%>/login.jsp">员工登陆</a>
			</div>
		</c:if>
		<c:if test="${ username != null }">
			<div class="login">
				<a href="<%=request.getContextPath()%>/loginsuccess.jsp">当前用户：${username}</a>
				<a href='<%=request.getContextPath()%>/logout.jsp'
					style="margin-left: 10px; font-weight: 700; font-size: 13px">注销</a>
			</div>
		</c:if>
	</div>
	<div id="nav">
		<div class="navblock">
			<ul>
				<li><a href="javascript:void(0)" class="detail">添加公告</a></li>
			</ul>
		</div>
	</div>
	<div id="content">
		<div class="form">
			<form name="myForm"
				action="<%=request.getContextPath()%>/AddNewsServlet" method="post">
				<span>公告标题</span> 
				<input type="text" name="title" required="required" style="width: 50%; height: auto;"> 
				<span>发布单位</span>
				<select name="department_id" style="width:50%; height: auto">
				<%for(int i=0;i<departmentList.size();i++){
					DepartmentBean department = (DepartmentBean)departmentList.get(i);%>
				<option value="<%=department.getId()%>"><%=department.getName()%></option>
				<%
					} %>
				</select>
				<%
					request.setAttribute("CurrentDate",DateUtil.getCurrentDateStr());
				%>
				
				<span>发布时间</span> <input type="date" name="date" value="${CurrentDate}" min="2017-01-01"
					max="2018-12-31" required="required" style="width: 50%; height: auto;"> <span>公告内容</span>

				<div id="textEdit">
					<textarea rows="36" class="textarea" name="content" id="myText"
						style="width: 100%; height: auto;"></textarea>

				</div>

				
				<input type="submit" value="发布">
			</form>
		</div>
	</div>
	<!-- <div id="bottom">
		<div class="text">Copyright ©2017-2017 WYP</div>
	</div> -->
	<script type="text/javascript">
		var title = getElementById("myText");
		alert(title.value);
		
	</script>
</body>
</html>
