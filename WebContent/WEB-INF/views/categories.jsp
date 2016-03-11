<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<section>
	<c:choose>
		<c:when test="${allCategories != null}">
			<!-- es gibt ein Attribut mit allen Kategorien, dann zeigen wir diese an -->
			<div class="page-header">
			<h1>Verfügbare Kategorien</h1>
			</div>
			<ul>			
				<c:forEach var="category" items="${allCategories}">
					<li><a href='kategorie/<c:out value="${category}"/>' title="Zur Kategorie <c:out value="${category}"/>"><c:out value="${category}" /></a></li>
				</c:forEach>		
			</ul>
		</c:when>
		<c:otherwise>
			<!-- es gibt kein Attribut mit allen Kategorien, also muss es ein Fehler oder eine einzelne Kategorie sein -->
			<c:choose>
				<c:when test="${param.error != null}">
					<p>Es ist ein Fehler bei der Abfrage aufgetreten. Versuchen Sie es zu einem späteren Zeitpunkt noch einmal.</p>
				</c:when>
				<c:otherwise>
					<div class="page-header">
					<h1><c:out value='${name}'/></h1>
					</div>
					<c:choose>
						<c:when test="${books.size() == 0}">
							<p>Leider haben wir zu dieser Kategorie keine Bücher...</p>
						</c:when>
						<c:otherwise>
							<c:forEach var="book" items="${books}">
								<div id="wrapper" class="col-sm-8">
									<h4><c:out value="${book.getTitle()}" /></h4>
									<p>von
										<c:set var="delimiter" value="" scope="request"></c:set><c:forEach var="a" items="${book.getAuthors()}">${delimiter}<c:out value="${a.getNameF()}" /> <c:out value="${a.getNameL()}" /><c:set var="delimiter" value=", " scope="request"></c:set></c:forEach>
									</p>
									<p><c:out value="${book.getPrice()}" />€ - <a href="<c:url value='/buch/${book.getIsbn()}' />" title="zum Buch <c:out value='${book.getTitle()}' />">zum Buch</a></p>
									<div class="add-to-cart col-sm-4">
									<!-- hier brauchen wir noch ein form-element, welches das buch zum Warenkorb hinzufügt -->
										<form action="../warenkorb" method="post" id="cartForm">
											<button type="submit" form="cartForm" value='<c:out value="${book.getIsbn()}"></c:out>' name="isbn">
												<img src="<c:url value="/img/icons/ic_add_shopping_cart_black_36dp.png" />" title="Zum Warenkorb hinzufügen" />
												<p>In den Warenkorb</p>
											</button>
											<sec:csrfInput/>
										</form>
									</div>
								</div>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</c:otherwise>
			</c:choose>
		</c:otherwise>	
	</c:choose>
</section>