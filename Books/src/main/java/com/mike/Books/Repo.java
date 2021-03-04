package com.mike.Books;


import java.util.List;

import org.springframework.data.repository.CrudRepository; /
import org.springframework.stereotype.Repository;

import com.tiffany.mvc.models.Book;

@Repository
public interface Repo extends Repo<Book, Long>{ 
    List<Book> findAll();
    

    List<Book> findByDescriptionContaining(String search);

    Long countByTitleContaining(String search);

    Long deleteByTitleStartingWith(String search);
}