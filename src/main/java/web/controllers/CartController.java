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

import appl.data.items.Book;
import appl.data.items.Cart;
import appl.data.items.User;
import appl.logic.service.BookService;
import exceptions.data.DatabaseException;

@Controller
public class CartController {

	public static User getUser() {
		Authentication a = SecurityContextHolder.getContext().getAuthentication();
		if (a == null) {
			return null;
		} else {
			return (User) a.getPrincipal();
		}
	}

	User user;

	public void setCurrentUser(User user) {
		this.user = user;
	}

	// private User user = (User)
	// SecurityContextHolder.getContext().getAuthentication().getPrincipal();

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
		m.addAttribute("bookItems", cart.getBooks());
		m.addAttribute("sum", cart.getPrice());
		return "cart";
	}

	@RequestMapping(value = "/bestellen", method = RequestMethod.POST)
	public String orderContent() {
		if (user.getStreet() != null && user.getStreetnumber() != null && user.getPlz() != null) {
			Set<String> isbns = new HashSet<String>();
			Calendar cal = Calendar.getInstance();
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
