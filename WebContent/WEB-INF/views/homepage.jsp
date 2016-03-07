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

	<c:if test="${bookOfTheMoment != null}">
		<article>
			<h3>Unser Buch des Moments</h3>
			<h4><c:out value="${bookOfTheMoment.getTitle()}"></c:out></h4>
			<p>von
				<c:set var="delimiter" value="" scope="request"></c:set><c:forEach var="a" items="${bookOfTheMoment.getAuthors()}">${delimiter}<c:out value="${a.getNameF()}" /> <c:out value="${a.getNameL()}" /><c:set var="delimiter" value=", " scope="request"></c:set></c:forEach>
			</p>
			<p><c:out value="${bookOfTheMoment.getDescription()}" escapeXml="false" ></c:out></p>
			<p><c:out value="${bookOfTheMoment.getPrice()}" />€ - <a href="<c:url value='/buch/${bookOfTheMoment.getIsbn()}' />" title="zum Buch <c:out value='${bookOfTheMoment.getTitle()}' />">zum Buch</a></p>
		</article>
	</c:if>	
</section>