<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<section>
	<h2>Warenkorb</h2>
	<article>
		<p>Hallo Kleines, ich bin dein Warenkorb. Schau mal, was schon in mir liegt :) <br/><c:out value="<c:url value="/img/cover/${book.getIsbn()}.jpg"/>" /></p>
<!--TODO: show added items  -->
		<c:choose>
			<c:when test="${query.isEmpty()}">
				<p>Noch keine Bücher im Warenkorb</p>
			</c:when>
			<c:otherwise>
				<h4><c:out value="${book.getTitle()}"/></h4>
				<p><c:out value="${book.getPages()}"/></p>
				<p><c:out value="${book.getPublisher()}"/></p>
				<p><c:out value="${book.getIsbn()}"/></p>
				<p><c:out value="${book.getPrice()}"/></p>
			</c:otherwise>
		</c:choose>	
	
	</article>
</section>