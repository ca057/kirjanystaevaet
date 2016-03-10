<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<section>
	<c:if test="${param.logout != null}">
		<div class="col-md-12">
	    <p class="good-bye">Danke für deinen Besuch &ndash; bis zum nächsten Mal!</p>
	    </div>
	</c:if>
	<diy id="hpheading" class="col-sm-12">
		<h1>Tipps aus unserem Sortiment</h1>
	</diy>
	<div>
	<div class="row">
			<div class="col-sm-4">	
					<article>
						<div class="row">
							<h4><c:out value="${recommendations.get(0).getTitle()}"></c:out></h4>
							<div class="col-sm-8">
							<p>von
								<c:set var="delimiter" value="" scope="request"></c:set><c:forEach var="a" items="${recommendations.get(0).getAuthors()}">${delimiter}<c:out value="${a.getNameF()}" /> <c:out value="${a.getNameL()}" /><c:set var="delimiter" value=", " scope="request"></c:set></c:forEach>
							</p>
							<!--  <p><c:out value="${bookOfTheMoment.getDescription()}" escapeXml="false" ></c:out></p>  -->
							<p><c:out value="${recommendations.get(0).getPrice()}" />€ - <a href="<c:url value='/buch/${recommendations.get(0).getIsbn()}' />" title="zum Buch <c:out value='${recommendations.get(0).getTitle()}' />">zum Buch</a></p>
							</div>
							<div class="col-sm-4">
								<img class="book-cover img-responsive" src="<c:url value="/img/cover/${recommendations.get(0).getIsbn()}.jpg"/>" title="<c:out value="Cover des Buchs '${recommendations.get(0).getTitle()}'"/>">
							</div>
						</div>
					</article>
	
			</div>
			<div class="col-sm-4">
								<article>
						<div class="row">
							<h4><c:out value="${recommendations.get(1).getTitle()}"></c:out></h4>
							<div class="col-sm-8">
							<p>von
								<c:set var="delimiter" value="" scope="request"></c:set><c:forEach var="a" items="${recommendations.get(1).getAuthors()}">${delimiter}<c:out value="${a.getNameF()}" /> <c:out value="${a.getNameL()}" /><c:set var="delimiter" value=", " scope="request"></c:set></c:forEach>
							</p>
							<!--  <p><c:out value="${bookOfTheMoment.getDescription()}" escapeXml="false" ></c:out></p>  -->
							<p><c:out value="${recommendations.get(1).getPrice()}" />€ - <a href="<c:url value='/buch/${recommendations.get(1).getIsbn()}' />" title="zum Buch <c:out value='${recommendations.get(1).getTitle()}' />">zum Buch</a></p>
							</div>
							<div class="col-sm-4">
								<img class="book-cover img-responsive" src="<c:url value="/img/cover/${recommendations.get(1).getIsbn()}.jpg"/>" title="<c:out value="Cover des Buchs '${recommendations.get(1).getTitle()}'"/>">
							</div>
						</div>
					</article>
			</div>
			<div class="col-sm-4">
								<article>
						<div class="row">
							<h4><c:out value="${recommendations.get(2).getTitle()}"></c:out></h4>
							<div class="col-sm-8">
							<p>von
								<c:set var="delimiter" value="" scope="request"></c:set><c:forEach var="a" items="${recommendations.get(2).getAuthors()}">${delimiter}<c:out value="${a.getNameF()}" /> <c:out value="${a.getNameL()}" /><c:set var="delimiter" value=", " scope="request"></c:set></c:forEach>
							</p>
							<!--  <p><c:out value="${bookOfTheMoment.getDescription()}" escapeXml="false" ></c:out></p>  -->
							<p><c:out value="${recommendations.get(2).getPrice()}" />€ - <a href="<c:url value='/buch/${recommendations.get(2).getIsbn()}' />" title="zum Buch <c:out value='${recommendations.get(2).getTitle()}' />">zum Buch</a></p>
							</div>
							<div class="col-sm-4">
								<img class="book-cover img-responsive" src="<c:url value="/img/cover/${recommendations.get(2).getIsbn()}.jpg"/>" title="<c:out value="Cover des Buchs '${recommendations.get(2).getTitle()}'"/>">
							</div>
						</div>
					</article>
			</div>
	</div>
	</div>
</section>