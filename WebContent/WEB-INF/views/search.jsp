<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ page session="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<tiles:useAttribute id="list" name="items" classname="java.util.List" />
<section>
	<h2>Suche</h2>
	<form>
		<label for="title">Titel</label>
		<input type="text" id="title" name="title">
		<label for="title">AutorIn</label>
		<input type="text" name="author">
		<label for="isbn">ISBN</label>
		<input type="text" name="isbn">
		<label for="year">Erscheinungsjahr</label>
		<input type="text" name="year">
		<label for="category">Kategorie</label>
		<input type="text" name="category">
		<input type="submit" value="Los gehts :)">
	</form>
	
	<h3>Suchergebnisse</h3>
	<!-- Suchanfrage wird nur angezeigt, wenn der übergebene String nicht leer ist. -->
	<c:choose>
		<c:when test="${query.isEmpty()}">
			<p>Noch keine Suchanfrage gestellt.</p>
		</c:when>
		<c:otherwise>
			<p>Suchanfrage: <span class="query"><c:out value='${query}' /></span></p>
			
			<c:forEach var="item" items="${results}">
			<!-- <p><tiles:insertAttribute value="${item}" flush="true" /><p>  -->  
			<p></p>
			</c:forEach>
			<!-- Hier müssen die ganzen gefundenen Bücher hin -->
		</c:otherwise>
	</c:choose>
</section>