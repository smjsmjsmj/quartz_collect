<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>quartz</title>
<%
	String bath = request.getContextPath();
%>
</head>
<link rel="stylesheet" type="text/css" href="<%=bath%>/css/layui.css">
<script type="text/javascript" src="<%=bath%>/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="<%=bath%>/js/layui.js"></script>
<script type="text/javascript" src="<%=bath%>/js/lay/modules/form.js"></script>
<script type="text/javascript" src="<%=bath%>/js/lay/modules/layer.js"></script>
<script type="text/javascript" src="<%=bath%>/js/laytpl.js"></script>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
		<div class="layui-header">
			<div class="layui-logo">Quartz Demo </div>
			<!-- 头部区域（可配合layui已有的水平导航） -->
		</div>
		<div class="layui-side layui-bg-black">
			<div class="layui-side-scroll">
				<!-- 左侧导航区域（可配合layui已有的垂直导航） -->
				<ul class="layui-nav layui-nav-tree" lay-filter="test">
					<li class="layui-nav-item layui-nav-itemed layui-this">
					<a class=""
						href="<%=bath%>/">Job列表</a></li>
				</ul>
			</div>
		</div>
		<div class="layui-body">