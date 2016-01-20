package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

	@RequestMapping(value = "/warenkorb", method = RequestMethod.POST)
	public String addToCart(@RequestParam(value = "isbn") String isbn) {
		System.out.println(isbn);
		return cartView;
	}
	// TODO: cart-logic erstellen. Nutzerspezifika implementieren, Methode:
	// showCart: holt sich User aus Datenbank, Items im Cart und zeigt sie an
	// Preisrechner, Liste mit Büchern

}
