<!-- Grundlage für unseren Bericht -->
<!-- Hier können wir immer mal wieder den aktuellen Stand und Entscheidungen festhalten, um damit unseren Bericht zu füllen -->

# kirjanystaevaet

## Abschlussbericht
Laut Guide der Vorlage benötigen wir die folgenden Dateien:
	
	lni.bst
	lni.cls

Alle anderen setze ich auf die `.gitignore` (auch das PDF, damit es keine Probleme gibt, kann sich ja jeder selber kompilieren). Die `latin1.sty` ist glaube ich nicht so wichtig, ich lasse die aber mal mit drin.

## Initialisierung

### Standard-Admin?

kommt noch.

### Data-Dump?

kommt noch.

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