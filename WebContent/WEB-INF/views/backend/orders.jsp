<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<section>
	<h2>Bestellungen</h2>
	<h3>Aktuelle Bestellungen</h3>
	<c:if test="${error != null}">
		<p class="error"><em>Fehler:</em> Bei Abfrage der Bestellungen ist ein Fehler mit der folgenden Fehlermeldung aufgetreten: <c:out value="${error}"></c:out> </p>
	</c:if>
	<c:if test="${error == null}">
		<c:choose>
			<c:when test="${orders.isEmpty()}">
				<p>Derzeit gibt es keine laufenden Bestellungen.</p>
			</c:when>
			<c:otherwise>
				<ul>
				
				</ul>
			</c:otherwise>
		</c:choose>
	</c:if>
</section>