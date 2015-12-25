<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<section>
	<h2>Mein Konto</h2>
	<form th:action="<c:url value="/login" />" method="post">
		<fieldset>
			<legend>Login</legend>
			<!-- Brauchen wir das? Woher kommen die Parameter? -->
			<div th:if="${param.error}" class="alert alert-error">    
			    <p>Ungültiger Benutzername oder Passwort.</p>
			</div>
			<div th:if="${param.logout}" class="alert alert-success"> 
			    <p>Sie haben sich ausgeloggt.</p>
			</div>
			<label for="username">Benutzername</label>
			<input type="text" id="username" name="username"/>        
			<label for="password">Passwort</label>
			<input type="password" id="password" name="password"/>
			<input type="submit" value="Einloggen">
		</fieldset>
	</form>
</section>