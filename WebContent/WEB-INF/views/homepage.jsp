<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<section>
	<c:if test="${param.logout != null}">
		<div class="col-md-12">
	    <p class="good-bye">Danke für deinen Besuch &ndash; bis zum nächsten Mal!</p>
	    </div>
	</c:if>
	
	<div class="row">
		<div class="col-sm-9">
			schalalalalalaa
			<!-- Fill this with life -->
		</div>
		<div class="col-sm-3">
			<h3>Unser Buch des Moments</h3>
				<c:if test="${bookOfTheMoment != null}">
					<article>
						<div class="row">
							<h4><c:out value="${bookOfTheMoment.getTitle()}"></c:out></h4>
							<div class="col-sm-8">
							<p>von
								<c:set var="delimiter" value="" scope="request"></c:set><c:forEach var="a" items="${bookOfTheMoment.getAuthors()}">${delimiter}<c:out value="${a.getNameF()}" /> <c:out value="${a.getNameL()}" /><c:set var="delimiter" value=", " scope="request"></c:set></c:forEach>
							</p>
							<!--  <p><c:out value="${bookOfTheMoment.getDescription()}" escapeXml="false" ></c:out></p>  -->
							<p><c:out value="${bookOfTheMoment.getPrice()}" />€ - <a href="<c:url value='/buch/${bookOfTheMoment.getIsbn()}' />" title="zum Buch <c:out value='${bookOfTheMoment.getTitle()}' />">zum Buch</a></p>
							</div>
							<div class="col-sm-4">
								<img class="book-cover img-responsive" src="<c:url value="/img/cover/${bookOfTheMoment.getIsbn()}.jpg"/>" title="<c:out value="Cover des Buchs '${bookOfTheMoment.getTitle()}'"/>">
							</div>
						</div>
					</article>
				</c:if>	
		</div>
	</div>
</section>