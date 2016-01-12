package web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CartController {

	@RequestMapping(value = "/warenkorb", method = RequestMethod.POST)
	public String addToCart(@RequestParam(value = "isbn") String isbn) {
		System.out.println(isbn);
		return "cart";
	}
	// TODO: cart-logic erstellen. Nutzerspezifika implementieren, Methode:
	// showCart: holt sich User aus Datenbank, Items im Cart und zeigt sie an
	// Preisrechner, Liste mit Büchern
}
