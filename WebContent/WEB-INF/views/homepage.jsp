<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>


<section>
	<c:if test="${param.logout != null}">
		<p class="good-bye">Danke für deinen Besuch &ndash; bis zum
			nächsten Mal!</p>
	</c:if>
	<div class="page-header">
		<h1>Tipps aus unserem Sortiment</h1>
	</div>

	<div class="row">
		<c:forEach var="book" items="${recommendations}">
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

						<sec:authorize access="isAnonymous() || hasRole('ADMIN') ">
								<button data-toggle="modal" data-target="#login"
									class="btn btn-primary center-block">
									<span class="glyphicon glyphicon-shopping-cart"></span> In den
									Warenkorb
								</button>
								<sec:authorize access="isAnonymous()">
									<div id="login" class="modal fade" role="dialog">
										<div class="modal-dialog">
	
											<!-- Modal content-->
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal">&times;</button>
													<h4 class="modal-title">Login nötig</h4>
												</div>
													<div class="modal-body" id="popupLoginBody">
														<p>Bitte erst als User einloggen!</p>
														<form method="post" action="<c:url value="/login" />" method="post">
															<input type="text" placeholder="E-Mail" id="username" name="username"> <input type="password" placeholder="Passwort" id="password" name="password"> <input
															class="btn btn-primary btn-block" type="submit" value="Anmelden">
															<sec:csrfInput />
														</form>
													</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-default"
														data-dismiss="modal">Close</button>
												</div>
											</div>
	
										</div>
									</div>
								</sec:authorize>
						</sec:authorize>
						<sec:authorize access="hasRole('USER')">
							<form action="<c:url value='categories' />" method="post"
								id="cartForm">
								<button type="submit" form="cartForm"
									value='<c:out value="${book.getIsbn()}"></c:out>' name="isbn"
									class="btn btn-primary center-block">
									<span class="glyphicon glyphicon-shopping-cart"></span> In den
									Warenkorb
								</button>
								<sec:csrfInput />
							</form>
						</sec:authorize>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
</section>