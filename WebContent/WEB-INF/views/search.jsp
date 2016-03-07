<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ page session="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<section>
	<h2>Suche</h2>
	<form>
		<label for="title">Titel</label>
		<input type="text" id="title" name="title">
		<label for="authorFirst">Vorname</label>
		<input type="text" id="authorFirst" name="authorFirst">
		<label for="authorLast">Nachname</label>
		<input type="text" id="authorLast" name="authorLast">
		<label for="isbn">ISBN</label>
		<input type="text" id="isbn" name="isbn">
		<label for="year">Erscheinungsjahr</label>
		<input type="text" id="year" name="year">
		<label for="category">Kategorie</label>
		<input type="text" id="category" name="category">
		<input type="submit" value="Los gehts :)">
	</form>
	
	<h3>Suchergebnisse</h3>
	<!-- Suchanfrage wird nur angezeigt, wenn der übergebene String nicht leer ist. -->
	<c:if test="${error != null}">
		<p><c:out value="${error}"></c:out></p>
	</c:if>
	<c:choose>
		<c:when test="${query.isEmpty()}">
			<p>Noch keine Suchanfrage gestellt.</p>
		</c:when>
		<c:otherwise>
			<p><span class="font-bold">Suchanfrage:</span> <span class="query"><c:out value='${query}' /></span></p>
			<c:choose>
				<c:when test="${results.isEmpty()}">
					<p>Keine Suchergebnisse</p>
				</c:when>
				<c:otherwise>
				
					<c:forEach var="book" items="${results}">
						<h4><c:out value="${book.getTitle()}"/></h4>
						<p><c:out value="${book.getDescription()}" escapeXml="false"/></p>
						<p><c:out value="${book.getPages()}"/></p>
						<p><c:out value="${book.getPublisher()}"/></p>
						<p><c:out value="${book.getIsbn()}"/></p>
						<p><c:out value="${book.getPrice()}"/></p>
					</c:forEach> 
				</c:otherwise>
			</c:choose>
		</c:otherwise>
	</c:choose>
</section>