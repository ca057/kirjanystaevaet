<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ page session="false" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div>
	<c:if test="${param.error != null}">
		<p class="error"><em>Fehler:</em> Es ist ein Fehler aufgetreten.</p>
		<c:choose>
			<c:when test="${errormsg != null}">
				<p class="error">Fehlermeldung: &bdquo;<c:out value="${errormsg}"></c:out>&ldquo;</p>
			</c:when>
			<c:when test="${param.msg != null}">
				<p class="error">Fehlermeldung: &bdquo;<c:out value="${param.msg}"></c:out>&ldquo;</p>
			</c:when>
		</c:choose>
	</c:if>
	
	<div class="page-header">
		<h1>Nutzer_innenverwaltung</h1>
	</div>

	<div class="row">
	<form class="form">
		<fieldset>
			<legend>Daten erfassen</legend>
			<div class="form-group col-sm-6 col-lg-4">
				<label for="name">Vorname</label> <input type="text" id="name"
					name="name" class="form-control" required />
			</div>

			<div class="form-group col-sm-6 col-lg-4">
				<label for="surname">Nachname</label> <input type="text"
					id="surname" name="surname" class="form-control" required />
			</div>

			<div class="form-group col-sm-6 col-lg-4">
				<label for="street">Straße</label> <input type="text" id="street"
					name="street" class="form-control" required />
			</div>

			<div class="form-group col-sm-6 col-lg-4">
				<label for="streetnumber">Hausnummer</label> <input type="text"
					id="streetnumber" name="streetnumber" class="form-control" required />
			</div>

			<div class="form-group col-sm-6 col-lg-4">
				<label for="plz">PLZ</label> <input type="text" id="plz" name="plz" class="form-control"
					required />
				<div class="hidden-info" id="plz-info-wrapper">
					<p id="plz-info"></p>
					<div id="plz-selection"></div>
				</div>
			</div>

			<div class="form-group col-sm-6 col-lg-4">
				<label for="email">E-Mail</label> <input type="email" id="email"
					name="email" class="form-control" required />
			</div>

			<div class="form-group col-sm-6 col-lg-4">
				<label for="role">Rolle</label> <select name="role" id="role"
					class="form-control" required>
					<option value="">Rolle auswählen</option>
					<option value="USER">Nutzer_in</option>
					<option value="ADMIN">Administrator_in</option>
				</select>
			</div>
			
			<div class="col-xs-12">
				<button type="submit" id="add-user-submit" class="btn btn-primary">Nutzer_in anlegen</button>
			</div>
		</fieldset>
	</form>
	</div>

	<form class="form">
		<p class="help-block">Hinweis: Neu angelegte Nutzer:innen sind derzeit erst nach
				einem neuen Laden der Seite zu sehen.
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
							<option value="${user.getUserId()}">
								<c:out value="${user.getUserId()}" />:
								<c:out value="${user.getName()}" />
								<c:out value="${user.getSurname()}" />
							</option>
						</c:forEach>
					</select>

					<div class="form-group col-sm-6 col-lg-4">
					<label for="edit-name">Vorname</label>
					<input type="text" id="edit-name" name="edit-name" class="form-control"/>
					</div>

					<div class="form-group col-sm-6 col-lg-4">
					<label for="edit-surname">Nachname</label>
					<input type="text" id="edit-surname" name="edit-surname" class="form-control"/>
					</div>

					<div class="form-group col-sm-6 col-lg-4">
					<label for="edit-street">Straße</label>
					<input type="text" id="edit-street" name="edit-street" class="form-control"/>
					</div>

					<div class="form-group col-sm-6 col-lg-4">
					<label for="edit-streetnumber">Hausnummer</label>
					<input type="text" id="edit-streetnumber" name="edit-streetnumber" class="form-control"/>
						</div>

					<div class="form-group col-sm-6 col-lg-4">
					<label for="edit-plz">PLZ</label>
					<input type="text" id="edit-plz" name="edit-plz" class="form-control"/>
					<div class="hidden-info" id="edit-plz-info-wrapper">
						<p id="edit-plz-info"></p>
						<div id="edit-plz-selection"></div>
					</div>
					</div>

					<div class="form-group col-sm-6 col-lg-4">
					<label for="edit-email">E-Mail</label>
					<input type="email" id="edit-email" name="edit-email" class="form-control"/>
					</div>

					<div class="form-group col-sm-6 col-lg-4">
					<label for="edit-password">Passwort <span class="glyphicon glyphicon-info-sign" title="mind. 6 Zeichen"></span></label>
					<input type="password" id="edit-password" name="edit-password" class="form-control"/>
						</div>

					<div class="form-group col-sm-6 col-lg-4">
					<label for="edit-role">Rolle</label>
					<select name="edit-role" id="edit-role" class="form-control" >
						<option value="">Rolle auswählen</option>
						<option value="USER">Nutzer:in</option>
						<option value="ADMIN">Administrator:in</option>
					</select>
					</div>

					<div class="col-xs-12">
						<button type="submit" class="btn btn-primary" id="edit-user-submit">Datensatz ändern</button>
					</div>
				</c:otherwise>
			</c:choose>
		</fieldset>
	</form>
	
	<form class="form-inline" id="loeschen" action="nutzerinnen/delete" method="POST">
		<fieldset>
			<legend>Nutzer_in löschen</legend>
			<p>
				<em>Hinweis: Neu angelegte Nutzer:innen sind derzeit erst nach
					einem neuen Laden der Seite zu sehen, der eigene Account kann nicht gelöscht werden.</em>
			</p>
			<label for="delete-id">Account auswählen</label>
			<select name="id" id="delete-id" class="form-control" required>
				<option value="">Account auswählen</option>
				<c:forEach var="user" items="${usersToDelete}">
					<option value="${user.getUserId()}">
						<c:out value="${user.getUserId()}" />: <c:out value="${user.getName()}" /> <c:out value="${user.getSurname()}" /> (<c:out value="${user.getRole()}"></c:out>)
					</option>
				</c:forEach>
			</select>
			
			<button type="submit" class="btn btn-danger">Account löschen</button>
			<sec:csrfInput />
		</fieldset>
	</form>
</div>