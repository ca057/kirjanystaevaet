<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<section>
	<p>Hier ist die Suche</p>
	<form>
		Titel: <br>
		<input type="text" name="title">
		Autor_In: <br>
		<input type="text" name="author">
		ISBN: <br>
		<input type="text" name="isbn">
		Erscheinungsjahr: <br>
		<input type="text" name="year">
		Kategorie: <br>
		<input type="text" name="category">
		<br>
		<input type="submit" value="Los gehts :)">
	</form>
</section>