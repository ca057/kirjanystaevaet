<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<section>
	<h2>Mein Konto</h2>
	<form action="<c:url value="/login" />" method="post">
		<fieldset>
			<legend>Login</legend>
			<!-- Brauchen wir das? Woher kommen die Parameter? -->
			<c:if test="${param.error != null}">
			    <p class="login-error">Ungültiger Benutzername oder Passwort.</p>
			</c:if>
			<label for="username">Benutzername</label>
			<input type="text" id="username" name="username"/>        
			<label for="password">Passwort</label>
			<input type="password" id="password" name="password"/>
			<input type="submit" value="Einloggen">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		</fieldset>
	</form>
</section>