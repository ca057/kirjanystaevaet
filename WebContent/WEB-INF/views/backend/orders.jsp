<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<section>
	<h2>Bestellungen</h2>
	<h3>Aktuelle Bestellungen</h3>
	<c:if test="${error != null}">
		<p class="error"><em>Fehler:</em> Bei Abfrage der Bestellungen ist ein Fehler mit der folgenden Fehlermeldung aufgetreten: <c:out value="${error}"></c:out> </p>
	</c:if>
	<c:if test="${error == null}">
		<c:choose>
			<c:when test="${orders.isEmpty()}">
				<p>Derzeit gibt es keine laufenden Bestellungen.</p>
			</c:when>
			<c:otherwise>
				<ul>
					<c:forEach var="order" items="${orders}">
						<li>Bestellung #<c:out value="${order.getOrderId()}" /> vom <fmt:formatDate pattern="dd.MM.yyyy" value="${order.getDate().getInstance().getTime()}" /> mit <c:out value="${order.getOrderItems().size()}"></c:out> Büchern:
							<ul>
								<c:forEach var="item" items="${order.getOrderItems()}">
									<li><c:out value="${item.getBook().getTitle()}"></c:out> (Menge: <c:out value="${item.getNumberOf()}"></c:out>)</li>
								</c:forEach>
							</ul>
						</li>
					</c:forEach>
				</ul>
			</c:otherwise>
		</c:choose>
	</c:if>
</section>