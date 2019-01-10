<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<style>
	#panel {
		width:300px;
		border:1px solid #000;
	}
	#pheader {
		padding:10px 0px;
		background:#00f;
		color:#fff;
		border-bottom:1px solid #000;
	}
</style>
<div id="panel">
	<div id="pheader"><decorator:title  default="普通面板"/></div>
	<div id="pcontent"><decorator:body/></div>
</div>