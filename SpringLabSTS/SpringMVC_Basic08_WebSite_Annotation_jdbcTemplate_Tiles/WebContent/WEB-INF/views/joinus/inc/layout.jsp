<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<! DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<title>
			<!-- title 영역 -->
			<tiles:getAsString name="title"/>
		</title>
			<!-- css 설정 영역  -->
		    <!-- <link href="login.css" type="text/css" rel="stylesheet" /> -->
		<link href='<tiles:getAsString name="css"/>' type="text/css" rel="stylesheet" />
	</head>
	<body>
		<!-- Header 영역 -->
		<tiles:insertAttribute name="header" />
		<!-- Visual 영역 -->
		<tiles:insertAttribute name="visual" />
		<div id="main">
			<div class="top-wrapper clear">
				<!-- Content 영역 -->
				<tiles:insertAttribute name="content" />
				<!-- Aside(navi) 영역 -->
				<tiles:insertAttribute name="aside" />
			</div>
		</div>
		<!-- Footer 영역 -->
		<tiles:insertAttribute name="footer" />
	</body>
</html>
