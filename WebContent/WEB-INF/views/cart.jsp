<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page session="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %> 


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
			<div class="row">
				<c:forEach var="book" items="${bookItems}">
					
					 	<div class="col-md-3">
						 	<div class="thumbnail">
								<img class="book-cover" src="<c:url value="/img/cover/${book.key.getIsbn()}.jpg"/>" title="<c:out value="Cover des Buchs '${book.key.getTitle()}'"/>">
								<h3><c:out value="${book.key.getTitle()}"/></h3>
								<p><c:out value="${book.key.getPages()}"/> Seiten</p>
								<p>Verlag: <c:out value="${book.key.getPublisher()}"/></p>
				 				<p>ISBN: <c:out value="${book.key.getIsbn()}"/></p>
								<p>Preis: <c:out value="${book.key.getPrice()}"/>€</p>
			
								<form id="deleteBook" method="post">
									<button class="btn btn-primary btn-sm" id="deleteButton" name="isbn" value="${book.key.getIsbn()}" type="submit" formaction="<c:url value='/buch_geloescht'/>">
										<span class="glyphicon glyphicon-remove-circle"></span>
										aus Warenkorb entfernen
									</button> 
									<sec:csrfInput/>
								</form>	
							</div>
						</div>
						
				</c:forEach> 
				</div>
			</c:otherwise>
		</c:choose>
		
		<p>Gesamtpreis: <c:out value="${sum}"/>€</p>
		<form id="orderForm" method="post">
			<c:choose>
				<c:when test="${bookItems.isEmpty()}">
					<button class="btn btn-primary btn-sm" id="orderButton" type="submit" formaction="<c:url value='/bestellung_aufgegeben'/>" disabled>
						<span class="glyphicon glyphicon-ok"></span>
						bestellen
					</button> 
				</c:when>
				<c:otherwise>
					<button class="btn btn-primary btn-sm" id="orderButton" type="submit" formaction="<c:url value='/bestellung_aufgegeben'/>">
						<span class="glyphicon glyphicon-ok"></span>
						bestellen
					</button> 
				</c:otherwise>
			</c:choose>
			<sec:csrfInput/>
		</form>
 </section>  