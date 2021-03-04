package com.mike.Books;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mike.mvc.models.Book;
import com.mike.mvc.services.BookService;

@Controller
public class Controller {

// ***** Autowire let's us REMOVE lines 26,29-31  - Syntax is below to replcae those lines *****
//	@Autowired
//	private BookService bookService;
	
	
    private final Service Service;
    
    
    public Controller(Service Service) {
        this.Service = Service;
    }
    
    @RequestMapping("/books")
    public String index(Model model) {
        List<Book> books = Service.allBooks();
        model.addAttribute("books", books);
        return "/books/index.jsp";
    }
    
//    This will take us to our new form from new.jsp
    @RequestMapping("/books/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "/books/new.jsp";
    }
    
    @RequestMapping(value="/books", method=RequestMethod.POST)
    // @Valid annotation to check to see if the submitted object passes validation.
    // inject the BindingResult to see if the object passed validation. This MUST come immediately after the @Valid parameter.
    public String create(@Valid @ModelAttribute("book") Book book, BindingResult result) {
        if (result.hasErrors()) {
            return "/books/new.jsp";
        } else {
            Service.createBook(book);
            return "redirect:/books";
        }
    }
    
    // Show specific book's info
//    @GetMapping("/books/{id}")
    @RequestMapping(value="/books/{id}")
    public String showBookInfo(@PathVariable("id") Long id, Model model) {
    	// next 2 lines are the same as line 64!
//    	Book book = bookService.findBook(id);
//    	model.addAttribute("book", book);
    	model.addAttribute("book", Service.findBook(id));
    	return "/books/show.jsp";
    }
    
    // Edit a specific book - will DISPLAY the edit form
    @GetMapping("/books/{id}")
//    @RequestMapping("/books/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model) {
        Book book = Service.findBook(id);
        model.addAttribute("book", book);
        return "/books/edit.jsp";
    }
    
    // Update a specific book -- PROCESSES the edit form
    @PutMapping("/books/{id}")
//    @RequestMapping(value="/books/{id}", method=RequestMethod.PUT)
    public String update(@Valid @ModelAttribute("book") Book book, BindingResult result) {
        if (result.hasErrors()) {
            return "/books/edit.jsp";
        } else {
            Service.updateBook(book);
            return "redirect:/books";
        }
    }
    
    // Delete book
    @RequestMapping(value="/books/{id}", method=RequestMethod.DELETE)
    public String destroy(@PathVariable("id") Long id) {
        Service.deleteBook(id);
        return "redirect:/books";
    }
}