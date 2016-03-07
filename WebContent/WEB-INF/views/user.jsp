<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
					<li><c:out value="${order.getId()}"></c:out> </li>
				</c:forEach>
				</ul>
			</c:otherwise>
		</c:choose>
	</article>
</section>