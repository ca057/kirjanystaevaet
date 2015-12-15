<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<section>
	<h2><c:out value="${book.getTitle()}" /></h2>
	<p>von <c:set var="delimiter" value="" scope="request"></c:set><c:forEach var="a" items="${book.getAuthors()}">${delimiter}<c:out value="${a.getNameF()}" />&nbsp;<c:out value="${a.getNameL()}" /><c:set var="delimiter" value=", " scope="request"></c:set></c:forEach></p>
	<h4>Beschreibung</h4>
	<p><c:out value="${book.getDescription()}"></c:out></p>
	<img src="<c:url value="/resources/img/cover/${book.getIsbn()}.jpg"/>" title="<c:out value="Cover des Buchs '${book.getTitle()}'"/>">
</section>