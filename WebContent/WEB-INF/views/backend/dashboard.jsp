<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page session="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<section>
	<h2><c:out value="${admin.getName()}" /> <c:out value="${admin.getSurname()}" />, willkommen im Dashboard!</h2>
	<article>
		<h3>Allgemeine Daten</h3>
		<p>Anzahl aller Bücher: 
			<c:choose>
				<c:when test="${amountOfBooks != null}"><c:out value="${amountOfBooks}"></c:out></c:when>
				<c:otherwise><em>Anzahl konnte derzeit nicht abgefragt werden.</em></c:otherwise>
			</c:choose>
		</p>
		<p>Anzahl aller Kategorien: 
			<c:choose>
				<c:when test="${amountOfCategories != null}"><c:out value="${amountOfCategories}"></c:out></c:when>
				<c:otherwise><em>Anzahl konnte nicht abgefragt werden.</em></c:otherwise>
			</c:choose>
		</p>
		<p>Anzahl aller Autor:innen: 
			<c:choose>
				<c:when test="${amountOfAuthors != null}"><c:out value="${amountOfAuthors}"></c:out></c:when>
				<c:otherwise><em>Anzahl konnte nicht abgefragt werden.</em></c:otherwise>
			</c:choose>
		</p>
		<p>Anzahl aller Nutzer:innen: 
			<c:choose>
				<c:when test="${amountOfUsers != null}">
					<c:out value="${amountOfUsers}"></c:out> (Nutzer:innen: <c:out value="${amountOfUsersUSER}"></c:out>; Administrator:innen: <c:out value="${amountOfUsersADMIN}"></c:out>)
				</c:when>
				<c:otherwise><em>Anzahl konnte nicht abgefragt werden.</em></c:otherwise>
			</c:choose>
		</p>
	</article>
	
	<article>
		<h3>Verkaufszahlen</h3>
		<h4>Die 5 meist verkauften Bücher</h4>
		<c:choose>
			<c:when test="${topSellers.isEmpty()}">
				<p>Es liegen leider noch keine Daten vor.</p>
			</c:when>
			<c:otherwise>
				<ol>
					<c:forEach var="t" items="${topSellers}" >
						<li>Test</li>
						<li><c:out value="${t.key.getTitle()}"></c:out>: <c:out value="${t.value.toString()}"></c:out></li>
					</c:forEach>	
				</ol>
			</c:otherwise>
		</c:choose>

		<h4>Die 5 am schlechtesten verkauften Bücher</h4>
		<c:choose>
			<c:when test="${shelfWarmers.isEmpty()}">
				<p>Es liegen leider noch keine Daten vor.</p>
			</c:when>
			<c:otherwise>
				<ol>
					<c:forEach var="s" items="${shelfWarmers}" >
						<li><c:out value="${s.key.getTitle()}"></c:out>: <c:out value="${s.value.toString()}"></c:out></li>
					</c:forEach>	
				</ol>
			</c:otherwise>
		</c:choose>
	</article>
	<article>
		<h3>Besuchszahlen</h3>
		<h4>Die 5 am häufigsten besuchten Bücher (in der Einzelansicht)</h4>
		<c:choose>
			<c:when test="${mostVisitedBooks.isEmpty()}">
				<p>Es liegen leider noch keine Daten vor.</p>
			</c:when>
			<c:otherwise>
				<ol>
					<c:forEach var="m" items="${mostVisitedBooks}" >
						<li><c:out value="${m.key.getTitle()}"></c:out>: <c:out value="${m.value}"></c:out></li>
					</c:forEach>	
				</ol>
			</c:otherwise>
		</c:choose>
		
		<h4>Die 5 am seltensten besuchten Bücher (in der Einzelansicht)</h4>
		<c:choose>
			<c:when test="${leastVisitedBooks.isEmpty()}">
				<p>Es liegen leider noch keine Daten vor.</p>
			</c:when>
			<c:otherwise>
				<ol>
					<c:forEach var="l" items="${leastVisitedBooks}" >
						<li><c:out value="${l.key.getTitle()}"></c:out>: <c:out value="${l.value}"></c:out></li>
					</c:forEach>	
				</ol>
			</c:otherwise>
		</c:choose>
	</article>
</section>
