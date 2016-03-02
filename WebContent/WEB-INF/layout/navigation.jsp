<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page session="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<ul class="navigation-content list-inline">
	<li><a href="<c:url value='/kategorie/php'/>">PHP</a></li>
	<li><a href="<c:url value='/kategorie/mysql'/>">MySQL</a></li>

	<li><a href="<c:url value='/suche' />" title="Suche">Suche</a></li>
	<li><a href="<c:url value='/kontakt'/>" title="Kontakt und Impressum">Kontakt/Impressum</a></li>
	<li><button type="button" formaction="<c:url value='/warenkorb'/>" title="Warenkorb">Warenkorb</button></li>

	<sec:authorize access="hasRole('USER')">
		<li class="float-right"><a href="<c:url value='/meinkonto'/>" title="Mein Konto anzeigen">Mein Konto</a></li>
	</sec:authorize>
	<sec:authorize access="hasAnyRole('USER', 'ADMIN')">
		<li class="float-right">
			<form class="form-inline" action="<c:url value="/logout" />" method="post">
				<button type="submit" value="Aus Mein Konto abmelden">Abmelden</button>
				<sec:csrfInput/>
			</form>
		</li>
	</sec:authorize>
	<sec:authorize access="isAnonymous()">
		<li class="float-right"><a href="<c:url value='/login'/>" title="In Mein Konto einloggen">Anmelden</a></li>
	</sec:authorize>
</ul>

<sec:authorize access="hasRole('ADMIN')">
	<ul class="navigation-content list-inline">
		<li><a href="<c:url value='/backend' />" title="Admin-Bereich">Admin-Bereich</a></li>
		<li><a href="<c:url value='/backend/bestand' />" title="Bestand verwalten">Bestand verwalten</a>
			<ul class="navigation-content-sub">
				<li><a href="<c:url value='/backend/bestand#kategorien' />" title="Kategorien verwalten">Kategorien</a>
					<ul>
						<li><a href="<c:url value='/backend/bestand#kategorien-anlegen' />" title="Neue Kategorie anlegen">Anlegen</a></li>
						<li><a href="<c:url value='/backend/bestand#kategorien-loeschen' />" title="Bestehende Kategorie löschen">Löschen</a></li>
					</ul>
				</li>
				<li><a href="<c:url value='/backend/bestand#autorinnen' />" title="Autor:innen verwalten">Autor:innen</a>
					<ul>
						<li><a href="<c:url value='/backend/bestand#autorinnen-anlegen' />" title="Neue:n Autor:in anlegen">Anlegen</a></li>
						<li><a href="<c:url value='/backend/bestand#autorinnen-loeschen' />" title="Bestehende Autor:innen löschen">Löschen</a></li>
					</ul>
				</li>
				<li><a href="<c:url value='/backend/bestand#buecher' />" title="Autor:innen verwalten">Bücher</a>
					<ul>
						<li><a href="<c:url value='/backend/bestand#buecher' />" title="Neue Bücher anlegen">Anlegen</a></li>
						<li><a href="<c:url value='/backend/bestand#buecher-loeschen' />" title="Bestehende Bücher löschen">Löschen</a></li>
					</ul>
				</li>
			</ul>
		</li>
		<li><a href="<c:url value='/backend/nutzerinnen' />" title="Kund:innen verwalten">Nutzer:in verwalten</a>
			<ul class="navigation-content-sub">
				<li><a href="<c:url value='/backend/nutzerinnen#anlegen' />" title="Neue:n Nutzer:in anlegen">Anlegen</a></li>
				<li><a href="<c:url value='/backend/nutzerinnen#aendern' />" title="Bestehende Nutzer:in ändern">Ändern</a></li>
				<li><a href="<c:url value='/backend/nutzerinnen#loeschen' />" title="Bestehende Nutzer:in löschen">Löschen</a></li>
			</ul>
		</li>
		<li><a href="<c:url value='/backend/nutzungsstatistiken' />" title="Nutzungsstatistiken">Nutzungsstatistiken</a></li>
		<li><a href="<c:url value='/backend/einstellungen' />" title="Einstellungen">Einstellungen</a></li>
	</ul>
</sec:authorize>