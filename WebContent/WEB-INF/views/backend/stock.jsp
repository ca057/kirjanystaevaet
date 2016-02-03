<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page session="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<section>
	<h2>Bestandsverwaltung</h2>
	<div id="stockmgmt">
		<section id="kategorien">
			<h3>Kategorien verwalten</h3>
			<h4 id="kategorien-anlegen">Neue Kategorie anlegen</h4>
				<form action="bestand/kategorien/add" method="POST">
					<label for="kategorien-anlegen-input">Neue Kategorie:</label>
					<input type="text" placeholder="Name der Kategorie" id="kategorien-anlegen-input" required />
					<button type="submit" value="add-category">Katgorie anlegen</button>
					<sec:csrfInput/>
				</form>
			<h4 id="kategorien-aendern">Bestehende Kategorie ändern</h4>
			<h4 id="kategorien-loeschen">Bestehende Kategorien löschen</h4>
		</section>
		
		<section id="autorinnen">
			<h3>Autor:innen verwalten</h3>
			<h4></h4>
		</section>
		
		<section id="buecher">
			<h3>Bücher verwalten</h3>
			<p><em>Hinweis:</em> Vor dem Anlegen neuer Bücher müssen Kategorien und Autor:innen bereits existieren.</p>
		</section>
	</div>
</section>