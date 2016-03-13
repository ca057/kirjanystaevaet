<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ page session="false" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="page-header">
	<h1>Registrierung</h1>
</div>

<form id="register-form" class="form">
	<fieldset>
		<p>Alle Felder müssen ausgefüllt sein.</p>

		<div class="form-group col-sm-6 col-md-4 col-lg-2">
			<label for="name">Vorname</label> <input type="text" id="name"
				name="name" class="form-control" required />
		</div>

		<div class="form-group col-sm-6 col-md-4 col-lg-2">
			<label for="surname">Nachname</label> <input type="text" id="surname"
				name="surname" class="form-control" required />
		</div>

		<div class="form-inline col-sm-6 col-md-4 col-lg-2">
				<label for="street">Straße und Nummer</label>
					<input type="text" id="street" name="street"
						class="form-control col-sm-10" required />
					<input type="text" id="streetnumber" name="streetnumber"
						class="form-control" required />
		</div>

		<div class="form-group col-sm-6 col-md-4 col-lg-2">
			<label for="plz">PLZ</label> <input type="text" id="plz"
				maxlength="5" class="form-control" required />
			<div class="hidden-info" id="plz-info-wrapper">
				<p id="plz-info"></p>
				<div id="plz-selection"></div>
			</div>
		</div>

		<div class="col-sm-6 col-md-4 col-lg-2">
			<label for="email">E-Mail</label> <input type="email" id="email"
				name="email" class="form-control" required />
		</div>

		<div class="form-group col-sm-6 col-md-4 col-lg-2">
			<label for="password">Passwort <span
				class="glyphicon glyphicon-info-sign" title="mind. 6 Zeichen"></span></label>
			<input type="password" id="password" name="password"
				class="form-control" required />
		</div>

		<div class="col-md-12">
			<label for="image">Profilbild</label> <input type="file" id="image"
				accept="image/*" name="file" />
		</div>

		<div class="col-md-12">
			<button type="submit" id="register-submit" title="Registrieren"
				class="btn btn-primary">Registrieren</button>
		</div>
		<sec:csrfInput />
		<p id="info-message" style="display: none; font-style: italic">&nbsp;</p>
	</fieldset>
</form>