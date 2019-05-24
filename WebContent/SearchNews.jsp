<%--
  Created by IntelliJ IDEA.
  User: Kazami Hatsuroku
  Date: 2018/1/7
  Time: 12:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page import="myModel.Article"%>
<%@page import="java.util.List"%>
<%@page import="myDAO.NewsDAO"%>
<%@ page import="myModel.DepartmentBean" %>
<%@ page import="myDAO.DepartmentDAO" %>
<%@ page import="java.util.ArrayList" %>

<%
    DepartmentDAO departmentDAO = new DepartmentDAO();

    ArrayList<DepartmentBean> departmentList = departmentDAO.getAllDepartment();

    request.setAttribute("departmentList", departmentList);
%>

<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="css/addnews.css">
    <title>查找公告</title>
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
            <li><a href="javascript:void(0)" class="detail">查找公告</a></li>
        </ul>
    </div>
</div>
<div id="content">
    <div class="form">
        <form action="<%=request.getContextPath()%>/SearchNewsResult.jsp" method="post">
            <span><input type="radio" name="search_method" required="required" checked="checked" value="1" />按照标题查找</span>
            <input type="text" name="title" style="width: 50%; height:auto;" />
            <span><input type="radio" name="search_method" required="required" value="2" />按照发布单位查找</span>
            <select name="department_id" style="width:50%; height: auto">
                <%for(int i=0;i<departmentList.size();i++){
                    DepartmentBean department = (DepartmentBean)departmentList.get(i);%>
                <option value="<%=department.getId()%>"><%=department.getName()%></option>
                <%
                    } %>
            </select>
            <span><input type="radio" name="search_method" required="required" value="3" />按照内容查找</span>
            <div id="textEdit">
                <textarea rows="36" class="textarea" name="content" style="width: 100%; height:auto;"></textarea>
            </div>
            <input type="submit" value="更新">
        </form>
    </div>
</div>
</body>
</html>

