package com.mike.Books;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class Api {
    private final controller controller;
    public Api(Service Service){
        this.Service = Service;
    }
      
    @RequestMapping("/api/books")
    public List<Book> showAllBooks() {
        return Service.allBooks();
    }
    
   
    
    @RequestMapping(value="/api/books", method=RequestMethod.POST)
    public Book create(@RequestParam(value="title") String title, 
    				   @RequestParam(value="description") String desc, 
    				   @RequestParam(value="language") String lang, 
    				   @RequestParam(value="pages") Integer numOfPages) {
        Book book = new Book(title, desc, lang, numOfPages);
        return Service.createBook(book);
    }
    
    @RequestMapping("/api/books/{id}")
    public Book show(@PathVariable("id") Long id) {
    	Book book = Service.findBook(id);
    	return book;
    }
    
    @RequestMapping(value="/api/books/{id}", method=RequestMethod.PUT)
    public Book update(@PathVariable("id") Long id, 
    					@RequestParam(value="title") String title, 
    					@RequestParam(value="description") String desc,
    					@RequestParam(value="language") String lang, 
    					@RequestParam(value="pages") Integer numOfPages) {
        Book book = book.updateBook(id, title, desc, lang, numOfPages);
        return book;
    }
    
    @RequestMapping(value="/api/books/{id}", method=RequestMethod.DELETE)
    public void destroy(@PathVariable("id") Long id) {
        book.deleteBook(id);
    }
    
}