package web.controllers;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import appl.data.items.Book;
import appl.data.items.Cart;
import appl.data.items.User;
import appl.logic.service.BookService;
import appl.logic.service.OrderService;
import exceptions.data.DatabaseException;

@Controller
public class CartController {

	// public void setCurrentUser(User user) {
	// this.user = user;
	// }

	// private User user = (User)
	// SecurityContextHolder.getContext().getAuthentication().getPrincipal();

	@Autowired
	private BookService bookService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private Cart cart;

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	// public void setOrderService(OrderService orderService) {
	// this.setOrderService = orderService;
	// }

	@RequestMapping(value = "/warenkorb", method = RequestMethod.POST)
	public String addToCart(@RequestParam(value = "isbn") String isbn) {
		System.out.println(isbn);
		System.out.println("User in Cart: " + getUser());
		if (isbn != null && !isbn.isEmpty()) {
			try {
				cart.addBook(bookService.getBookByIsbn(isbn));
			} catch (DatabaseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "redirect:/warenkorb";
	}

	@RequestMapping(value = "/warenkorb", method = RequestMethod.DELETE)
	public String deleteFromCart(@RequestParam(value = "isbn") String isbn) {
		if (isbn != null && !isbn.isEmpty()) {
			try {
				cart.deleteBook(bookService.getBookByIsbn(isbn));
			} catch (DatabaseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "redirect:/warenkorb";
	}

	@RequestMapping(value = "/warenkorb", method = RequestMethod.GET)
	public String getCart(Model m) {
		Book b = null;
		Map<Book, Integer> tempBooks = new HashMap<Book, Integer>();
		Set<String> isbns = cart.getBooks().keySet();
		for (String s : isbns) {
			try {
				b = bookService.getBookByIsbn(s);
			} catch (DatabaseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (tempBooks.containsKey(b)) {
				tempBooks.put(b, tempBooks.get(b) + 1);
			} else {
				tempBooks.put(b, 1);
			}
		}
		// TODO schau nach, wie du diese map dem Model übergibst! Vergiss den
		// View nicht, da noch über die map drüberiterieren!
		m.addAttribute("bookItems", tempBooks);
		// m.addAttribute("sum", cart.getPrice());

		return "cart";
	}

	@RequestMapping(value = "/bestellen", method = RequestMethod.POST)
	public String orderContent() throws DatabaseException {
		User user = getUser();
		if (user.getStreet() != null && user.getStreetnumber() != null && user.getPlz() != null) {
			// TODO: In der Bedingung user.getPlz() != null ergänzen, wenn sie
			// gesetzt ist
			Calendar cal = Calendar.getInstance();
			orderService.createOrder(cart.getBooks(), user.getUserId(), cal);
			cart.deleteContent();
		} else {
			System.out.println("User has no data.");
		}
		System.out.println("bestellung ausgeführt");
		return "redirect:/warenkorb";
	}

	private User getUser() {
		Authentication a = SecurityContextHolder.getContext().getAuthentication();
		if (a == null) {
			return null;
		} else {
			return (User) a.getPrincipal();
		}
	}
}
