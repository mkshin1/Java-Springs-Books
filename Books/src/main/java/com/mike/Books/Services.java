package com.mike.Books;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.tiffany.mvc.models.Book;
import com.tiffany.mvc.repos.BookRepository;

@Service
public class Service {
    //
    private final Repo Repo;
    
    public Service(Repo Repo) {
        this.Repo = Repo;
    }

    public List<Book> allBooks() {
        return Repo.findAll();
    }

    public Book createBook(Book b) {
        return Repo.save(b);
    }
  
    public Book findBook(Long id) {
        Optional<Book> optionalBook = Repo.findById(id);
        if(optionalBook.isPresent()) {
            return optionalBook.get();
        } else {
            return null;
        }
    }
    
    
    public Book updateBook(Book b) {
    	/
    	return Repo.save(b);
    }
    
    public void deleteBook(Long id) {
    	Repo.deleteById(id);
    }
}