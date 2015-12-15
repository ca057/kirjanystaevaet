<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<section>
	<h2><c:out value="${book.getTitle()}" /></h2>
	<p>von
		<c:forEach var="a" items="${book.getAuthors()}">
			<c:out value="${a.getNameF()}" />&nbsp;<c:out value="${a.getNameL()}" />
		</c:forEach>
	</p>
	<h4>Beschreibung</h4>
	<p><c:out value="${book.getDescription()}"></c:out></p>
</section>