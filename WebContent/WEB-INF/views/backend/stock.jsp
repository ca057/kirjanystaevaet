<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page session="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<section>
	<h2>Bestandsverwaltung</h2>
	<div id="stockmgmt">
		<div class="nav-sub">
			<ul>
				<li>Kategorien
					<ul>
						<li>Anlegen</li>
						<li>Ändern</li>
						<li>Löschen</li>
					</ul>
				</li>
				<li>Autor:innen
					<ul>
						<li>Anlegen</li>
						<li>Ändern</li>
						<li>Löschen</li>
					</ul>
				</li>
				<li>Bücher
					<ul>
						<li>Anlegen</li>
						<li>Ändern</li>
						<li>Löschen</li>
					</ul>
				</li>
			</ul>
		</div>
		<section>
			<p>Bevor ein neues Buch angelegt werden kann, müssen Kategorie und alle Autoren bereits existieren.</p>
			<p>Löschvorgänge sind nur möglich, wenn alle abhängigen Einträge nicht mehr existieren.</p>
		</section>
	</div>
</section>