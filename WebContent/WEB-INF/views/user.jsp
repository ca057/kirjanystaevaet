<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<section>
	<h2>Hallo <sec:authentication property="principal.username"/>!</h2>
	<article>
		<c:choose>
			<c:when test="${lastOrders.isEmpty()}">
				<p>Du hast noch keine Bestellungen aufgegeben.</p>
			</c:when>
			<c:otherwise>
				<ul>
				<c:forEach var="order" items="${lastOrders}">
					<li>Bestellung #<c:out value="${order.getId()}" /> vom <fmt:formatDate pattern="dd.MM.yyyy" value="${order.getDate().getInstance().getTime()}" /> mit folgendem Inhalt:
						<ul>
							<c:forEach var="item" items="${order.getOrderItems()}">
								<li><c:out value="${item.getBook().getTitle()}"></c:out> (<c:out value="${item.getBook().getPrice()}"></c:out>€)</li>
							</c:forEach>
						</ul>
					</li>
				</c:forEach>
				</ul>
			</c:otherwise>
		</c:choose>
	</article>
</section>