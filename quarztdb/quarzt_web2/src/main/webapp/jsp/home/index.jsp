<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="head.jsp"%>
<style>
a{
  cursor: pointer;
}

</style>

<span class="layui-breadcrumb"> <a href="<%=bath%>/">首页</a> <a><cite>Job列表</cite></a>
</span>

<!-- 内容主体区域 -->
<div style="padding: 15px;">
	<button onclick="window.location.href='<%=bath%>/add'"
		class="layui-btn layui-btn-normal"
		style="float: right; margin-bottom: 10px;">新增</button>
	<table class="layui-table">
		<colgroup>
			<col width="150">
			<col width="200">
			<col>
		</colgroup>
		<thead>

			<tr>
				<th>Job组</th>
				<th>Job名称</th>
				<th>Job描述</th>
				<th>Job 文件路径</th>
				<th>Job 入口函数</th>
				<th>执行表达式</th>
				<th>状态</th>
				<th>创建时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${joblist}" var="item">
				<tr>
					<td>${item.jobGroup}</td>
					<td>${item.jobName}</td>
					<td>${item.desc }</td>
					<td>${item.classPath }</td>
					<td>${item.methodName }</td>
					<td>${item.cronExpression }</td>
					<td>${item.jobStatus }</td>
					<td>${item.createTime}</td>
					<td><a href="javascript:;" class="start" data-id="${item.id}">开始</a>
					<a href="javascript:; " class="stop" data-id="${item.id}">停止</a></td>
				</tr>
			</c:forEach>

		</tbody>
	</table>

</div>
</div>


<script>
	//JavaScript代码区域
	layui.use('element', function() {
		var element = layui.element;

	});
</script>
<script>
$(function() {
	$(".start").click(function(){
		var _this=this;
		var _$jobId=$(this).attr("data-id");
		
		$.get("<%=bath%>/changeJobStatus",{"status":"0","jobId":_$jobId},function(data){
			window.location.reload();
		});
		
	});
	$(".stop").click(function(){
		var _this=this;
		var _$jobId=$(this).attr("data-id");
		$.get("<%=bath%>/changeJobStatus",{"status":"1","jobId":_$jobId},function(data){
			window.location.reload();
		});
	});
});
</script>


<%@ include file="footer.jsp"%>
