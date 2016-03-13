<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ page session="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<section>
	<div class="page-header">
		<h1>Suche</h1>
	</div>
	
	<div class="row">
	
	<form class="form">
		<div class="col-sm-6 col-md-4 col-lg-2">
			<label for="title">Titel</label>
			<input type="text" id="title" name="title" placeholder="Titel" class="form-control">
		</div>
		<div class="col-sm-6 col-md-4 col-lg-2">
			<label for="isbn">ISBN</label>
			<input type="text" id="isbn" name="isbn" placeholder="ISBN" class="form-control">
		</div>
		<div class="col-sm-6 col-md-4 col-lg-2">
			<label for="year">Erscheinungsjahr</label>
			<input type="text" id="year" name="year" placeholder="Jahr" class="form-control">
		</div>
		<div class="col-sm-6 col-md-4 col-lg-2">
			<label for="category">Kategorie</label>
			<input type="text" id="category" name="category" placeholder="Kategorie" class="form-control">
		</div>
		<div class="col-sm-6 col-md-4 col-lg-2">
			<label for="authorFirst">Autor_in</label>
			<input type="text" id="authorFirst" name="authorFirst" placeholder="Vorname" class="form-control">
		</div>
		<div class="col-sm-6 col-md-4 col-lg-2">
			<label for="authorLast">Autor_in</label>
			<input type="text" id="authorLast" name="authorLast" placeholder="Nachname" class="form-control">
		</div>
		<div class="col-xs-12">
			<button class="btn btn-primary" type="submit" value="Suche">Suche</button>
		</div>
	</form>
	
	</div>
	
	
	
	<!-- Suchanfrage wird nur angezeigt, wenn der übergebene String nicht leer ist. -->
	<c:if test="${error != null}">
		<p><c:out value="${error}"></c:out></p>
	</c:if>
	<c:choose>
		<c:when test="${query.isEmpty()}">
		</c:when>
		<c:otherwise>
			<h2>Suchergebnisse</h2>
			<p><span class="font-bold">Suchanfrage:</span> <span class="query"><c:out value='${query}' /></span></p>
			<c:choose>
				<c:when test="${results.isEmpty()}">
					<p>Keine Suchergebnisse</p>
				</c:when>
				<c:otherwise>
					<c:forEach var="book" items="${results}">
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
												<form action="../warenkorb" method="post" id="cartForm">
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
</section>