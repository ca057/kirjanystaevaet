<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ page session="false" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<section>
	<div class="page-header">
		<h1>
			Hallo
			<c:out value="${user.getName()}" />
			<c:out value="${user.getSurname()}" />
			!
		</h1>
	</div>

	<c:if test="${param.error != null}">
		<p class="error">Bei der letzten Aktion ist ein Fehler
			aufgetreten.</p>
	</c:if>

	<div class="row">
		<div class="col-sm-9">
			<article>
				<h2>Deine Bestellungen</h2>
				<div class="panel-group" id="accordion">
					<div class="panel panel-default">
						<c:choose>
							<c:when test="${lastOrders.isEmpty()}">
								<p>Du hast noch keine Bestellungen aufgegeben.</p>
							</c:when>
							<c:otherwise>
								<ul>
									<c:forEach var="order" items="${lastOrders}">
										<div class="panel-heading">
      										<h4 class="panel-title">
      											<a data-toggle="collapse" data-parent="#accordion" <c:out value="href=#collapse${order.getOrderId()}" />>Bestellung vom <fmt:formatDate pattern="dd.MM.yyyy"
												value="${order.getDate().getInstance().getTime()}" /></a>
      										</h4>
    									</div>
    									<div id="collapse<c:out value='${order.getOrderId()}'/>" class="panel-collapse collapse in">
											<ul>
												<c:forEach var="item" items="${order.getOrderItems()}">
													<li><c:out value="${item.getBook().getTitle()}"></c:out>
														(Menge: <c:out value="${item.getNumberOf()}"></c:out>;
														Einzelpreis: <c:out value="${item.getBook().getPrice()}"></c:out>€)</li>
												</c:forEach>
											</ul>
										</div>
									</c:forEach>
								</ul>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</article>
		</div>
		<c:choose>
			<c:when test="${user.getImage() != null}">
				<div class="col-sm-3">
					<div class="jumbotron">
						<img class="img-responsive .img-thumbnail"
							src=<s:url value='/meinkonto/profilbild'/> alt="Profilbild" />
					</div>
				</div>
			</c:when>
			<c:otherwise>
				<div class="col-sm-3">
					<div class="jumbotron">
						<form action="meinkonto/bildhochladen" method="POST"
							enctype="multipart/form-data">
							<label for="image">Lade ein Profilbild hoch!</label> <input
								type="file" accept="image/*" name="file" />
							<button type="submit" id="profilepicture-submit"
								title="Hochladen" class="btn btn-primary">Hochladen</button>
							<sec:csrfInput />
						</form>
					</div>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
</section>