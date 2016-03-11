<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page session="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="page-header">
	<h1>Registrierung</h1>
</div>

<p>Schon einen Account? Hier geht es zum <a href="<c:url value="/login" />" title="Zum Login">Login</a>!</p>

<form id="register-form" class="form-inline">
	<fieldset>
		<legend>Neu registrieren</legend>
		<p>Alle Felder müssen ausgefüllt sein.</p>
		
		<div class="form-group">
		<label for="name">Vorname</label>
		<input type="text" id="name" name="name" required/>
		</div>
		
		<div class="form-group">
		<label for="surname">Nachname</label>
		<input type="text" id="surname" name="surname" required/>
		</div>
		
		<div class="form-group">
		<label for="street">Straße</label>
		<input type="text" id="street" name="street" required/>
		</div>
		
		<div class="form-group">
		<label for="streetnumber">Hausnummer</label>
		<input type="text" id="streetnumber" name="streetnumber" required/>
		</div>
		
		<div class="form-group">
		<label for="plz">PLZ</label>
		<input type="text" id="plz" maxlength="5" required/>
		<div class="hidden-info" id="plz-info-wrapper">
			<p id="plz-info"></p>
			<div id="plz-selection"></div>
		</div>
		</div>
		
		<div class="form-group">
		<label for="email">E-Mail</label>
		<input type="email" id="email" name="email" required/>
		</div>
		
		<div class="form-group">
		<label for="password">Passwort <span class="glyphicon glyphicon-info-sign" title="mind. 6 Zeichen"></span></label>
		<input type="password" id="password" name="password" required/>
		</div>
		
		<div class="form-group">
		<label for="image">Profilbild</label>
		<input type="file" id="image" accept="image/*" name="file" />
		</div>
		<button type="submit" id="register-submit" title="Registrieren" class="btn btn-default">Registrieren</button>
		<sec:csrfInput/>
		<p id="info-message" style="display:none;font-style:italic">&nbsp;</p>
	</fieldset>
</form>