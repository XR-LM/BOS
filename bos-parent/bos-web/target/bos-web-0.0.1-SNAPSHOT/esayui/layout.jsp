<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/js/ztree/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/ztree/jquery.ztree.all-3.5.js"></script>

</head>
<body class="easyui-layout">   
    <div data-options="region:'north',title:'North Title',split:true" style="height:100px;"></div>   
    <div data-options="region:'south',title:'South Title',split:true" style="height:100px;"></div>   
    <div data-options="region:'east',iconCls:'icon-reload',title:'East',split:true" style="width:100px;"></div>   
    <div data-options="region:'west',title:'West',split:true" style="width:200px;">
	    <div id="aa" class="easyui-accordion"  data-options="fit:true">   
		    <div title="Title1" data-options="iconCls:'icon-save'" style="overflow:auto;padding:10px;">   
		        <h3 style="color:#0099FF;">Accordion for jQuery</h3>   
		        <p>Accordion is a part of easyui framework for jQuery.     
		        It lets you define your accordion component on web page more easily.</p>   
		    </div>   
		    <div title="Title2" data-options="iconCls:'icon-reload',selected:true" style="padding:10px;">   
		        content2    
		    </div>   
		    <div title="Title3" data-options="iconCls:'icon-reload',selected:true" >   
		        content3    
		    </div>   
		</div>  
    </div>   
    <div data-options="region:'center',title:'center title'" style="padding:5px;background:#eee;">
    	<div id="aa" class="easyui-tabs" style="width:500px;height:250px;" data-options="fit:true" >   
		    <div title="Title1" data-options="iconCls:'icon-save'" >   
		        <h3 style="color:#0099FF;">Accordion for jQuery</h3>   
		        <p>Accordion is a part of easyui framework for jQuery.     
		        It lets you define your accordion component on web page more easily.</p>   
		    </div>   
		    <div title="Title2" data-options="iconCls:'icon-reload',selected:true">   
		        content2    
		    </div>   
		    <div title="Title3" data-options="iconCls:'icon-reload',selected:true" >   
		        content3    
		    </div>   
		</div>  
    
    
    
    </div>   
</body>  

</html>