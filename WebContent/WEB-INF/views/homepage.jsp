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

	<c:if test="${bookOfTheDay != null}">
		<article>
			<h3>Unser Buch des Tages</h3>
			<h4><c:out value="${bookOfTheDay.getTitle()}"></c:out></h4>
			<p>von
				<c:set var="delimiter" value="" scope="request"></c:set><c:forEach var="a" items="${bookOfTheDay.getAuthors()}">${delimiter}<c:out value="${a.getNameF()}" /> <c:out value="${a.getNameL()}" /><c:set var="delimiter" value=", " scope="request"></c:set></c:forEach>
			</p>
			<p><c:out value="${bookOfTheDay.getDescription()}" escapeXml="false" ></c:out></p>
			<p><c:out value="${bookOfTheDay.getPrice()}" />€ - <a href="<c:url value='/buch/${bookOfTheDay.getIsbn()}' />" title="zum Buch <c:out value='${book.getTitle()}' />">zum Buch</a></p>
		</article>
	</c:if>	
</section>