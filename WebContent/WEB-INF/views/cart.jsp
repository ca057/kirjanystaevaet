<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<section>
	<h2><c:out value='${name}'/></h2>
	<article>
		<p>Hallo Kleines, ich bin dein Warenkorb. Schau mal, was schon in mir liegt :) <br/><c:out value="<c:url value="/img/cover/${book.getIsbn()}.jpg"/>" /></p>
	</article>
</section>