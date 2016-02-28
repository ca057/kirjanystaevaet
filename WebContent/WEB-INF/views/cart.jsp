<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %> 


<section>
	<h2>Warenkorb</h2>
	<article>
		<p>Hallo <c:out value="${name}" />, ich bin dein Warenkorb. Schau mal, was schon in mir liegt :)</p>
	</article>
 Bücher im Warenkorb 
		<c:choose>
			<c:when test="${bookItems.isEmpty()}">
				<p>Keine Bücher im Warenkorb</p>
			</c:when>
			<c:otherwise>
				<c:forEach var="book" items="${bookItems}">
<%-- 					<p><c:out value="<c:url value="/img/cover/${book.getIsbn()}.jpg"/>"/></p> --%>
					<h4><c:out value="${book.getTitle()}"/></h4>
					<p><c:out value="${book.getDescription()}" escapeXml="false"/></p>
					<p><c:out value="${book.getPages()}"/></p>
					<p><c:out value="${book.getPublisher()}"/></p>
					<p><c:out value="${book.getIsbn()}"/></p>
					<p><c:out value="${book.getPrice()}"/></p>
				</c:forEach> 
			</c:otherwise>
		</c:choose>
	
 	<a href="<c:url value='/bestellen'/>">bestellen</a> 
 	<c:choose>
 		<c:when test="${cart.isEmpty()} ">
 			<p>Du kannst leider nichts bestellen. Tue doch erst etwas in Deinen Warenkorb :)</p>
 		</c:when>
 		<c:otherwise>
 			Bestellung aufgegeben
 		</c:otherwise>
 	</c:choose>
 	
</section>  