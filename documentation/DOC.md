<!-- Grundlage für unseren Bericht -->
<!-- Hier können wir immer mal wieder den aktuellen Stand und Entscheidungen festhalten, um damit unseren Bericht zu füllen -->

# kirjanystaevaet

## Abschlussbericht
Laut Guide der Vorlage benötigen wir die folgenden Dateien:
	
	lni.bst
	lni.cls

Alle anderen setze ich auf die `.gitignore` (auch das PDF, damit es keine Probleme gibt, kann sich ja jeder selber kompilieren). Die `latin1.sty` ist glaube ich nicht so wichtig, ich lasse die aber mal mit drin.

## Allgemeines

### Vorgehensweise

- Verwendung von FeatureBranches
- keine strikte Aufgabentrennung, Bearbeitung vieler Features gemeinsam
- Viele Teamtreffen
- Versuch, Conventions so gut wie möglich einzuhalten (Interfaces, Separation of concerns, Exception handling etc.)
- Verwendung von github, da Probleme mit SVN+SSH+SVN (-> Probleme mit git? :D )
- Verwendung von Beans etc.
- Einschränkung der Sichtbarkeit so weit wie möglich (SoC etc.)

### Reflektion
- Späte Dokumentation
- keine Mockups zu Beginn
- Schwierigkeiten bei der Einarbeitung in Spring, Hibernate & co

## Initialisierung

### Standard-Admin?

Im Initialization-Bean

### Data-Dump?

kommt noch. Zu beachten: PLZ eingefügt und Anpassung, da Hibernate die Spalten alphabetisch ordnet. Muss in den Properties von Hibernate nicht extra angegeben werden, da automatisch nach import.sql an spezieller Stelle gesucht wird.

### ID / GeneratedValues

Strategy = Identity, weil Table etc. nicht geht bei Dump

## Sicherheit

### HTTPS

W�re produktiv gut, kann man im Bericht erw�hnen.

### Passw�rter

Sind mit BCrypt verschl�sselt. FunFact: Wird bei Login nicht entschl�sselt, sondern Usereingabe verschl�sselt und dann verglichen.

## Code-Interaktion

Wir verwenden keine Message-Objekte, sondern Maps. W�re bei gro�en Sachen zwar sch�ner, aber uns gerade zu umst�ndlich.

## Views

### Verwendung der Spring Security Tag Library

