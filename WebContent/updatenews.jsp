<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page import="myModel.Article"%>
<%@page import="java.util.List"%>
<%@page import="myDAO.NewsDAO"%>
<%@ page import="myDAO.DepartmentDAO" %>

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet" type="text/css" href="css/addnews.css">
<title>修改公告</title>
</head>
<body>
	<div id="head">
		<div class="logo">
			
		</div>
		<div class="title">
			<span>办公自动化系统</span>
		</div>
	</div>
	<div id="nav">
		<div class="navblock">
			<ul>
				<li><a href="javascript:void(0)" class="detail">修改公告</a></li>
			</ul>
		</div>
	</div>
		<div id="content">
		<div class="form">
				<%
					int id = Integer.parseInt(request.getParameter("id"));
					NewsDAO NewsSearch = new NewsDAO();
					DepartmentDAO departmentDAO = new DepartmentDAO();
					request.setAttribute("departmentDAO", departmentDAO);
					Article article = new Article();
					article = NewsSearch.getArticleById(id);//根据传过来的id，查询具体的新闻信息
					request.setAttribute("article", article);
				%>
			<form action="<%=request.getContextPath()%>/UpdateNewsServlet?id=${article.getId()}" method="post">
				<span>公告标题</span>
				<input type="text" name="title" required="required" style="width: 50%; height:auto;"  value="${article.getTitle()}" >
				<span>发布单位</span> 
				<input type="text" name="department_id" required="required" readonly="readonly" value="${ departmentDAO.getNameById(article.getDepartment_id())}" style="width: 50%; height:auto;">
				<span>发布时间</span> 
				<input type="date" name="date" readonly="readonly" required="required" value="${article.getDate()}" style="width: 50%; height:auto;">
				<span>公告内容</span>
				<div id="textEdit">
				<textarea rows="36" class="textarea" name="content" required="required" style="width: 100%; height:auto;">${article.getContent()}</textarea>
				</div>
				<input type="submit" value="更新">
			</form>
		</div>
	</div>
</body>
</html>
