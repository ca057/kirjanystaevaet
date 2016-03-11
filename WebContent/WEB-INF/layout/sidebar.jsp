<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ page session="false" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="nav-side-menu">
	<i class="toggle-btn" data-toggle="collapse"
		data-target="#menu-content"></i>
	<div class="menu-list">
		<ul id="menu-content" class="menu-content collapse out">
			 
			<li><a href="<c:url value='/backend' />"><span class="glyphicon glyphicon-dashboard"></span> Dashboard
			</a></li>
			
			<li><a href="<c:url value='/backend/bestand' />"
				title="Bestand verwalten"><span class="glyphicon glyphicon-hdd"></span> Bestand</a>
			</li>
			
			<li data-toggle="collapse" data-target="#categories"
				class="collapsed"><a href="#"><span class="glyphicon glyphicon-th-list"></span> Kategorien <span class="caret"></span></a>
			</li>
			<ul class="sub-menu collapse" id="categories">
					<li><a
						href="<c:url value='/backend/bestand#kategorien' />">Verwalten</a></li>
					<li><a
						href="<c:url value='/backend/bestand#kategorien-anlegen' />">Anlegen</a></li>
					<li><a
						href="<c:url value='/backend/bestand#kategorien-loeschen' />">Löschen</a></li>
				</ul>


			<li data-toggle="collapse" data-target="#authors"
				class="collapsed"><a href="#"><span class="glyphicon glyphicon-pencil"></span> Autor_innen <span class="caret"></span></a>
			</li>
			<ul class="sub-menu collapse" id="authors">
					<li><a
						href="<c:url value='/backend/bestand#autorinnen' />">Verwalten</a></li>
					<li><a
						href="<c:url value='/backend/bestand#autorinnen-anlegen' />">Anlegen</a></li>
					<li><a
						href="<c:url value='/backend/bestand#autorinnen-loeschen' />">Löschen</a></li>
				</ul>
				
			<li data-toggle="collapse" data-target="#books"
				class="collapsed"><a href="#"><span class="glyphicon glyphicon-book"></span> Bücher <span class="caret"></span></a>
			</li>
			<ul class="sub-menu collapse" id="books">
					<li><a
						href="<c:url value='/backend/bestand#buecher' />">Verwalten</a></li>
					<li><a
						href="<c:url value='/backend/bestand#buecher-anlegen' />">Anlegen</a></li>
					<li><a
						href="<c:url value='/backend/bestand#buecher-loeschen' />">Löschen</a></li>
				</ul>
				
			<li data-toggle="collapse" data-target="#user"
				class="collapsed"><a href="#"><span class="glyphicon glyphicon-user"></span> Nutzer_innen <span class="caret"></span></a>
			</li>
			<ul class="sub-menu collapse" id="user">
					<li><a
						href="<c:url value='/backend/nutzerinnen#nutzerinnen' />">Verwalten</a></li>
					<li><a
						href="<c:url value='/backend/nutzerinnen#anlegen' />">Anlegen</a></li>
					<li><a
						href="<c:url value='/backend/nutzerinnen#aendern' />">Ändern</a></li>
					<li><a
						href="<c:url value='/backend/nutzerinnen#loeschen' />">Löschen</a></li>
				</ul>
			
			<li><a href="<c:url value='/backend/bestellungen' />"
			title="Bestellungen"><span class="glyphicon glyphicon-shopping-cart"></span> Bestellungen</a></li>
			</ul>
			
		</div>
</div>