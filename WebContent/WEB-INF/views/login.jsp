<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page session="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<section>
	<h2>Mein Konto</h2>
	<form action="<c:url value="/login" />" method="post">
		<fieldset>
			<legend>Login</legend>
			<c:if test="${param.error != null}">
			    <p class="login-error">Ungültiger Benutzername oder Passwort.</p>
			</c:if>
			<label for="username">E-Mail</label>
			<input type="text" id="username" name="username"/>        
			<label for="password">Passwort</label>
			<input type="password" id="password" name="password"/>
			<button type="submit" value="In Mein Konto anmelden">Anmelden</button>
			<sec:csrfInput/>
		</fieldset>
	</form>
</section>