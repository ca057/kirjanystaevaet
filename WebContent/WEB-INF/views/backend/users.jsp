<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page session="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div>
	<h2>Nutzer:innenverwaltung</h2>
	<h4 id="anlegen">Nutzer:in anlegen</h4>
	<form>
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
		</fieldset>
	</form>

	<h4 id="aendern">Datensatz eines/einer Nutzer:in ändern</h4>
	<p><em>Hinweis: Neu angelegte Nutzer:innen sind derzeit erst nach einem neuen Laden der Seite zu sehen.</em></p>
	<form>		
		<fieldset>
			<legend>Nutzer:in auswählen und Änderungen eintragen</legend>
			<select name="user" id="user" required>
				<c:if test="${!users.isEmpty()}">
					<c:forEach var="user" items="${users}">
						<option value="${user.getUserId()}"><c:out value="${user.getUserId()}" />: <c:out value="${user.getName()}" /> <c:out value="${user.getSurname()}" /></option>
					</c:forEach>			
				</c:if>
				<label for="edit-name">Vorname</label>
				<input type="text" id="edit-name" name="edit-name" required/>
				<label for="edit-surname">Nachname</label>
				<input type="text" id="edit-surname" name="edit-surname" required/>
				<label for="edit-street">Straße</label>
				<input type="text" id="edit-street" name="edit-street" required/>
				<label for="edit-streetnumber">Hausnummer</label>
				<input type="text" id="edit-streetnumber" name="edit-streetnumber" required/>
				<label for="edit-plz">PLZ</label>
				<input type="text" id="edit-plz" name="edit-plz" required/>
				<label for="edit-email">E-Mail</label>
				<input type="email" id="edit-email" name="edit-email" required/>
				<label for="edit-role">Rolle</label>
				<select name="edit-role" id="edit-role" required>
					<option value="USER">Nutzer:in</option>
					<option value="ADMIN">Administrator:in</option>
				</select>
				<button type="submit" id="edit-user-submit">Nutzer:in anlegen</button>
	
			</select>
		</fieldset>
	</form>
	
	<h4 id="loeschen">Nutzer:in löschen</h4>
	<p><em>Hinweis: Neu angelegte Nutzer:innen sind derzeit erst nach einem neuen Laden der Seite zu sehen.</em></p>
	<form>
		<p>Noch nicht implementiert :(</p>
	</form>
</div>