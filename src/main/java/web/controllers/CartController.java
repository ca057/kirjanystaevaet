package web.controllers;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import appl.data.items.Book;
import appl.data.items.Cart;
import appl.data.items.User;
import appl.logic.service.BookService;

@Controller
public class CartController {
	private User user = (User) SecurityContextHolder.getContext().getAuthentication();

	@Autowired
	private BookService bookService;

	// @Autowired
	// private OrderService orderService;

	@Autowired
	private Cart cart;

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	// public void setOrderService(OrderService orderService){
	// this.setOrderService = orderService;
	// }

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

	@ResponseBody
	public String currentUserName(Authentication authentication) {
		return authentication.getName();
	}

	@RequestMapping(value = "/bestellen", method = RequestMethod.POST)
	public String orderContent() {
		if (user.getStreet() != null && user.getStreetnumber() != null && user.getPlz() != null) {
			Set<String> isbns = new HashSet<String>();
			Calendar cal = Calendar.getInstance();
			// books- Liste iterieren, getISbn -> zu set hinzufügen
			for (Book b : cart.getBooks()) {
				isbns.add(b.getIsbn());
			}
			// orderService.createOrder(isbns, userId, cal);
			// TODO orderService.METHOD
			cart.deleteContent();
		}
		return "redirect:/warenkorb";
	}
}
