<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page session="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div>
	<h2>Kund:innenverwaltung</h2>
	<h4 id="anlegen">Kunde:in anlegen</h4>
	<form>
		<label for="name">Vorname</label>
		<input type="text" id="name" name="name" required/>
		<label for="surname">Nachname</label>
		<input type="text" id="surname" name="surname" required/>
		<label for="street">Straße</label>
		<input type="text" id="street" name="street" required/>
		<label for="streetnumber">Hausnummer</label>
		<input type="text" id="streetnumber" name="streetnumber" required/>
		<label for="plz">PLZ</label>
		<input type="text" id="plz" name="plz" required/>
		<label for="email">E-Mail</label>
		<input type="email" id="email" name="email" required/>
		<label for="password">Passwort</label>
		<input type="password" id="password" name="password" required/>
		<button type="submit">Kunde:in anlegen</button>
		<sec:csrfInput/>
	</form>

	<h4 id="aendern">Datensatz eines/einer Kund:in ändern</h4>
	<form>
		<p>Noch nicht implementiert :(</p>
	</form>
	
	<h4 id="loeschen">Kunde:in löschen</h4>
	<form>
		<p>Noch nicht implementiert :(</p>
	</form>
</div>