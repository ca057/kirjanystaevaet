<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %> 


<section>
	<h2>Warenkorb</h2>
	<p>Hallo <c:out value="${name}" />, ich bin dein Warenkorb. Schau mal, was schon in mir liegt :)</p>
 	<h3>Bücher im Warenkorb</h3> 
		<c:choose>
			<c:when test="${bookItems.isEmpty()}">
				<p>Keine Bücher im Warenkorb</p>
			</c:when>
			<c:otherwise>
				<c:forEach var="book" items="${bookItems}">
					<img class="book-cover" src="<c:url value="/img/cover/${book.key.getIsbn()}.jpg"/>" title="<c:out value="Cover des Buchs '${book.key.getTitle()}'"/>">
					<h4><c:out value="${book.key.getTitle()}"/></h4>
					<p><c:out value="${book.key.getDescription()}" escapeXml="false"/></p>
					<p><c:out value="${book.key.getPages()}"/></p>
					<p><c:out value="${book.key.getPublisher()}"/></p>
	 				<p>ISBN: <c:out value="${book.key.getIsbn()}"/></p>
<%-- 					<p>Preis: <c:out value="${book.getPrice()}"/></p> --%>
				</c:forEach> 
			</c:otherwise>
		</c:choose>
	
 	<a href="<c:url value='/bestellen'/>">bestellen</a> 
 	<c:choose>
 		<c:when test="${cart.isEmpty()} ">
 			<p>Du kannst leider nichts bestellen. Tue doch erst etwas in Deinen Warenkorb :)</p>
 		</c:when>
 		<c:otherwise>
 			<p>Bestellung aufgegeben</p>
 		</c:otherwise>
 	</c:choose>
 	
</section>  