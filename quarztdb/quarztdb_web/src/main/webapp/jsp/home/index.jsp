<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title><%
 String bath=request.getContextPath();
%>
</head>
<script type="text/javascript" src="<%=bath %>/js/jquery-1.8.3.min.js"></script>
<body>
   home index
   <input type="button" value="stop" onclick="clicks()">
   <script type="text/javascript">
     function clicks(){
    	 $.ajax({
    		 url:"<%=bath%>/stop",
    	 });
     }
   
   </script>
</body>
</html>