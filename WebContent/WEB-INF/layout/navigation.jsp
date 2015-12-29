<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<ul class="navigation-content list-inline">
	<li><a href="<c:url value='/kategorie/php'/>">PHP</a></li>
	<li><a href="<c:url value='/kategorie/java'/>">Java</a></li>

	<li><a href="<c:url value='/suche' />" title="Suche">Suche</a></li>
	<li><a href="<c:url value='/kontakt'/>" title="Kontakt und Impressum">Kontakt/Impressum</a></li>

	<li class="float-right"><a href="<c:url value='/login'/>" title="In Mein Konto einloggen">Anmelden</a></li>
	<li class="float-right">
		<form class="form-inline" action="<c:url value="/logout" />" method="post">
			<button type="submit" value="Aus Mein Konto abmelden">Abmelden</button>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		</form>
	</li>
</ul>