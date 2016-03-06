<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page session="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div>
	<c:if test="${param.error != null}">
	    <p><em>Fehler:</em> Bei der Abfrage der Daten ist ein Fehler mit der folgenden Fehlermeldung aufgetreten: &bdquo;<c:out value="${errormsg}"></c:out>&ldquo;</p>
	</c:if>
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
			<div class="hidden-info" id="plz-info-wrapper">
				<p id="plz-info"></p>
				<div id="plz-selection"></div>
			</div>
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
			<c:choose>
				<c:when test="${users.isEmpty()}">
					<p>Noch keine Nutzer:innen in der Datenbank vorhanden.</p>
				</c:when>
				<c:otherwise>
					<label for="edit-id">Datensatz auswählen</label>
					<select name="edit-id" id="edit-id" required>
						<c:forEach var="user" items="${users}">
							<option value="${user.getUserId()}"><c:out value="${user.getUserId()}" />: <c:out value="${user.getName()}" /> <c:out value="${user.getSurname()}" /></option>
						</c:forEach>			
					</select>
					
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
					<div class="hidden-info" id="edit-plz-info-wrapper">
						<p id="edit-plz-info"></p>
						<div id="edit-plz-selection"></div>
					</div>
					
					<label for="edit-email">E-Mail</label>
					<input type="email" id="edit-email" name="edit-email" required/>
					
					<label for="edit-password">Passwort (mindestens 6 Zeichen lang)</label>
					<input type="password" id="edit-password" name="edit-password" required/>
					
					<label for="edit-role">Rolle</label>
					<select name="edit-role" id="edit-role" required>
						<option value="USER">Nutzer:in</option>
						<option value="ADMIN">Administrator:in</option>
					</select>
					
					<button type="submit" id="edit-user-submit">Datensatz ändern</button>	
				</c:otherwise>
			</c:choose>
		</fieldset>
	</form>
	
	<h4 id="loeschen">Nutzer:in löschen</h4>
	<p><em>Hinweis: Neu angelegte Nutzer:innen sind derzeit erst nach einem neuen Laden der Seite zu sehen.</em></p>
	<form>
		<p>Noch nicht implementiert :(</p>
	</form>
</div>