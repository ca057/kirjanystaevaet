<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page session="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<section>
	<div class="page-header">
	<h1>Login</h1>
	</div>
	<form action="<c:url value="/login" />" method="post">
		<fieldset>
			<c:if test="${param.error != null}">
			    <p class="error">Ungültiger Benutzername oder Passwort.</p>
			</c:if>
			<label for="username">E-Mail</label>
			<input type="text" id="username" name="username"/>        
			<label for="password">Passwort</label>
			<input type="password" id="password" name="password"/>
			<button type="submit" value="In Mein Konto anmelden">Anmelden</button>
			<sec:csrfInput/>
		</fieldset>
	</form>
	<p>Noch kein Konto? Hier kommst du zur <a href="<c:url value='/registrierung' />" title="Zur Registrierung">Registrierung</a>.</p>
</section>