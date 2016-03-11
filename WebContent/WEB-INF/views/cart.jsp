<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %> 
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>



<section>
	<div class="page-header">
	<h1>Warenkorb</h1>
	</div>
	<p>Hallo <c:out value="${name}" />!</p>
 	<h2>Bücher in deinem Warenkorb</h2> 
		<c:choose>
			<c:when test="${bookItems.isEmpty()}">
				<p>Keine Bücher im Warenkorb</p>
			</c:when>
			<c:otherwise>
				<c:forEach var="book" items="${bookItems}">
					<img class="book-cover" src="<c:url value="/img/cover/${book.key.getIsbn()}.jpg"/>" title="<c:out value="Cover des Buchs '${book.key.getTitle()}'"/>">
					<h3><c:out value="${book.key.getTitle()}"/></h3>
					<p><c:out value="${book.key.getDescription()}" escapeXml="false"/></p>
					<p><c:out value="${book.key.getPages()}"/></p>
					<p><c:out value="${book.key.getPublisher()}"/></p>
	 				<p>ISBN: <c:out value="${book.key.getIsbn()}"/></p>
					<p>Preis: <c:out value="${book.key.getPrice()}"/>€</p>

					<form id="deleteBook" method="post">
						<button class="btn btn-default btn-sm" id="deleteButton" name="isbn" value="${book.key.getIsbn()}" type="submit" formaction="<c:url value='/buch_geloescht'/>">
							<span class="glyphicon glyphicon-remove-circle"></span>
							aus Warenkorb entfernen
						</button> 
						<sec:csrfInput/>
					</form>	
				</c:forEach> 
			</c:otherwise>
		</c:choose>
		<p>Gesamtpreis: <c:out value="${sum}"/>€</p>
	
	<form id="orderForm" method="post">
		<button class="btn btn-default btn-sm" id="orderButton" type="submit" formaction="<c:url value='/bestellung_aufgegeben'/>">
			<span class="glyphicon glyphicon-ok"></span>
			bestellen
		</button> 
		<sec:csrfInput/>
	</form>	
	
 </section>  