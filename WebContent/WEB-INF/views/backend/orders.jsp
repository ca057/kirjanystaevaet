<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<section>
	<div class="page-header">
		<h1>Bestellungen</h1>
	</div>
	<c:if test="${error != null}">
		<p class="error"><em>Fehler:</em> Bei Abfrage der Bestellungen ist ein Fehler mit der folgenden Fehlermeldung aufgetreten: <c:out value="${error}"></c:out> </p>
	</c:if>
	<c:if test="${error == null}">
		<c:choose>
			<c:when test="${orders.isEmpty()}">
				<p>Derzeit gibt es keine laufenden Bestellungen.</p>
			</c:when>
			<c:otherwise>
					<c:forEach var="order" items="${orders}">
						<div class="panel-heading">
      										<h4 class="panel-title">
      											<c:forEach var='item' items='${order.getOrderItems()}'><c:set var="totalprice" value="${totalprice + item.getPrice()}"/> </c:forEach>
      											<a data-toggle="collapse" data-parent="#accordion" <c:out value="href=#collapse${order.getOrderId()}" />>Bestellung vom <fmt:formatDate pattern="dd.MM.yyyy"
												value="${order.getDate().getInstance().getTime()}" />: <c:out value="${totalprice}"></c:out> €</a>
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
			</c:otherwise>
		</c:choose>
	</c:if>
</section>