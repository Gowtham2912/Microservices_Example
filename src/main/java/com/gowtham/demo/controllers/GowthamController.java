package com.gowtham.demo.controllers;

import com.gowtham.demo.model.Book;
import com.gowtham.demo.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class GowthamController {

	
    private BookService service;

    @Autowired
    public GowthamController(BookService service) {
        this.service = service;
    }

    @GetMapping("/books")
    public String showAllBooks(Model model) {
        model.addAttribute("books", service.findAll());
        return "books";
    }

    @GetMapping("/new-book")
    public String showBookCreationForm(Model model) {
        model.addAttribute("book", new Book());
        return "new-book";
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String addNewBook(@Valid @ModelAttribute Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "new-book";
        }
        service.save(book);
        model.addAttribute("books", service.findAll());
        return "books";
    }

    @GetMapping("/{id}")
    public String showBookdById(@PathVariable int id, Model model) {
        Book book = service.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));
        model.addAttribute("book", book);
        return "edit-book";
    }

    @PostMapping("/{id}/update")
    public String updateBook(@PathVariable int id, @Valid @ModelAttribute Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "edit-book";
        }
        service.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));
        service.save(book);
        model.addAttribute("books", service.findAll());
        return "books";
    }

    @PostMapping("/{id}/delete")
    public String deleteBook(@PathVariable int id, Model model) {
        service.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));
        service.deleteById(id);
        model.addAttribute("books", service.findAll());
        return "books";
    }
}
