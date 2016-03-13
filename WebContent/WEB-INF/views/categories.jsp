<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>


<section>
	<c:choose>
		<c:when test="${allCategories != null}">
			<!-- es gibt ein Attribut mit allen Kategorien, dann zeigen wir diese an -->
			<div class="page-header">
				<h1>Verfügbare Kategorien</h1>
			</div>
			<ul>
				<c:forEach var="category" items="${allCategories}">
					<li><a href='kategorie/<c:out value="${category}"/>'
						title="Zur Kategorie <c:out value="${category}"/>"><c:out
								value="${category}" /></a></li>
				</c:forEach>
			</ul>
		</c:when>
		<c:otherwise>
			<!-- es gibt kein Attribut mit allen Kategorien, also muss es ein Fehler oder eine einzelne Kategorie sein -->
			<c:choose>
				<c:when test="${param.error != null}">
					<p>Es ist ein Fehler bei der Abfrage aufgetreten. Versuchen Sie
						es zu einem späteren Zeitpunkt noch einmal.</p>
				</c:when>
				<c:otherwise>
					<div class="page-header">
						<h1>
							<c:out value='${name}' />
						</h1>
					</div>
					<c:choose>
						<c:when test="${books.size() == 0}">
							<p>Leider haben wir zu dieser Kategorie keine Bücher...</p>
						</c:when>
						<c:otherwise>
							<c:forEach var="book" items="${books}">
								<div class="col-sm-6 col-md-3">
									<div class="thumbnail">
										<img class="book-cover img-responsive center-block"
											src="<c:url value="/img/cover/${book.getIsbn()}.jpg"/>"
											title="<c:out value="Cover des Buchs '${book.getTitle()}'"/>">
										<div class="caption center-block">
											<h4>
												<c:out value="${book.getTitle()}" />
											</h4>
											<p>
												von
												<c:set var="delimiter" value="" scope="request"></c:set>
												<c:forEach var="a" items="${book.getAuthors()}">${delimiter}<c:out
														value="${a.getNameF()}" />
													<c:out value="${a.getNameL()}" />
													<c:set var="delimiter" value=", " scope="request"></c:set>
												</c:forEach>
											</p>
											<p>
												<c:out value="${book.getPrice()}" />
												€ - <a href="<c:url value='/buch/${book.getIsbn()}' />"
													title="zum Buch <c:out value='${book.getTitle()}' />">zum
													Buch</a>
											</p>
												<form action="<c:url value='warenkorb' />" method="post" id="cartForm">
													<button type="submit" form="cartForm"
														value='<c:out value="${book.getIsbn()}"></c:out>'
														name="isbn" class="btn btn-primary center-block">
														<span class="glyphicon glyphicon-shopping-cart"></span> In
														den Warenkorb
													</button>
													<sec:csrfInput />
												</form>
										</div>
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