<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page session="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="page-header">
	<h1>Registrierung</h1>
</div>

<p>Schon einen Account? Hier geht es zum <a href="<c:url value="/login" />" title="Zum Login">Login</a>!</p>

<form id="register-form">
	<fieldset>
		<legend>Neu registrieren</legend>
		<p>Alle Felder müssen ausgefüllt sein.</p>
		<label for="name">Vorname</label>
		<input type="text" id="name" name="name" required/>
		<label for="surname">Nachname</label>
		<input type="text" id="surname" name="surname" required/>
		<label for="street">Straße</label>
		<input type="text" id="street" name="street" required/>
		<label for="streetnumber">Hausnummer</label>
		<input type="text" id="streetnumber" name="streetnumber" required/>
		<label for="plz">PLZ</label>
		<input type="text" id="plz" maxlength="5" required/>
		<div class="hidden-info" id="plz-info-wrapper">
			<p id="plz-info"></p>
			<div id="plz-selection"></div>
		</div>
		<label for="email">E-Mail</label>
		<input type="email" id="email" name="email" required/>
		<label for="password">Passwort (mindestens 6 Zeichen lang)</label>
		<input type="password" id="password" name="password" required/>
		<label for="image">Profilbild</label>
		<input type="file" id="image" accept="image/*" name="file" />
		<button type="submit" id="register-submit" title="Registrieren">Registrieren</button>
		<sec:csrfInput/>
		<p id="info-message" style="display:none;font-style:italic">&nbsp;</p>
	</fieldset>
</form>