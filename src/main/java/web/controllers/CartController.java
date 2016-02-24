package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import appl.data.items.Cart;
import appl.logic.service.BookService;

@Controller
public class CartController {

	@Autowired
	private BookService bookService;

	@Autowired
	private Cart cart;

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	@RequestMapping(value = "/warenkorb", method = RequestMethod.POST)
	public String addToCart(@RequestParam(value = "isbn") String isbn) {
		System.out.println(isbn);
		if (isbn != null && !isbn.isEmpty()) {
			cart.addBook(bookService.getBookByIsbn(isbn));
		}
		return "redirect:/warenkorb";
	}

	@RequestMapping(value = "/warenkorb", method = RequestMethod.DELETE)
	public String deleteFromCart(@RequestParam(value = "isbn") String isbn) {
		if (isbn != null && !isbn.isEmpty()) {
			cart.deleteBook(bookService.getBookByIsbn(isbn));
		}
		return "redirect:/warenkorb";
	}

	@RequestMapping(value = "/warenkorb", method = RequestMethod.GET)
	public String getCart(Model m) {
		m.addAttribute("bookItems", cart.getBooks());
		m.addAttribute("sum", cart.getPrice());
		return "cart";
	}

	@RequestMapping(value = "/bestellen", method = RequestMethod.POST)
	public String orderContent() {
		// TODO implement order
		cart.deleteContent();
		return "";
	}
}
