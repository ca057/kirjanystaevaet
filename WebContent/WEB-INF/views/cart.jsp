<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %> 
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<script src="../WebContent/resources/js/jquery-2.2.1.min.js"></script>
<script src="../WebContent/resources/js/confirmOrder.js"></script>


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
	
	<form id="orderForm" method="post">
		<button id="orderButton" type="submit" formaction="<c:url value='/bestellen'/>">bestellen</button> 
	 	<c:choose>
	 		<c:when test="${cart.isEmpty()}">
	 			<p>Du kannst leider nichts bestellen. Tue doch erst etwas in Deinen Warenkorb :)</p>
	 		</c:when>
	 			<c:otherwise>
	 				<p>Hier dein Warenkorb</p>
 		 			<p id="confirmOrder" style="display:none">Bestellung aufgegeben</p>
				</c:otherwise>
			</c:choose>
 		<sec:csrfInput/>
	</form>
 </section>  