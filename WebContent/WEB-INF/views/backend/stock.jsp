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
					<label for="kategorien-anlegen-input">Name der neuen Kategorie:</label>
					<input type="text" placeholder="Name der Kategorie" id="kategorien-anlegen-input" name="name" required />
					<button type="submit">Katgorie anlegen</button>
					<sec:csrfInput/>
				</form>
			<h4 id="kategorien-loeschen">Bestehende Kategorien löschen</h4>
				<form action="bestand/kategorien/delete" method="DELETE">
					<label for="kategorien-loeschen-input">Name der Kategorie:</label>
					<input type="text" placeholder="Name der Kategorie" id="kategorien-loeschen-input" name="name" required />
					<button type="submit">Katgorie löschen</button>
					<sec:csrfInput/>
				</form>
		</section>
		
		<section id="autorinnen">
			<h3>Autor:innen verwalten</h3>
			<h4 id="autorinnen-anlegen">Neue:n Autor:in anlegen</h4>
				<form action="bestand/autorinnen/add" method="POST">
					<button type="submit">Autor:in anlegen</button>
					<sec:csrfInput/>
				</form>
			
			<h4 id="autorinnen-aendern">Bestehende:n Autor:in ändern</h4>
				<form action="bestand/autorinnen/edit" method="POST">
					<button type="submit">Autor:in ändern</button>
					<sec:csrfInput/>
				</form>
						
			<h4 id="autorinnen-loeschen">Bestehende:n Autor:in löschen</h4>
				<form action="bestand/autorinnen/delete" method="DELETE">
					<button type="submit">Autor:in löschen</button>
					<sec:csrfInput/>
				</form>
		</section>
		
		<section id="buecher">
			<h3>Bücher verwalten</h3>
			<p><em>Hinweis:</em> Vor dem Anlegen neuer Bücher müssen Kategorien und Autor:innen bereits existieren.</p>
			
			<h4 id="buecher-anlegen">Neues Buch anlegen</h4>
				<form action="bestand/buecher/add" method="POST">
					<button type="submit">Buch anlegen</button>
					<sec:csrfInput/>
				</form>
			
			<h4 id="buecher-aendern">Bestehendes Buch ändern</h4>
				<form action="bestand/buecher/edit" method="POST">
					<button type="submit">Buch ändern</button>
					<sec:csrfInput/>
				</form>
						
			<h4 id="buecher-loeschen">Bestehendes Buch löschen</h4>
				<form action="bestand/buecher/delete" method="DELETE">
					<button type="submit">Buch löschen</button>
					<sec:csrfInput/>
				</form>
		</section>
	</div>
</section>