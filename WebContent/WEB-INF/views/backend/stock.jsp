<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page session="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div id="stockmgmt">
	<c:if test="${param.error != null}">
	    <p><em>Fehler:</em> Bei der Abfrage der Daten ist ein Fehler mit der folgenden Fehlermeldung aufgetreten: &bdquo;<c:out value="${errormsg}"></c:out>&ldquo;</p>
	</c:if>

	<h2>Bestandsverwaltung</h2>
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
				<c:choose>
					<c:when test="${categories.isEmpty()}">
						<p>Noch keine Kategorien in der Datenbank vorhanden.</p>
					</c:when>
					<c:otherwise>
						<label for="kategorien-loeschen-input">Name der Kategorie:</label>
						<select name="name" id="kategorien-loeschen-input" required>
							<c:forEach var="category" items="${categories}">
								<option value="${category.getCategoryID()}"><c:out value="${category.getCategoryID()}" />: <c:out value="${category.getCategoryName()}" /></option>
							</c:forEach>			
						</select>
						<button type="submit">Katgorie löschen</button>
						<sec:csrfInput/>
					</c:otherwise>
				</c:choose>	
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
				<c:choose>
					<c:when test="${categories.isEmpty() && authors.isEmpty()}">
						<p>Noch keine Kategorien oder Autor:innen in der Datenbank vorhanden.</p>					
					</c:when>		
					<c:otherwise>
						<label for="buecher-anlegen-category">Kategorien auswählen:</label>
						<select name="categories" id="buecher-anlegen-category" multiple>
							<c:forEach var="category" items="${categories}">
								<option value="${category.getCategoryID()}"><c:out value="${category.getCategoryID()}" />: <c:out value="${category.getCategoryName()}" /></option>
							</c:forEach>
						</select>
					
						<label for="buecher-anlegen-isbn">ISBN:</label>
						<input type="text" id="buecher-anlegen-isbn" name="isbn" placeholder="ISBN eingeben" required/>
	
						<label for="buecher-anlegen-title">Titel:</label>
						<input type="text" id="buecher-anlegen-title" name="title" placeholder="Titel eingeben" required/>
						
						<label for="buecher-anlegen-description">Beschreibung:</label>
						<textarea rows="10" cols="50" maxlength="1024" name="description" id="buecher-anlegen-description" required></textarea>
						
						<label for="buecher-anlegen-price">Preis:</label>
						<input type="number" min="0" id="buecher-anlegen-price" name="price" placeholder="Preis eingeben" required/>
						
						<label for="buecher-anlegen-publisher">Verleger:</label>
						<input type="text" id="buecher-anlegen-publisher" name="publisher" placeholder="Verleger eingeben" required/>
						
						<label for="buecher-anlegen-date">Veröffentlichungsdatum:</label>
						<input type="date" id="buecher-anlegen-date" name="date" required autocomplete/>
						
						<label for="buecher-anlegen-edition">Ausgabe:</label>
						<input type="text" id="buecher-anlegen-edition" name="edition" placeholder="Ausgabe" required/>
	
						<label for="buecher-anlegen-pages">Seitenzahl:</label>
						<input type="number" min="0" id="buecher-anlegen-pages" name="pages" placeholder="Seitenzahl eingeben" required/>
			
						<label for="buecher-anlegen-authors">Autor:innen auswählen</label>
						<select name="authors" id="buecher-anlegen-authors" multiple required>
							<c:forEach var="author" items="${authors}">
								<option value="${author.getAuthorId()}"><c:out value="${author.getAuthorId()}" />: <c:out value="${author.getNameF()}" /> <c:out value="${author.getNameL()}" /></option>
							</c:forEach>
						</select>

						<button type="submit">Buch anlegen</button>
						<sec:csrfInput/>
					</c:otherwise>	
				</c:choose>
			</form>
		
		<h4 id="buecher-aendern">Bestehendes Buch ändern</h4>
			<form action="bestand/buecher/edit" method="POST">
				<button type="submit">Buch ändern</button>
				<sec:csrfInput/>
			</form>
					
		<h4 id="buecher-loeschen">Bestehendes Buch löschen</h4>
			<c:choose>
				<c:when test="${books.isEmpty()}">
					<p>Noch keine Bücher in der Datenbank vorhanden.</p>
				</c:when>
				<c:otherwise>
					<form action="bestand/buecher/delete" method="DELETE">
						<label for="buecher-loeschen-isbn"></label>
						<select name="isbn" id=buecher-loeschen-isbn required>
							<c:forEach var="book" items="${books}">
								<option value="${book.getIsbn()}"><c:out value="${book.getIsbn()}"></c:out>: <c:out value="${book.getTitle()}"></c:out></option>		
							</c:forEach>						
						</select>
						<button type="submit">Buch löschen</button>
						<sec:csrfInput/>					
					</form>
				</c:otherwise>
			</c:choose>
	</section>
</div>