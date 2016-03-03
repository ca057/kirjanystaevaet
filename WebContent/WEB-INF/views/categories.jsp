<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<section>
	<c:choose>
		<c:when test="${allCategories != null}">
			<!-- es gibt ein Attribut mit allen Kategorien, dann zeigen wir diese an -->
			<h2>Verfügbare Kategorien</h2>
			<ul>			
				<c:forEach var="category" items="${allCategories}">
					<li><a href='kategorie/<c:out value="${category}"/>' title="Zur Kategorie <c:out value="${category}"/>"><c:out value="${category}" /></a></li>
				</c:forEach>		
			</ul>
		</c:when>
		<c:otherwise>
			<!-- es gibt kein Attribut mit allen Kategorien, also muss es ein Fehler oder eine einzelne Kategorie sein -->
			<c:choose>
				<c:when test="${param.error != null}">
					<p>Es ist ein Fehler bei der Abfrage aufgetreten. Versuchen Sie es zu einem späteren Zeitpunkt noch einmal.</p>
				</c:when>
				<c:otherwise>
					<h2><c:out value='${name}'/></h2>
					<article>
						<p>Folgende Bücher haben wir zu <c:out value='${name}' /></p>
					</article>
				</c:otherwise>
			</c:choose>
		</c:otherwise>	
	</c:choose>
</section>