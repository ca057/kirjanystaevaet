<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<section>
	<h2>Hallo <c:out value="${user.getName()}" /> <c:out value="${user.getSurname()}" />!</h2>
	<c:choose>
		<c:when test="{user.getImage() != null}">
			<img class="profilepicture" src="/meinkonto/profilbild/${user.getUserId()}" alt="Profilbild" />
		</c:when>
		<c:otherwise>
			<form action="meinkonto/bildhochladen" method="POST"
			enctype="multipart/form-data">
			<label for="image">Lade ein Profilbild!</label>
			<input type="file" accept="image/*" name="file" />
			<button type="submit" id="profilepicture-submit" title="Hochladen">Hochladen</button>
			<sec:csrfInput/>
		</form>
		</c:otherwise>
	</c:choose>
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
								<li><c:out value="${item.getBook().getTitle()}"></c:out> (Menge: <c:out value="${item.getNumberOf()}"></c:out>; Einzelpreis: <c:out value="${item.getBook().getPrice()}"></c:out>€)</li>
							</c:forEach>
						</ul>
					</li>
				</c:forEach>
				</ul>
			</c:otherwise>
		</c:choose>
	</article>
</section>