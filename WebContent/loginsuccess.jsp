<%@ page import="myDAO.UserDAO" %>
<%@ page import="myModel.UserBean" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" type="text/css" href="css/loginsuccess.css">
<title>办公自动化</title>
</head>
<body>
	<div id="head">
		<div class="logo">
			<!-- 		<img src="picture/logo.png"> -->
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
				<li></li>
			</ul>
		</div>
	</div>
	<div id="login">
		<label for="add"></label>
		<div class="bt" id="add"
			onclick="window.location.href='<%=request.getContextPath()%>/addnews.jsp'">新增公告</div>

		<c:if test="${ priority == 10 }">
		<label for="add"></label>
		<div class="bt" id="addUser"
			onclick="window.location.href='<%=request.getContextPath()%>/addUser.jsp'">新增用户</div>
		</c:if>

		<c:if test="${ priority == 10 }">
		<label for="person"></label>
		<div class="bt" id="person"
			 onclick="window.location.href='/manaDepartment.jsp'">管理部门</div>
		</c:if>

		<c:if test="${ priority == 10 }">
		<label for="myOffice"></label>
		<div class="bt" id="myOffice"
			onclick="window.location.href='seeAllUsers.jsp'">查看所有用户</div>
		</c:if>

		<label for="searchNews"></label>
		<div class="bt" id="searchNews"
			onclick="window.location.href='SearchNews.jsp'">查询新闻</div>


		<label for="back"></label>
		<div class="bt" id="back"
			onclick="window.location.href='<%=request.getContextPath()%>/news.jsp'">返回首页</div>

	</div>
	<div id="bottom">
		<div class="text">Copyright ©2017-2017 WYP</div>
	</div>
</body>
</html>
