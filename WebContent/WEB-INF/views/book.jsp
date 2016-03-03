<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<section>
	<c:choose>
		<c:when test="${error != null}">
			<p><c:out value="${error}"></c:out></p>
			<p><a href="<c:url value='/'/>" title="zur Startseite">zur Startseite</a></p>
		</c:when>
		<c:otherwise>
			<h2><c:out value="${book.getTitle()}" /></h2>
			<p>von
				<c:set var="delimiter" value="" scope="request"></c:set><c:forEach var="a" items="${authors}">${delimiter}<c:out value="${a.getNameF()}" /> <c:out value="${a.getNameL()}" /><c:set var="delimiter" value=", " scope="request"></c:set></c:forEach>
			</p>
			<img class="book-cover" src="<c:url value="/img/cover/${book.getIsbn()}.jpg"/>" title="<c:out value="Cover des Buchs '${book.getTitle()}'"/>">
			<h4>Beschreibung</h4>
			<p><c:out value="${book.getDescription()}" escapeXml="false"></c:out></p>
			<div class="add-to-cart">
				<!-- hier brauchen wir noch ein form-element, welches das buch zum Warenkorb hinzufügt -->
				<form action="../warenkorb" method="post" id="cartForm">
					<button type="submit" form="cartForm" value='<c:out value="${book.getIsbn()}"></c:out>' name="isbn">
						<img src="<c:url value="/img/icons/ic_add_shopping_cart_black_36dp.png" />" title="Zum Warenkorb hinzufügen" />
						<p>Zum Warenkorb hinzufügen</p>
					</button>
				</form>
			</div>
		</c:otherwise>	
	</c:choose>
</section>