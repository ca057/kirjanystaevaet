<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page session="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<section>
	<h2>Hallo <c:out value="${admin.getName()}" /> <c:out value="${admin.getSurname()}" />!</h2>
	<article>
		<p>Hier kann der Shop verwaltet werden.</p>
	</article>
</section>