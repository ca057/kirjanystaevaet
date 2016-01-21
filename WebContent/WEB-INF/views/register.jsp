<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page session="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<h2>Registrierung</h2>

<p>Schon einen Account? Hier geht es zum <a href="<c:url value="/login" />" title="Zum Login">Login</a>!</p>

<form id="register-form">
	<fieldset>
		<legend>Neu registrieren</legend>
		<label for="name">Vorname</label>
		<input type="text" id="name" name="name" required/>
		<label for="surname">Nachname</label>
		<input type="text" id="surname" name="surname" required/>
		<label for="email">E-Mail</label>
		<input type="email" id="email" name="email" required/>
		<label for="password">Passwort</label>
		<input type="password" id="password" name="password" required/>
		<button type="submit" id="register-submit" title="Registrieren">Registrieren</button>
		<sec:csrfInput/>
	</fieldset>
</form>