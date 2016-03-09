package web.controllers;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
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
import appl.logic.service.UserService;
import exceptions.controller.ControllerOvertaxedException;
import exceptions.data.DatabaseException;

@Controller
public class CartController {

	@Autowired
	private BookService bookService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private UserService userService;

	@Autowired
	private Cart cart;

	@RequestMapping(value = "/warenkorb", method = RequestMethod.POST)
	public String addToCart(@RequestParam(value = "isbn") String isbn) {
		System.out.println(isbn);
		try {
			System.out.println("User in Cart: " + getUser());
		} catch (ControllerOvertaxedException e1) {
			e1.printStackTrace();
		}
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

	// @RequestMapping(value = "/warenkorb", method = RequestMethod.DELETE)
	// public String deleteFromCart(@RequestParam(value = "isbn") String isbn) {
	// if (isbn != null && !isbn.isEmpty()) {
	// try {
	// cart.deleteBook(bookService.getBookByIsbn(isbn));
	// } catch (DatabaseException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	// return "redirect:/warenkorb";
	// }

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

		m.addAttribute("bookItems", tempBooks);
		// m.addAttribute("sum", cart.getPrice());

		return "cart";
	}

	@RequestMapping(value = "/bestellung_aufgegeben", method = RequestMethod.POST)
	public String orderContent(Model m) {
		try {
			User user = getUser();
			System.out.println(user.toString());
			if (!cart.isEmpty()) {
				if (user.getStreet() != null && user.getStreetnumber() != null && user.getPlz() != null) {
					Calendar cal = Calendar.getInstance();
					orderService.createOrder(cart.getBooks(), user.getUserId(), cal);
					cart.deleteContent();
				} else {
					System.out.println("User has no data.");
				}
				m.addAttribute("name", user.getName());
				m.addAttribute("surname", user.getSurname());
				m.addAttribute("street", user.getStreet());
				m.addAttribute("streetnumber", user.getStreetnumber());
				m.addAttribute("plz", user.getPlz());
				return "orderConducted";
			} else {
				return "redirect:/warenkorb";
			}
		} catch (DatabaseException | ControllerOvertaxedException e) {
			return "redirect:/warenkorb";
		}

	}

	@RequestMapping(value = "/buch_geloescht", method = RequestMethod.POST)
	public String deleteBook(@RequestParam(value = "isbn") String isbn) {
		Book b;
		try {
			b = bookService.getBookByIsbn(isbn);
		} catch (DatabaseException | NoSuchElementException e) {
			return "redirect:/warenkorb";
		}
		cart.deleteBook(b);
		return "redirect:/warenkorb";
	}

	private User getUser() throws ControllerOvertaxedException {
		Authentication a = SecurityContextHolder.getContext().getAuthentication();
		if (a == null) {
			throw new ControllerOvertaxedException("Authentication is null");
		} else {
			try {
				return userService.findByID(((User) a.getPrincipal()).getUserId()).get();
			} catch (DatabaseException | NoSuchElementException e) {
				throw new ControllerOvertaxedException(e.getMessage());
			}
		}
	}

}
