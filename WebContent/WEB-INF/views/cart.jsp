<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<section>
	<h2>Warenkorb</h2>
	<article>
		<p>Hallo <c:out value="${name}" />, ich bin dein Warenkorb. Schau mal, was schon in mir liegt :)</p>
	</article>
</section>