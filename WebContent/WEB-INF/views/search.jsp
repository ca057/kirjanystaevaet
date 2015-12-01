<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<section>
	<h2>Suche</h2>
	<form>
		<label for="title">Titel</label>
		<input type="text" id="title" name="title"><br>
		<label for="title">AutorIn</label>
		<input type="text" name="author"><br>
		<lable for="isbn">ISBN</lable>
		<input type="text" name="isbn"><br>
		<label for="year">Erscheinungsjahr</label>
		<input type="text" name="year"><br>
		<lable for="category">Kategorie</label>
		<input type="text" name="category"><br>
		<input type="submit" value="Los gehts :)">
	</form>
	
	<h3>Suchergebnisse</h3>
</section>