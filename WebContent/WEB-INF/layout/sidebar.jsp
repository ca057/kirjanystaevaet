<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ page session="false" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div id="sidebar-wrapper">
		<ul class="sidebar-nav">
			<li class="sidebar-brand"><a href="#"> Start Bootstrap </a></li>
			<li><a href="<c:url value='/backend' />" title="Dashboard">Dashboard</a></li>
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
		<li><a href="<c:url value='/backend/bestellungen' />" title="Bestellungen">Bestellungen</a></li>
	</ul> 
		</ul>
	</div>