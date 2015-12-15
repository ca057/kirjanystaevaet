package web;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import appl.data.items.Author;
import appl.data.items.Book;
import appl.logic.service.BookService;

@Controller
@RequestMapping(value = "/buch/{isbn}")
public class SingleBookController {

	@Autowired
	private BookService service;

	public void setService(BookService service) {
		this.service = service;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String getBook(@PathVariable("isbn") String isbn, Model m) {
		if (isbn != null && !isbn.isEmpty()) {
			// TODO do something when there is no book returned, because the
			// isbn does not exists --> what will bookservice return if no book
			// is found?
			// TODO return correct book
			// m.addAttribute("book", getBookFromDatabase(isbn));
			Book dummyBook = new Book();
			dummyBook.setTitle("Service Oriented Architecture");
			dummyBook.setIsbn("0131428985");
			dummyBook.setDescription(
					"This is a comprehensive tutorial that teaches fundamental and advanced SOA design principles, supplemented with detailed case studies and technologies used to implement SOAs in the real world. ***We'll have cover endorsements from Tom Glover, who leads IBM's Web Services Standards initiatives; Dave Keogh, Program Manager for Visual Studio Enterprise Tools at Microsoft, and Sameer Tyagi, Senior Staff Engineer, Sun Microsystems. All major software manufacturers and vendors are promoting support for SOA. As a result, every major development platform now officially supports the creation of service-oriented solutions. Parts I, II, and III cover basic and advanced SOA concepts and theory that prepare you for Parts IV and V, which provide a series of step-by-step 'how to' instructions for building an SOA. Part V further contains coverage of WS-* technologies and SOA platform support provided by J2EE and .NET.");
			HashSet<Author> authorSet = new HashSet();
			Author author = new Author();
			author.setNameF("FirstName");
			author.setNameL("LastName");
			authorSet.add(author);
			author.setNameF("FirstName yeah");
			author.setNameL("LastName chabo");
			authorSet.add(author);
			dummyBook.setAuthors(authorSet);
			m.addAttribute("book", dummyBook);
		}
		return "book";
	}

	private Book getBookFromDatabase(String isbn) {
		return service.getBookByIsbn(isbn);
	}

}
