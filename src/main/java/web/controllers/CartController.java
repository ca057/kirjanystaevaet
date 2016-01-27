package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import appl.logic.service.BookService;

@Controller
public class CartController {

	@Autowired
	private BookService bookService;

	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	private final static String cartView = "cart";

	@RequestMapping(value = "/warenkorb", method = RequestMethod.GET)
	public String addToCart(@RequestParam(value = "isbn", required = false) String isbn, Model m) {
		System.out.println(isbn);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName(); // get logged in User
		m.addAttribute("name", name);
		return cartView;
	}
	// TODO: cart-logic erstellen. Nutzerspezifika implementieren, Methode:
	// showCart: holt sich User aus Datenbank, Items im Cart und zeigt sie an
	// Preisrechner, Liste mit Büchern
	// eingeloggten Nutzer finden, Warenkorb holen getCart() --> Liste
	// zurückgeben, Buch hinzufügen
	// Buch über ISBN getBook();
	//

}
