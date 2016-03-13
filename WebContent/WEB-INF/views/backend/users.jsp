<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ page session="false" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div>
	<c:if test="${param.error != null}">
		<p>
			<em>Fehler:</em> Bei der Abfrage der Daten ist ein Fehler mit der
			folgenden Fehlermeldung aufgetreten: &bdquo;
			<c:out value="${errormsg}"></c:out>
			&ldquo;
		</p>
	</c:if>
	<div class="page-header">
		<h1>Nutzer_innenverwaltung</h1>
	</div>

	<form class="form-inline">
		<fieldset>
			<legend>Daten erfassen</legend>
			<div class="form-group">
				<label for="name">Vorname</label> <input type="text" id="name"
					name="name" required />
			</div>

			<div class="form-group">
				<label for="surname">Nachname</label> <input type="text"
					id="surname" name="surname" required />
			</div>

			<div class="form-group">
				<label for="street">Straße</label> <input type="text" id="street"
					name="street" required />
			</div>

			<div class="form-group">
				<label for="streetnumber">Hausnummer</label> <input type="text"
					id="streetnumber" name="streetnumber" required />
			</div>

			<div class="form-group">
				<label for="plz">PLZ</label> <input type="text" id="plz" name="plz"
					required />
				<div class="hidden-info" id="plz-info-wrapper">
					<p id="plz-info"></p>
					<div id="plz-selection"></div>
				</div>
			</div>

			<div class="form-group">
				<label for="email">E-Mail</label> <input type="email" id="email"
					name="email" required />
			</div>

			<div class="form-group">
				<label for="role">Rolle</label> <select name="role" id="role"
					class="form-control" required>
					<option value="">Rolle auswählen</option>
					<option value="USER">Nutzer_in</option>
					<option value="ADMIN">Administrator_in</option>
				</select>
			</div>

			<button type="submit" id="add-user-submit" class="btn btn-default">Nutzer_in anlegen</button>
		</fieldset>
	</form>

	<form class="form-inline">
		<p>
			<em>Hinweis: Neu angelegte Nutzer:innen sind derzeit erst nach
				einem neuen Laden der Seite zu sehen.</em>
		</p>
		<fieldset>
			<legend>Änderungen in bestehenden Account eintragen</legend>
			<c:choose>
				<c:when test="${users.isEmpty()}">
					<p>Noch keine Nutzer:innen in der Datenbank vorhanden.</p>
				</c:when>
				<c:otherwise>
					<label for="edit-id">Datensatz auswählen</label>
					<select name="edit-id" id="edit-id" class="form-control" required>
						<c:forEach var="user" items="${users}">
						<option value="">Datensatz auswählen</option>
							<option value="${user.getUserId()}"><c:out
									value="${user.getUserId()}" />:
								<c:out value="${user.getName()}" />
								<c:out value="${user.getSurname()}" /></option>
						</c:forEach>
					</select>

					<div class="form-group">
					<label for="edit-name">Vorname</label>
					<input type="text" id="edit-name" name="edit-name" />
					</div>

					<div class="form-group">
					<label for="edit-surname">Nachname</label>
					<input type="text" id="edit-surname" name="edit-surname" />
					</div>

					<div class="form-group">
					<label for="edit-street">Straße</label>
					<input type="text" id="edit-street" name="edit-street" />
					</div>

					<div class="form-group">
					<label for="edit-streetnumber">Hausnummer</label>
					<input type="text" id="edit-streetnumber" name="edit-streetnumber" />
						</div>

					<div class="form-group">
					<label for="edit-plz">PLZ</label>
					<input type="text" id="edit-plz" name="edit-plz" />
					<div class="hidden-info" id="edit-plz-info-wrapper">
						<p id="edit-plz-info"></p>
						<div id="edit-plz-selection"></div>
					</div>
					</div>

					<div class="form-group">
					<label for="edit-email">E-Mail</label>
					<input type="email" id="edit-email" name="edit-email" />
					</div>

					<div class="form-group">
					<label for="edit-password">Passwort <span class="glyphicon glyphicon-info-sign" title="mind. 6 Zeichen"></span></label>
					<input type="password" id="edit-password" name="edit-password" />
						</div>

					<div class="form-group">
					<label for="edit-role">Rolle</label>
					<select name="edit-role" id="edit-role" class="form-control" >
						<option value="">Rolle auswählen</option>
						<option value="USER">Nutzer:in</option>
						<option value="ADMIN">Administrator:in</option>
					</select>
					</div>

					<button type="submit" class="btn btn-default" id="edit-user-submit">Datensatz ändern</button>
				</c:otherwise>
			</c:choose>
		</fieldset>
	</form>

	<form class="form-inline" id="loeschen">
		<fieldset>
			<legend>Nutzer_in löschen</legend>
			<p>
				<em>Hinweis: Neu angelegte Nutzer:innen sind derzeit erst nach
					einem neuen Laden der Seite zu sehen.</em>
			</p>
			<p>Noch nicht implementiert :(</p>
		</fieldset>
	</form>
</div>