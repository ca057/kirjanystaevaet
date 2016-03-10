<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ page session="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>

<html>
<head lang="de">
	<title>WebShop</title>

	<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
	<meta name="viewport" content="initial-scale=1" />

	<link rel="stylesheet" href="<s:url value='/css/style.css'/>">
	<link rel="stylesheet" href="<s:url value='/css/login-popup.css'/>">
	
	<script type="text/javascript" src="<s:url value='/js/jquery-2.2.1.min.js' />"></script>
	<script type="text/javascript" src="<s:url value='/js/elevator.min.js' />"></script>
	<script type="text/javascript" src="<s:url value='/js/helpme.js'/>"></script>
	<script type="text/javascript" src="<s:url value='/js/script.js'/>"></script>
	
	<sec:authorize access="hasRole('ADMIN')">
		<script type="text/javascript" src="<s:url value='/js/adminscript.js'/>"></script>
	</sec:authorize>
</head>

<body>
	<div id="wrapper">
		<div id="content-wrapper">
			<header>
				<a href="<c:url value='/'/>" title="zur Startseite">
					<t:insertAttribute name="header"></t:insertAttribute>
				</a>
			</header>
	
			<nav class="shadow-1">
				<t:insertAttribute name="navigation"></t:insertAttribute>
			</nav>
		
			<div id="content">
				<t:insertAttribute name="content"></t:insertAttribute>
			</div>
		</div>
		
		<footer>
			<t:insertAttribute name="footer"></t:insertAttribute>
		</footer>
	</div>
</body>
</html>