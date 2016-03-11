<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ page session="false" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div id="stockmgmt">
	<c:if test="${param.error != null}">
		<p>
			<em>Fehler:</em> Bei der Abfrage der Daten ist ein Fehler mit der
			folgenden Fehlermeldung aufgetreten:
			<c:choose>
				<c:when test="${errormsg != null}">
		    		&bdquo;<c:out value="${errormsg}"></c:out>&ldquo;
		    	</c:when>
				<c:when test="${param.msg != null}">
		    		&bdquo;<c:out value="${param.msg}"></c:out>&ldquo;
		    	</c:when>
			</c:choose>
		</p>
	</c:if>

	<div class="page-header">
		<h1>Bestandsverwaltung</h1>
	</div>

	<ul class="nav nav-tabs">
		<li class="active"><a data-toggle="tab" href="#kategorien">Kategorien</a></li>
		<li><a data-toggle="tab" href="#autorinnen">Autor_innen</a></li>
		<li><a data-toggle="tab" href="#buecher">Bücher</a></li>
	</ul>
	<div class="tab-content">
		<div id="kategorien" class="tab-pane fade in active">
			<div class="row">
				<section>
					<div class="col-md-6">
						<form action="bestand/kategorien/add" method="POST">
							<fieldset>
								<legend>Kategorie anlegen</legend>
								<label for="kategorien-anlegen-input">Name der neuen
									Kategorie:</label> <input type="text" placeholder="Name der Kategorie"
									id="kategorien-anlegen-input" name="name" required />
								<button type="submit" class="btn btn-default" >Kategorie anlegen</button>
								<sec:csrfInput />
							</fieldset>
						</form>
					</div>
					<div class="col-md-6">
						<form action="bestand/kategorien/delete" method="POST">
							<fieldset>
								<legend>Kategorie löschen</legend>
								<c:choose>
									<c:when test="${categories.isEmpty()}">
										<p>Noch keine Kategorien in der Datenbank vorhanden.</p>
									</c:when>
									<c:otherwise>
										<label for="kategorien-loeschen-input">Name der
											Kategorie:</label>
										<select name="id" id="kategorien-loeschen-input" class="form-control" required>
											<option value="">Kategorie auswählen</option>
											<c:forEach var="category" items="${categories}">
												<option value="${category.getCategoryID()}"><c:out
														value="${category.getCategoryID()}" />:
													<c:out value="${category.getCategoryName()}" /></option>
											</c:forEach>
										</select>
										<button type="submit" class="btn btn-default" >Kategorie löschen</button>
										<sec:csrfInput />
									</c:otherwise>
								</c:choose>
							</fieldset>
						</form>
					</div>
				</section>
			</div>
		</div>
		<div id="autorinnen" class="tab-pane fade">
			<section>
				<p>
					<em>Hinweis: Neu angelegte Autor_innen stehen erst nach einem
						neuen Laden der Seite zur Verfügung.</em>
				</p>
				<div class="row">
					<div class="col-md-6">
						<form>
							<fieldset>
								<legend>Autor_in anlegen</legend>
								<label for="autorinnen-anlegen-first">Vorname:</label> <input
									id="autorinnen-anlegen-first" name="first" type="text"
									placeholder="Vorname" required /> <label
									for="autorinnen-anlegen-last">Nachname:</label> <input
									id="autorinnen-anlegen-last" name="last" type="text"
									placeholder="Nachname" required />

								<button type="submit" class="btn btn-default" id="autorinnen-anlegen-submit"
									class="btn btn-default">Autor_in anlegen</button>
								<sec:csrfInput />
							</fieldset>
						</form>
					</div>
					<div class="col-md-6">
						<form action="bestand/autorinnen/delete" method="POST">
							<fieldset>
								<legend>Autor_in löschen</legend>
								<label for="autorinnen-loeschen-id">Autor_innen
									auswählen</label> <select name="author" id="autorinnen-loeschen-id" class="form-control"
									multiple required>
									<c:forEach var="author" items="${authors}">
										<option value="${author.getAuthorId()}"><c:out
												value="${author.getAuthorId()}" />:
											<c:out value="${author.getNameF()}" />
											<c:out value="${author.getNameL()}" /></option>
									</c:forEach>
								</select>

								<button type="submit" class="btn btn-default">Autor_in
									löschen</button>
								<sec:csrfInput />
							</fieldset>
						</form>
					</div>
				</div>
			</section>
		</div>
		<div id="buecher" class="tab-pane fade">
			<section>
				<p>
					<em>Hinweis:</em> Vor dem Anlegen neuer Bücher müssen Kategorien
					und Autor_innen bereits existieren.
				</p>

				<form action="bestand/buecher/add" method="POST"
					enctype="multipart/form-data" class="form-inline">
					<fieldset>
						<c:choose>
							<c:when test="${categories.isEmpty() && authors.isEmpty()}">
								<legend>Buch anlegen</legend>
								<p>Noch keine Kategorien oder Autor_innen in der Datenbank
									vorhanden.</p>
							</c:when>
							<c:otherwise>
								<legend>Buch anlegen</legend>
								<div class="row">
									<div class="col-md-12">
										<div class="form-group">
											<label for="buecher-anlegen-isbn">ISBN</label> <input
												type="text" id="buecher-anlegen-isbn" name="isbn"
												placeholder="ISBN eingeben" required />
										</div>

										<div class="form-group">
											<label for="buecher-anlegen-title">Titel</label> <input
												type="text" id="buecher-anlegen-title" name="title"
												placeholder="Titel eingeben" required />
										</div>

										<div class="form-group">
											<label for="buecher-anlegen-price">Preis</label> <input
												type="text" id="buecher-anlegen-price" name="price"
												placeholder="Preis eingeben" required />
										</div>

										<div class="form-group">
											<label for="buecher-anlegen-publisher">Verlag</label> <input
												type="text" id="buecher-anlegen-publisher" name="publisher"
												placeholder="Verleger eingeben" required />
										</div>
									</div>


									<div class="col-md-12">
										<div class="form-group">
											<label for="buecher-anlegen-edition">Ausgabe</label> <input
												type="text" id="buecher-anlegen-edition" name="edition"
												placeholder="Ausgabe" required />
										</div>

										<div class="form-group">
											<label for="buecher-anlegen-pages">Seitenzahl</label> <input
												type="number" min="0" id="buecher-anlegen-pages"
												name="pages" placeholder="Seitenzahl eingeben" required />
										</div>

										<div class="form-group">
											<label for="buecher-anlegen-stock">Bestand</label> <input
												type="number" min="0" id="buecher-anlegen-stock"
												name="stock" placeholder="Bestand eingeben" required />
										</div>
										
										<div class="form-group">
											<label for="buecher-anlegen-cover">Cover</label><span class="btn btn-default btn-file" >Suchen<input
												type="file" accept="image/*" name="file" required ></span>
										</div>
									</div>

									<div class="col-md-12">
										<div class="form-group">
											<label for="buecher-anlegen-category">Kategorien
												auswählen</label> <select name="categories"
												id="buecher-anlegen-category" class="form-control" multiple
												required>
												<c:forEach var="category" items="${categories}">
													<option value="${category.getCategoryID()}"><c:out
															value="${category.getCategoryID()}" />:
														<c:out value="${category.getCategoryName()}" /></option>
												</c:forEach>
											</select>
										</div>

										<div class="form-group">
											<label for="buecher-anlegen-authors">Autor_innen
												auswählen</label> <select name="authors"
												id="buecher-anlegen-authors" class="form-control" multiple
												required>
												<c:forEach var="author" items="${authors}">
													<option value="${author.getAuthorId()}"><c:out
															value="${author.getAuthorId()}" />:
														<c:out value="${author.getNameF()}" />
														<c:out value="${author.getNameL()}" /></option>
												</c:forEach>
											</select>
										</div>

										<div class="form-group">
											<label for="buecher-anlegen-description">Beschreibung</label>
											<textarea class="form-control" rows="3" maxlength="4096"
												name="description" id="buecher-anlegen-description" required></textarea>
										</div>
									</div>

									<div class="col-md-12">
										<div class="form-group">
											<label for="buecher-anlegen-pubdate">Veröffentlichungsdatum</label>
											<div class="form-group">
												<label for="buecher-anlegen-day">Tag</label> <select
													name="day" id="buecher-anlegen-day" class="form-control" required>
													<option value="">Tag</option>
													<option value="1">1</option>
													<option value="2">2</option>
													<option value="3">3</option>
													<option value="4">4</option>
													<option value="5">5</option>
													<option value="6">6</option>
													<option value="7">7</option>
													<option value="8">8</option>
													<option value="9">9</option>
													<option value="10">10</option>
													<option value="11">11</option>
													<option value="12">12</option>
													<option value="13">13</option>
													<option value="14">14</option>
													<option value="15">15</option>
													<option value="16">16</option>
													<option value="17">17</option>
													<option value="18">18</option>
													<option value="19">19</option>
													<option value="20">20</option>
													<option value="21">21</option>
													<option value="22">22</option>
													<option value="23">23</option>
													<option value="24">24</option>
													<option value="25">25</option>
													<option value="26">26</option>
													<option value="27">27</option>
													<option value="28">28</option>
													<option value="29">29</option>
													<option value="30">30</option>
													<option value="31">31</option>
												</select>
											</div>
											<div class="form-group">
												<label for="buecher-anlegen-month">Monat</label> <select
													name="month" id="buecher-anlegen-month" class="form-control" required>
													<option value="">Monat auswählen</option>
													<option value="Januar">Januar</option>
													<option value="Februar">Februar</option>
													<option value="März">März</option>
													<option value="April">April</option>
													<option value="Mai">Mai</option>
													<option value="Juni">Juni</option>
													<option value="Juli">Juli</option>
													<option value="August">August</option>
													<option value="September">September</option>
													<option value="Oktober">Oktober</option>
													<option value="November">November</option>
													<option value="Dezember">Dezember</option>
												</select>
											</div>
											<div class="form-group">
												<label for="buecher-anlegen-year">Jahr</label> <input
													type="number" min="0" max="2222" id="buecher-anlegen-year"
													name="year" placeholder="Jahr eingeben" required />
											</div>
										</div>
									</div>


									<button type="submit" class="btn btn-default">Buch anlegen</button>
									<sec:csrfInput />
								</div>
							</c:otherwise>
						</c:choose>
					</fieldset>
				</form>

				<form action="bestand/buecher/edit" method="POST"
					enctype="multipart/form-data">
					<fieldset>
						<legend>Buch ändern</legend>
						<c:choose>
							<c:when test="${books.isEmpty()}">
								<p>Noch keine Bücher zum Ändern in der Datenbank vorhanden.</p>
							</c:when>
							<c:otherwise>
								<p class="hidden" id="buecher-aendern-data-loading"><em>Daten werden geladen, bitte warten...</em></p>
								<label for="buecher-aendern-isbn"></label>
								<select name="isbn" id="buecher-aendern-isbn" class="form-control" required>
									<option value="">Buch auswählen</option>
									<c:forEach var="book" items="${books}">
										<option value="${book.getIsbn()}">
											<c:out value="${book.getIsbn()}"></c:out>:
											<c:out value="${book.getTitle()}"></c:out>
										</option>
									</c:forEach>
								</select>
								<label for="buecher-aendern-categories">Kategorien
									auswählen:</label>
								<select name="categories" id="buecher-aendern-categories" class="form-control" multiple>
									<c:forEach var="category" items="${categories}">
										<option value="${category.getCategoryID()}"><c:out
												value="${category.getCategoryID()}" />:
											<c:out value="${category.getCategoryName()}" /></option>
									</c:forEach>
								</select>

								<label for="buecher-aendern-title">Titel:</label>
								<input type="text" id="buecher-aendern-title" name="title"
									placeholder="Titel eingeben" />

								<label for="buecher-aendern-authors">Autor_innen
									auswählen</label>
								<select name="authors" id="buecher-aendern-authors" class="form-control" multiple>
									<c:forEach var="author" items="${authors}">
										<option value="${author.getAuthorId()}"><c:out
												value="${author.getAuthorId()}" />:
											<c:out value="${author.getNameF()}" />
											<c:out value="${author.getNameL()}" /></option>
									</c:forEach>
								</select>

								<label for="buecher-aendern-description">Beschreibung:</label>
								<textarea rows="10" cols="50" maxlength="4096"
									name="description" id="buecher-aendern-description"></textarea>

								<label for="buecher-aendern-price">Preis:</label>
								<input type="text" id="buecher-aendern-price" name="price"
									placeholder="Preis eingeben" />

								<label for="buecher-aendern-publisher">Verleger:</label>
								<input type="text" id="buecher-aendern-publisher"
									name="publisher" placeholder="Verleger eingeben" />

								<p>Veröffentlichungsdatum</p>
								<label for="buecher-aendern-day">Tag</label>
								<select name="day" class="form-control" id="buecher-aendern-day">
									<option value="">Tag auswählen</option>
									<option value="1">1</option>
									<option value="2">2</option>
									<option value="3">3</option>
									<option value="4">4</option>
									<option value="5">5</option>
									<option value="6">6</option>
									<option value="7">7</option>
									<option value="8">8</option>
									<option value="9">9</option>
									<option value="10">10</option>
									<option value="11">11</option>
									<option value="12">12</option>
									<option value="13">13</option>
									<option value="14">14</option>
									<option value="15">15</option>
									<option value="16">16</option>
									<option value="17">17</option>
									<option value="18">18</option>
									<option value="19">19</option>
									<option value="20">20</option>
									<option value="21">21</option>
									<option value="22">22</option>
									<option value="23">23</option>
									<option value="24">24</option>
									<option value="25">25</option>
									<option value="26">26</option>
									<option value="27">27</option>
									<option value="28">28</option>
									<option value="29">29</option>
									<option value="30">30</option>
									<option value="31">31</option>
								</select>
								<label for="buecher-aendern-month">Monat</label>
								<select name="month" class="form-control" id="buecher-aendern-month">
									<option value="">Monat auswählen</option>
									<option value="Januar">Januar</option>
									<option value="Februar">Februar</option>
									<option value="März">März</option>
									<option value="April">April</option>
									<option value="Mai">Mai</option>
									<option value="Juni">Juni</option>
									<option value="Juli">Juli</option>
									<option value="August">August</option>
									<option value="September">September</option>
									<option value="Oktober">Oktober</option>
									<option value="November">November</option>
									<option value="Dezember">Dezember</option>
								</select>
								<label for="buecher-aendern-year">Jahr:</label>
								<input type="number" min="0" max="2222"
									id="buecher-aendern-year" name="year"
									placeholder="Jahr eingeben" />

								<label for="buecher-aendern-edition">Ausgabe:</label>
								<input type="text" id="buecher-aendern-edition" name="edition"
									placeholder="Ausgabe" />

								<label for="buecher-aendern-pages">Seitenzahl:</label>
								<input type="number" min="0" id="buecher-aendern-pages"
									name="pages" placeholder="Seitenzahl eingeben" />

								<label for="buecher-aendern-cover">Cover:</label>
								<input type="file" accept="image/*" name="file" />

								<button type="submit" class="btn btn-default" >Buch ändern</button>
								<sec:csrfInput />
							</c:otherwise>
						</c:choose>
					</fieldset>
				</form>

				<c:choose>
					<c:when test="${books.isEmpty()}">
						<legend>Bestand ändern</legend>
						<p>Noch keine Bücher in der Datenbank vorhanden.</p>
					</c:when>
					<c:otherwise>
						<legend>Bestand ändern</legend>
						<p>Die eingetragenen Änderungen werden zum bisherigen Bestand
							hinzu addiert bzw. abgezogen.</p>
						<form action="bestand/buecher/stock" method="POST">
							<fieldset>
								<label for="buecher-stock-isbn"></label> <select name="isbn"
									id=buecher-stock-isbn class="form-control" required>
									<option value="">Buch auswählen</option>
									<c:forEach var="book" items="${books}">
										<option value="${book.getIsbn()}"><c:out
												value="${book.getIsbn()}"></c:out>:
											<c:out value="${book.getTitle()}"></c:out></option>
									</c:forEach>
								</select> <label for="buecher-stock-stock">Änderung:</label> <input
									type="number" id="buecher-stock-stock" name="stock"
									placeholder="Änderung eingeben">
								<button type="submit" class="btn btn-default" >Bestand ändern</button>
								<sec:csrfInput />
							</fieldset>
						</form>
					</c:otherwise>
				</c:choose>

				<c:choose>
					<c:when test="${books.isEmpty()}">
						<legend>Buch löschen</legend>
						<p>Noch keine Bücher in der Datenbank vorhanden.</p>
					</c:when>
					<c:otherwise>
						<form action="bestand/buecher/delete" method="POST">
							<fieldset>
								<legend>Buch löschen</legend>
								<label for="buecher-loeschen-isbn"></label> <select name="isbn"
									id="buecher-loeschen-isbn" class="form-control" required>
									<option value="">Buch auswählen</option>
									<c:forEach var="book" items="${books}">
										<option value="${book.getIsbn()}"><c:out
												value="${book.getIsbn()}"></c:out>:
											<c:out value="${book.getTitle()}"></c:out></option>
									</c:forEach>
								</select>
								<button type="submit" class="btn btn-default" >Buch löschen</button>
								<sec:csrfInput />
							</fieldset>
						</form>
					</c:otherwise>
				</c:choose>
			</section>
		</div>
	</div>
</div>
