<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ page session="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<section>
	<h2>Suche</h2>
	
	<form class="form-inline">
		<div class="form-group">
			<label for="title">Titel</label>
			<input type="text" id="title" name="title" placeholder="Titel">
		</div>
		<div class="form-group">
			<label for="authorFirst">Vorname</label>
			<input type="text" id="authorFirst" name="authorFirst" placeholder="Vorname">
		</div>
		<div class="form-group">
			<label for="authorLast">Nachname</label>
			<input type="text" id="authorLast" name="authorLast" placeholder="Nachname">
		</div>
		<div class="form-group">
			<label for="isbn">ISBN</label>
			<input type="text" id="isbn" name="isbn" placeholder="ISBN">
		</div>
		<div class="form-group">
			<label for="year">Erscheinungsjahr</label>
			<input type="text" id="year" name="year" placeholder="Jahr">
		</div>
		<div class="form-group">
			<label for="category">Kategorie</label>
			<input type="text" id="category" name="category" placeholder="Kategorie">
		</div>
		<button class="btn btn-primary" type="submit" value="Suche">Suche</button>
	</form>
	
	
	<h2>Suchergebnisse</h2>
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
					<h5>Inhalt</h5>
						<div class="row">
							<div class="col-sm-8">
								<c:out value="${book.getDescription()}" escapeXml="false"/>
								<h5>Details</h5>
								<p>Seitenzahl: <c:out value="${book.getPages()}"/></p>
								<p>Verlag: <c:out value="${book.getPublisher()}"/></p>
								<p>ISBN: <c:out value="${book.getIsbn()}"/></p>
								<p>Preis <c:out value="${book.getPrice()}"/></p>
							</div>
							<div class="col-sm-4">
								<img class="book-cover img-responsive" src="<c:url value="/img/cover/${book.getIsbn()}.jpg"/>" title="<c:out value="Cover des Buchs '${book.getTitle()}'"/>">
							</div>
						</div>
					</c:forEach> 
				</c:otherwise>
			</c:choose>
		</c:otherwise>
	</c:choose>
</section>