Die Bibliothek ist über die `pom.xml` aus irgendwelchen Gründen nicht verfügbar, kann aber [hier](http://mvnrepository.com/artifact/org.springframework.security/spring-security-taglibs/4.0.3.RELEASE) heruntergeladen werden und in den Build Path eingebunden werden. Trotzdem muss sie als dependency in die `pom.xml` eingetragen werden.

#### Links
- [Spring Docs: 27. JSP Tag Libraries](https://docs.spring.io/spring-security/site/docs/current/reference/html/taglibs.html)
- [Spring Security part V : Security tags](https://doanduyhai.wordpress.com/2012/02/26/spring-security-part-v-security-tags/)
- [Spring security at view layer using JSP taglibs](http://howtodoinjava.com/2013/04/18/spring-security-at-view-layer-using-jsp-taglibs/)

#### Beispiel `navigation.jsp`

Alle Tag-Libraries einbinden und mit einem Prefix kennzeichnen, mit dem sie dann später verwendet werden können:

```html
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
```

Verwendung der Tag-Library: Logt jemand sich als `USER` (siehe `SecurityConfig`), dann zeigen wir den Link zu __Mein Konto__:

```html
	<!-- weitere Listenelemente ausgelassen -->
	
	<sec:authorize access="hasRole('USER')">
		<li class="float-right"><a href="<c:url value='/meinkonto'/>" title="Mein Konto anzeigen">Mein Konto</a></li>
	</sec:authorize>
```

Alle, die eingeloggt sind sollen sich auch wieder ausloggen können, also jeder mit der Rolle `USER` oder `ADMIN`. Eine andere Möglichkeit wäre die Verwendung von `isAuthenticated()`. Da wir uns ausloggen, müssen wir wieder den `CSRF`-Token mitschicken. Daher brauchen wir ein Form, um den Token mitschicken zu können. Mit CSS müssen wir den Button natürlich noch verschwinden lassen...

```html
	<sec:authorize access="hasAnyRole('USER', 'ADMIN')">
		<li class="float-right">
			<form class="form-inline" action="<c:url value="/logout" />" method="post">
				<button type="submit" value="Aus Mein Konto abmelden">Abmelden</button>
				<sec:csrfInput/>
			</form>
		</li>
	</sec:authorize>
```

Alle anonymen Gäste bekommen die Möglichkeit, sich anzumelden. Wer nicht anonym ist, muss schon angemeldet sein und bekommt diese Option daher nicht angezeigt:

```html
	<sec:authorize access="isAnonymous()">
		<li class="float-right"><a href="<c:url value='/login'/>" title="In Mein Konto einloggen">Anmelden</a></li>
	</sec:authorize>
</ul>
```

#### Beispiel `user.jsp`
Über die Tag-Library können wir den Nutzernamen einblenden (oder auch das Passwort), da wir Zugriff auf das `principal`-Objekt haben. Wenn wir den Klarnamen anzeigen wollen (_Hallo Kim_ oder _Hallo Alex_), müssen wir das über den entsprechenden Controller via Model an den View weiterleiten, der das dann rendern kann.

```html
	<h2>Hallo <sec:authentication property="principal.username"/>!</h2>
```

## Database
### Cascading
Über den CascadeType in den Annotationen wird angegeben, welche Elemente, die in einer Relation mit einem Element stehen ebenfalls gelöscht/gespeichert werden, wenn letzteres gelöscht/gespeichert wird.
Wenn in der Annotation kein CascadeType angegeben ist, tritt der Default ein, nämlich, dass kein Cascading stattfindet.
In diesem Projekt wurden folgende Entscheidungen getroffen:
- Eine `Category` kann nur gelöscht werden, wenn es kein `Book` mehr mit dieser `Category` gibt. Ein socher Versuch führt zu einer `DatabaseException` mit einer entsprechendedn Errormessage.
- Wenn das letzte `Book` einer `Category` gelöscht wird, wird die `Category` nicht gelöscht. Es darf `Categories` geben, die keine `Books` enthalten.
- Wenn das letzte `Book` eines bestimmten `Authors` gelöscht wird, wird der `Author` ebenfalls gelöscht (`CascadeType.ALL`). `Author` wird hier anders behandelt als `Category`, da `Categories` potentiell besser weiter verwendet werden können als `Authors`. 

### DAOs
TODO: Im Grunde immutable -> Zugriff nur über Konstruktor.

### Books
Dürfen Books gelöscht werden: Problem Abhängigkeiten mit der Orderhistorie
Es gibt eine Methode zum löschen, sie sollte nur nicht standardmäßig verwendet werden. Stattdessen wird ein weiteres Feld `boolean available` eingeführt, dass angibt, ob ein Buch verkauft werden kann oder nicht. Hierbei gilt zu beachten, dass das ein Unterschied zu `int stock = 0 ` ist, was nur bedeutet, dass das Buch gerade nicht auf Lager ist, aber prinzipiell schon verkauft werden würde.

### Author
Darf nicht heiraten. Wenn Namen geändert werden, werden sie auch (durch die Verknüpfung von Buch und Autor) bei allen Büchern geändert. Das darf nicht sein, weil sich bei einer bestimmten Ausgabe von einem Buch nicht der Autorname ändern kann.
Andererseits darf (laut Johannes) ein und diesselbe Person nicht zwei Einträge in der Datenbank haben.

### User
Für "herkömmliche" User und Admins selbe Tabelle, Trennung durch Spalte "Role".

## Builder
Im create die Variablen wieder leeren, falls der Builder nochmal verwendet wird.
