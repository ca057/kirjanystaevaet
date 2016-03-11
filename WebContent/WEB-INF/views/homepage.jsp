<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<section>
	<c:if test="${param.logout != null}">
		<p class="good-bye">Danke für deinen Besuch &ndash; bis zum
			nächsten Mal!</p>
	</c:if>
	<div class="page-header">
		<h1>Tipps aus unserem Sortiment</h1>
	</div>
	
	<div class="row row-eq-height">
		<div class="col-sm-6 col-md-3">
			<div class="thumbnail">
				<article>
					<img class="book-cover img-responsive center-block"
						src="<c:url value="/img/cover/${recommendations.get(0).getIsbn()}.jpg"/>"
						title="<c:out value="Cover des Buchs '${recommendations.get(0).getTitle()}'"/>">
					<div class="caption">
						<h4>
							<c:out value="${recommendations.get(0).getTitle()}"></c:out>
						</h4>

						<p>
							von
							<c:set var="delimiter" value="" scope="request"></c:set>
							<c:forEach var="a" items="${recommendations.get(0).getAuthors()}">${delimiter}<c:out
									value="${a.getNameF()}" />
								<c:out value="${a.getNameL()}" />
								<c:set var="delimiter" value=", " scope="request"></c:set>
							</c:forEach>
						</p>
						<!--  <p><c:out value="${bookOfTheMoment.getDescription()}" escapeXml="false" ></c:out></p>  -->
						<p>
							<c:out value="${recommendations.get(0).getPrice()}" />
							€
						<p>
							<a
								href="<c:url value='/buch/${recommendations.get(0).getIsbn()}' />"
								class="btn btn-primary center-block" role="button">zum Buch</a>
						</p>
					</div>
				</article>
			</div>
		</div>
		<div class="col-sm-6 col-md-3">
			<div class="thumbnail">
				<article>
					<img class="book-cover img-responsive center-block"
						src="<c:url value="/img/cover/${recommendations.get(1).getIsbn()}.jpg"/>"
						title="<c:out value="Cover des Buchs '${recommendations.get(1).getTitle()}'"/>">
					<div class="caption">
						<h4>
							<c:out value="${recommendations.get(1).getTitle()}"></c:out>
						</h4>
						<p>
							von
							<c:set var="delimiter" value="" scope="request"></c:set>
							<c:forEach var="a" items="${recommendations.get(1).getAuthors()}">${delimiter}<c:out
									value="${a.getNameF()}" />
								<c:out value="${a.getNameL()}" />
								<c:set var="delimiter" value=", " scope="request"></c:set>
							</c:forEach>
						</p>
						<!--  <p><c:out value="${bookOfTheMoment.getDescription()}" escapeXml="false" ></c:out></p>  -->
						<p>
							<c:out value="${recommendations.get(1).getPrice()}" />
							€
						<p>
							<a
								href="<c:url value='/buch/${recommendations.get(1).getIsbn()}' />"
								class="btn btn-primary center-block" role="button">zum Buch</a>
						</p>
					</div>
				</article>
			</div>
		</div>
		<div class="col-sm-6 col-md-3">
			<div class="thumbnail">
				<article>
					<img class="book-cover img-responsive center-block"
						src="<c:url value="/img/cover/${recommendations.get(2).getIsbn()}.jpg"/>"
						title="<c:out value="Cover des Buchs '${recommendations.get(2).getTitle()}'"/>">
					<div class="caption">
						<h4>
							<c:out value="${recommendations.get(2).getTitle()}"></c:out>
						</h4>
						<p>
							von
							<c:set var="delimiter" value="" scope="request"></c:set>
							<c:forEach var="a" items="${recommendations.get(2).getAuthors()}">${delimiter}<c:out
									value="${a.getNameF()}" />
								<c:out value="${a.getNameL()}" />
								<c:set var="delimiter" value=", " scope="request"></c:set>
							</c:forEach>
						</p>
						<!--  <p><c:out value="${bookOfTheMoment.getDescription()}" escapeXml="false" ></c:out></p>  -->
						<p>
							<c:out value="${recommendations.get(2).getPrice()}" />
							€
						<p>
							<a
								href="<c:url value='/buch/${recommendations.get(2).getIsbn()}' />"
								class="btn btn-primary center-block" role="button">zum Buch</a>
						</p>
					</div>
				</article>
			</div>
		</div>
		<div class="col-sm-6 col-md-3">
			<div class="thumbnail center-block">
				<article>
					<img class="book-cover img-responsive center-block"
						src="<c:url value="/img/cover/${recommendations.get(3).getIsbn()}.jpg"/>"
						title="<c:out value="Cover des Buchs '${recommendations.get(2).getTitle()}'"/>">
					<div class="caption">
						<h4>
							<c:out value="${recommendations.get(3).getTitle()}"></c:out>
						</h4>
						<p>
							von
							<c:set var="delimiter" value="" scope="request"></c:set>
							<c:forEach var="a" items="${recommendations.get(2).getAuthors()}">${delimiter}<c:out
									value="${a.getNameF()}" />
								<c:out value="${a.getNameL()}" />
								<c:set var="delimiter" value=", " scope="request"></c:set>
							</c:forEach>
						</p>
						<!--  <p><c:out value="${bookOfTheMoment.getDescription()}" escapeXml="false" ></c:out></p>  -->
						<p>
							<c:out value="${recommendations.get(3).getPrice()}" />
							€
						<p>
							<a
								href="<c:url value='/buch/${recommendations.get(3).getIsbn()}' />"
								class="btn btn-primary center-block" role="button">zum Buch</a>
						</p>
					</div>
				</article>
			</div>
		</div>
	</div>
</section>