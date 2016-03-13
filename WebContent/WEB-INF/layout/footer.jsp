<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="col-xs-12 footer">
	<ul class="list-inline">
		<li><a href="#" title="zurück nach oben" class="to-top">nach
				oben</a></li>
		<li><a href="<c:url value='/'/>" title="zur Startseite">Startseite</a></li>
		<li><a href="<c:url value='/login'/>" title="zur Login">zum
				Login</a></li>
		<li><a href="<c:url value='/kontakt#kontakt'/>"
			title="Kontakt und Impressum">Kontakt/Impressum</a></li>
	</ul>
	<ul class="list-inline">
		<li>&copy; 2015 O/R/T/T &ndash; <a href="http://www.wtfpl.net/"
			title="WTFPL License">WTFPL License</a></li>
	</ul>
</div>
