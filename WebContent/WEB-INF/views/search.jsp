<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<section>
	<h2>Suche</h2>
	<form>
		<label for="title">Titel</label>
		<input type="text" id="title" name="title"><br>
		<label for="title">AutorIn</label>
		<input type="text" name="author"><br>
		<label for="isbn">ISBN</label>
		<input type="text" name="isbn"><br>
		<label for="year">Erscheinungsjahr</label>
		<input type="text" name="year"><br>
		<label for="category">Kategorie</label>
		<input type="text" name="category"><br>
		<input type="submit" value="Los gehts :)">
	</form>
	
	<h3>Suchergebnisse</h3>
	<!-- Suchanfrage wird nur angezeigt, wenn der übergebene String nicht leer ist. -->
	<c:choose>
		<c:when test="${query.isEmpty()}">
			<p>Noch keine Suchanfrage gestellt.</p>
		</c:when>
		<c:otherwise>
			<p><span class="font-bold">Suchanfrage:</span> <span class="query"><c:out value='${query}' /></span></p>
			
			<!-- Hier müssen die ganzen gefundenen Bücher hin -->
		</c:otherwise>
	</c:choose>
</section>