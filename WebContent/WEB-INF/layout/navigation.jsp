<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ page session="false" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="navbar navbar-default container-fluid">
	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse"
			data-target="#navbar">
			<span class="icon-bar"></span> <span class="icon-bar"></span> <span
				class="icon-bar"></span>
		</button>
	</div>
	<div class="collapse navbar-collapse" id="navbar">
		<ul class="nav navbar-nav">
			<c:choose>
				<c:when test="${navigation != null} && ${navigation.size() < 7 }">
					<c:forEach var="nav" items="${navigation}">
						<li><a href="<c:url value='/kategorie/${nav}'/>"><c:out
									value="${nav}" /></a></li>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">Kategorien <span class="caret"></span></a>
						<ul class="dropdown-menu">
						<c:forEach var="nav" items="${navigation}">
						<li><a href="<c:url value='/kategorie/${nav}'/>"><c:out
									value="${nav}" /></a></li>
						</c:forEach>
						</ul></li>
				</c:otherwise>
			</c:choose>
			<li><a href="<c:url value='/suche' />" title="Suche">Suche</a></li>
			<li><a href="<c:url value='/kontakt'/>">Über uns</a></li>

		</ul>

		<ul class="nav navbar-nav navbar-right divider-vertical">
			<sec:authorize access="hasRole('USER')">
				<li><a href="<c:url value='/warenkorb'/>" title="Warenkorb"><span
						class="glyphicon glyphicon-shopping-cart"></span> Warenkorb</a></li>
				<li><a href="<c:url value='/meinkonto'/>"
					title="Mein Konto anzeigen"><span
						class="glyphicon glyphicon-user"></span> Konto</a></li>
			</sec:authorize>
			<sec:authorize access="hasAnyRole('USER', 'ADMIN')">
				<li>
					<form id="logout-form" action="<c:url value="/logout" />"
						method="post">
						<button class="btn" id="logout-button" type="submit"
							value="Abmelden">
							<span class="glyphicon glyphicon-log-out"></span> Abmelden
						</button>
						<sec:csrfInput />
					</form>
				</li>
			</sec:authorize>
			<sec:authorize access="isAnonymous()">
				<li class="dropdown"><a class="dropdown-toggle"
					href="<c:url value="/registrierung" />"><span
						class="glyphicon glyphicon-globe"></span> Registrieren</a></li>
				<li class="dropdown"><a class="dropdown-toggle" href="#"
					data-toggle="dropdown"><span class="glyphicon glyphicon-log-in"></span>
						Anmelden</a>
					<div class="dropdown-menu">
						<form method="post" action="<c:url value="/login" />"
							method="post">
							<input type="text" placeholder="E-Mail" id="username"
								name="username"> <input type="password"
								placeholder="Passwort" id="password" name="password"> <input
								class="btn btn-primary btn-block" type="submit" value="Anmelden">
							<sec:csrfInput />
						</form>
					</div></li>
			</sec:authorize>
		</ul>
	</div>
</div>