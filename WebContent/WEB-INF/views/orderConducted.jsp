<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %> 
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<section>
	<h1>Die Bestellung wurde ausgeführt</h1> 
	<p>Bald hast Du Deine neuen Bücher!</p>
	<a href="<c:url value='/'/>" title="Homepage">Zur Startseite</a>
	<a href="<c:url value='/suche'/>" title="Suche">Zur Suche</a>
	<a href="<c:url value='/meinkonto'/>" title="Konto">Zum Konto</a>
</section>