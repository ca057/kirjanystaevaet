<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="t"%>
<%@ page session="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>

<html>
<head lang="de">
	<title>WebShop</title>

	<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
	<meta name="viewport" content="initial-scale=1" />

	<link rel="stylesheet" href="<s:url value='/resources/css/style.css'/>">
	
	<script type="text/javascript" src="<s:url value='/resources/js/script.js'/>"></script>
</head>

<body>
	<div id="wrapper">
		<!-- Header (gleichbleibend auf allen Seiten) -->
		<header>
			<t:insertAttribute name="header"></t:insertAttribute>
		</header>

		<!-- Navigation (gleichbleibend auf allen Seiten, wird dynamisch anhand der Kategorien generiert) -->
		<nav>
			<t:insertAttribute name="navigation"></t:insertAttribute>
		</nav>
	
		<!-- Inhalt (hier folgt der jeweilige Seiteninhalt) -->
		<div id="content-wrapper">
			<t:insertAttribute name="content"></t:insertAttribute>
		</div>
	
		<!-- Footer (auf allen Seiten gleich) -->
		<footer>
			<t:insertAttribute name="footer"></t:insertAttribute>
		</footer>
	</div>
</body>
</html>