package com.gowtham.demo.services;

import com.gowtham.demo.model.Book;
import com.gowtham.demo.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BookService {

    private BookRepository repository;

    @Autowired
    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public List<Book> findAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Optional<Book> findById(int id) {
        return repository.findById(id);
    }

    public Book save(Book stock) {
        return repository.save(stock);
    }

    public void deleteById(int id) {
        repository.deleteById(id);
    }
}