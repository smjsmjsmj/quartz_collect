<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    String basepath=request.getContextPath();
    %>
<%@ include file="head.jsp" %>
<form class="layui-form" id="jobform" style="margin-top: 30px;width:50%;" action="">

 <div class="layui-form-item">
    <label class="layui-form-label">Job组</label>
    <div class="layui-input-block">
      <input type="text" name="JobGroup" required  lay-verify="required" placeholder="请输入Job组名称" autocomplete="off" class="layui-input">
    </div>
  </div>

  <div class="layui-form-item">
    <label class="layui-form-label">Job名称</label>
    <div class="layui-input-block">
      <input type="text" name="JobName" required  lay-verify="required" placeholder="请输入Job名称" autocomplete="off" class="layui-input">
    </div>
  </div>
  
  <div class="layui-form-item">
    <label class="layui-form-label">间隔时间</label>
    <div class="layui-input-block">
      <input type="text" name="RepeatInterval" required  lay-verify="required" placeholder="请输入间隔时间" autocomplete="off" class="layui-input">
    </div>
  </div>
  
 
   <div class="layui-form-item">
    <label class="layui-form-label">Job入口文件</label>
    <div class="layui-input-block">
      <input type="text" name="ClassPath" required  lay-verify="required" placeholder="请输入Job入口文件" autocomplete="off" class="layui-input">
    </div>
  </div>
  
  <div class="layui-form-item">
    <label class="layui-form-label">Job入口函数</label>
    <div class="layui-input-block">
      <input type="text" name="MethodName" required  lay-verify="required" placeholder="请输入Job入口函数" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">启用</label>
    <div class="layui-input-block">
      <input type="checkbox" name="jobStatus" lay-skin="switch">
    </div>
  </div>
  
  <div class="layui-form-item">
    <div class="layui-input-block">
      <button class="layui-btn" id="btn-submit" lay-submit lay-filter="formDemo">立即提交</button>
      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
    </div>
  </div>
</form>
 
<script>
	$("#btn-submit").click(function(e){
		var _$formdata=serializeToJson("jobform");
		console.log(_$formdata);
		$.post("<%=basepath%>/save",_$formdata,function(data){
			console.log(data);
		});
		e.preventDefault();
	});
	
	/**
	 * 表单序列化成json
	 * @param form
	 */
	function serializeToJson(form) {
	    var o = {};
	    var a = $("#" + form).serializeArray();
	    $.each(a, function () {
	        if (o[this.name]) {
	            if (!o[this.name].push) {
	                o[this.name] = [o[this.name]];
	            }
	            o[this.name].push(this.value || '');
	        } else {
	            o[this.name] = this.value || '';
	        }
	    });
	    return o;
	};
</script>


<%@ include file="footer.jsp" %>