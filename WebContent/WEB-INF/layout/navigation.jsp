<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<ul class="navigation-content list-inline">
	<!-- das sollte hier eigentlich dynamisch generiert werden -->
	<li><a href="<c:url value='/kategorie/php'/>">PHP</a></li>
	<li><a href="<c:url value='/kategorie/java'/>">Java</a></li>
	<!-- Und diesen Menüpunkt gibt es immer -->
	<li><a href="<c:url value='/suche' />" title="Suche">Suche</a></li>
	<li><a href="<c:url value='/kontakt'/>" title="Kontakt und Impressum">Kontakt/Impressum</a></li>
</ul>