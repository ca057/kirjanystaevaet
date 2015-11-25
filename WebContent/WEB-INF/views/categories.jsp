<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<section>
	<h2><c:out value='${name}'/></h2>
	<article>
		<p>Folgende Bücher haben wir zu <c:out value='${name}' /></p>
	</article>
</section>