<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page session="false" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	

<section>
	<c:choose>
		<c:when test="${error != null}">
			<p>
				<c:out value="${error}"></c:out>
			</p>
			<p>
				<a href="<c:url value='/'/>" title="zur Startseite">zur
					Startseite</a>
			</p>
		</c:when>
		<c:otherwise>
			<div class="page-header">
			<h1>
				<c:out value="${book.getTitle()}" />
			</h1>
			</div>
			<c:choose>
				<c:when test="${!info.isEmpty()}">
					<p class="error">
						<c:out value="${info}"></c:out>
					</p>
				</c:when>
			</c:choose>
			<p>
				von
				<c:set var="delimiter" value="" scope="request"></c:set>
				<c:forEach var="a" items="${book.getAuthors()}">${delimiter}<c:out
						value="${a.getNameF()}" />
					<c:out value="${a.getNameL()}" />
					<c:set var="delimiter" value=", " scope="request"></c:set>
				</c:forEach>
			</p>
			<div class="row row-eq-height">
				<div class="col-sm-3">
					<img class="book-cover img-responsive"
						src="<c:url value="/img/cover/${book.getIsbn()}.jpg"/>"
						title="<c:out value="Cover des Buchs '${book.getTitle()}'"/>">
				</div>
				<div class="col-sm-9">
					<h3>Details</h3>
					<div class="row">
						<div class="col-sm-7">
							<p>
								Seitenzahl:
								<c:out value="${book.getPages()}" />
							</p>
							<p>
								Verlag:
								<c:out value="${book.getPublisher()}" />
							</p>
							<p>
								ISBN:
								<c:out value="${book.getIsbn()}" />
							</p>
							<p>
								Preis
								<c:out value="${book.getPrice()}" />
							</p>
							<h3>Kurzbeschreibung</h3>
							<!--  </div> -->
						</div>
<%-- 						<sec:authorize access="isAnonymous()"> --%>
<!-- 								<button data-toggle="modal" data-target="#login" -->
<!-- 									class="btn btn-primary center-block"> -->
<!-- 									<span class="glyphicon glyphicon-shopping-cart"></span> In den -->
<!-- 									Warenkorb -->
<!-- 								</button> -->
<%-- 								<sec:authorize access="isAnonymous()"> --%>
<!-- 									<div id="login" class="modal fade" role="dialog"> -->
<!-- 										<div class="modal-dialog"> -->
	
<!-- 											Modal content -->
<!-- 											<div class="modal-content"> -->
<!-- 												<div class="modal-header"> -->
<!-- 													<button type="button" class="close" data-dismiss="modal">&times;</button> -->
<!-- 													<h4 class="modal-title">Login nötig</h4> -->
<!-- 												</div> -->
<!-- 													<div class="modal-body" id="popupLoginBody"> -->
<!-- 														<p>Bitte erst als User einloggen!</p> -->
<%-- 														<form method="post" action="<c:url value="/login" />" method="post"> --%>
<!-- 															<input type="text" placeholder="E-Mail" id="username" name="username"> <input type="password" placeholder="Passwort" id="password" name="password"> <input -->
<!-- 															class="btn btn-primary btn-block" type="submit" value="Anmelden"> -->
<%-- 															<sec:csrfInput /> --%>
<!-- 														</form> -->
<!-- 													</div> -->
<!-- 												<div class="modal-footer"> -->
<!-- 													<button type="button" class="btn btn-default" -->
<!-- 														data-dismiss="modal">Close</button> -->
<!-- 												</div> -->
<!-- 											</div> -->
	
<!-- 										</div> -->
<!-- 									</div> -->
<%-- 								</sec:authorize> --%>
<%-- 								</sec:authorize> --%>
<%-- 								<sec:authorize access="hasRole('ADMIN')"> --%>
<!-- 									<button data-toggle="modal" data-target="#adminPopup" class="btn btn-primary center-block"> -->
<!-- 										<span class="glyphicon glyphicon-shopping-cart"></span> -->
<!-- 										In den Warenkorb -->
<!-- 									</button> -->
<!-- 									<div id="adminPopup" class="modal fade" role="dialog"> -->
<!-- 										<div class="modal-dialog"> -->
	
<!-- 											Modal content -->
<!-- 											<div class="modal-content"> -->
<!-- 												<div class="modal-header"> -->
<!-- 													<button type="button" class="close" data-dismiss="modal">&times;</button> -->
<!-- 													<h4 class="modal-title">Kein Zugriff</h4> -->
<!-- 												</div> -->
<!-- 													<div class="modal-body"> -->
<!-- 														<p>Der Warenkorb steht nur der Kundschaft zur Verfügung.</p> -->
<!-- 													</div> -->
<!-- 												<div class="modal-footer"> -->
<!-- 													<button type="button" class="btn btn-default" -->
<!-- 														data-dismiss="modal">Close</button> -->
<!-- 												</div> -->
<!-- 											</div> -->
	
<!-- 										</div> -->
<!-- 									</div> -->
<%-- 								</sec:authorize> --%>
						
<%-- 								<sec:authorize access="hasRole('USER')"> --%>
<%-- 									<form action="<c:url value='warenkorb' />" method="post" id="cartForm"> --%>
<%-- 										<button type="submit" form="cartForm" value='<c:out value="${book.getIsbn()}"></c:out>' name="isbn" class="btn btn-primary center-block"> --%>
<!-- 											<span class="glyphicon glyphicon-shopping-cart"></span>  -->
<!-- 											In den Warenkorb -->
<!-- 										</button> -->
<%-- 								<sec:csrfInput /> --%>
<!-- 							</form> -->
<%-- 						</sec:authorize> --%>
						<div class="add-to-cart col-sm-5">
<%-- 					<sec:authorize access="hasRole('USER')"> --%>
							<form action="<c:url value='warenkorb' />" method="post" id="cartForm">
								<button type="submit" form="cartForm" value='<c:out value="${book.getIsbn()}"></c:out>' name="isbn">
									<img src="<c:url value="/img/icons/ic_add_shopping_cart_black_36dp.png" />" title="Zum Warenkorb hinzufügen" />
									<p>In den Warenkorb</p>
								</button>
								<sec:csrfInput />
							</form>
<%-- 					</sec:authorize> --%>
						</div>
					</div>
					<div>
						<p>
							<!--  <div class="crop">
							<c:out value="${book.getDescription()}" escapeXml="false" />  -->
							<c:set var="string1" value="${book.getDescription()}" />
							<c:set var="string2" value="${fn:substring(string1, 3, 350)}" />
							<c:out value="${string2}" escapeXml="false">${string2}</c:out>
							...
						</p>
						<a href="#desc" class="toggle btn btn-primary">weiterlesen</a>
					</div>
				</div>
			</div>
			<div class="des col-sm-12 hidden">
				<h3>Beschreibung</h3>
				<p>
					<c:out value="${book.getDescription()}" escapeXml="false"></c:out>
				</p>
			</div>
		</c:otherwise>
	</c:choose>
</section>