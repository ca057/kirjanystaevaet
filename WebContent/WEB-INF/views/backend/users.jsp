<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page session="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div>
	<h2>Nutzer:innenverwaltung</h2>
	<h4 id="anlegen">Nutzer:in anlegen</h4>
	<fieldset>
		<legend>Daten erfassen</legend>
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
		<label for="role">Rolle</label>
		<select name="role" id="role" required>
			<option value="USER">Nutzer:in</option>
			<option value="ADMIN">Administrator:in</option>
		</select>
		<button type="submit" id="add-user-submit">Nutzer:in anlegen</button>
		<sec:csrfInput/>
	</fieldset>

	<h4 id="aendern">Datensatz eines/einer Nutzer:in ändern</h4>
	<form>
		<p>Noch nicht implementiert :(</p>
	</form>
	
	<h4 id="loeschen">Nutzer:in löschen</h4>
	<form>
		<p>Noch nicht implementiert :(</p>
	</form>
</div>