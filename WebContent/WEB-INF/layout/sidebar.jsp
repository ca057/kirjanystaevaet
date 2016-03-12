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
			
			<li data-toggle="collapse" data-target="#bestand"
				class="collapsed"><a href="#"><span class="glyphicon glyphicon-hdd"></span> Bestand <span class="caret"></span></a>
			</li>
			<ul class="sub-menu collapse" id="bestand">
					<li><a
						href="<c:url value='/backend/bestand#kategorien' />"><span class="glyphicon glyphicon-th-list"></span> Kategorien</a></li>
					<li><a
						href="<c:url value='/backend/bestand#autorinnen' />"><span class="glyphicon glyphicon-pencil"></span> Autor_innen</a></li>
					<li><a
						href="<c:url value='/backend/bestand#buecher' />"><span class="glyphicon glyphicon-book"></span> Bücher</a></li>
				</ul>

			<li data-toggle="collapse" data-target="#user"
				class="collapsed"><a href="#"><span class="glyphicon glyphicon-user"></span> Nutzer_innen <span class="caret"></span></a>
			</li>
			<ul class="sub-menu collapse" id="user">
					<li><a
						href="<c:url value='/backend/nutzerinnen#nutzerinnen' />"><span class="glyphicon glyphicon-cog"></span> Verwalten</a></li>
					<li><a
						href="<c:url value='/backend/nutzerinnen#anlegen' />"><span class="glyphicon glyphicon-save"></span> Anlegen</a></li>
					<li><a
						href="<c:url value='/backend/nutzerinnen#aendern' />"><span class="glyphicon glyphicon-refresh"></span> Ändern</a></li>
					<li><a
						href="<c:url value='/backend/nutzerinnen#loeschen' />"><span class="glyphicon glyphicon-trash"></span> Löschen</a></li>
				</ul>
			
			<li><a href="<c:url value='/backend/bestellungen' />"
			title="Bestellungen"><span class="glyphicon glyphicon-shopping-cart"></span> Bestellungen</a></li>
			</ul>
			
		</div>
</div>