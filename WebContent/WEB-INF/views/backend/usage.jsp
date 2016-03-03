<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page session="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<section>
	<h2>Nutzungsstatistiken</h2>
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
</section>