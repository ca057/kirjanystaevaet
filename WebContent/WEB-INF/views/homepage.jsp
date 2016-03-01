<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<section>
	<c:if test="${param.logout != null}">
	    <p class="good-bye">Danke für deinen Besuch &ndash; bis zum nächsten Mal!</p>
	</c:if>
	<div id="searchfield">
		<form action="suche" method="get">
			<input type="text" name="all" placeholder="Welches Buch suchst du?" required/>
			<input type="submit" value="Suchen" />
		</form>
	</div>

	<article>
		<h2>Unsere Adventskracher</h2>
		<h3>Eine Unterüberschrift</h3>
		<p>Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.</p>
		<h3>Eine zweite Unterüberschrift</h3>
		<p>Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.</p>
	</article>

	<article>
		<h2>Unsere Weihnachtskracher</h2>
		<p>Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.</p>
	</article>
	
	<a href="<c:url value='/buch/059600916X'/>" title="zumBuch">Zum Buch</a>
	
		<h2><c:out value="${book.getTitle()}" /></h2>
	<p>von <c:set var="delimiter" value="" scope="request"></c:set><c:forEach var="a" items="${book.getAuthors()}">${delimiter}<c:out value="${a.getNameF()}" /> <c:out value="${a.getNameL()}" /><c:set var="delimiter" value=", " scope="request"></c:set></c:forEach></p>
	<h4>Beschreibung</h4>
	<p><c:out value="${book.getDescription()}"></c:out></p>
	<img class="book-cover" src="<c:url value="/img/cover/${book.getIsbn()}.jpg"/>" title="<c:out value="Cover des Buchs '${book.getTitle()}'"/>">

	<div class="add-to-cart">
		<!-- hier brauchen wir noch ein form-element, welches das buch zum Warenkorb hinzufügt -->
		<form action="../warenkorb" method="post" id="cartForm">
			<button type="submit" form="cartForm" value='<c:out value="/buch/059600916X"></c:out>' name="isbn">
				<img src="<c:url value="/img/icons/ic_add_shopping_cart_black_36dp.png" />" title="Zum Warenkorb hinzufügen" />
				<p>Zum Warenkorb hinzufügen</p>
			</button>
		</form>
	</div>
</section>