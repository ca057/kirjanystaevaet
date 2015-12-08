<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<section>
	<article>
		<h2>Mein Konto</h2>
		<form action="login" method="post">
			<input type="text" name="name" placeholder="Benutzername" required>
			<input type="password" name="pwd" placeholder="Passwort" required>
			<input type="submit" value="Einloggen">
		</form>
	</article>
</section>