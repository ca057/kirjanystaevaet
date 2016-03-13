<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ page session="false" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<section>
	<div class="page-header">
		<h1>Login</h1>
	</div>
	<div class="row">
		<div class="col-sm-4">
			<form action="<c:url value="/login" />" method="post" class="form">
				<fieldset>
					<c:if test="${param.error != null}">
						<p class="error">Ungültiger Benutzername oder Passwort.</p>
					</c:if>
					<div class="form-group">
						<label for="username">E-Mail</label> <input type="text"
							id="username" name="username" class="form-control col-sm-6" />
					</div>
					<div class="form-group">
						<label for="password">Passwort</label> <input type="password"
							id="password" name="password" class="form-control col-sm-6" />
					</div>
					<button type="submit" value="In Mein Konto anmelden"
						class="btn btn-primary">Anmelden</button>
					<sec:csrfInput />
				</fieldset>
			</form>
			<p>
				Noch kein Konto? Hier kommst du zur <a
					href="<c:url value='/registrierung' />" title="Zur Registrierung">Registrierung</a>.
			</p>
		</div>
	</div>

</section>