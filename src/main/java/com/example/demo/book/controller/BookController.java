package com.example.demo.book.controller;

import com.example.demo.book.entity.BookEntity;
import com.example.demo.book.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    // USER + ADMIN (ali user vidi samo public)
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping
    public List<BookEntity> getAll() {
        return service.getAllVisible();
    }

    // USER + ADMIN (ali user ne može vidjeti hidden)
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/{id}")
    public BookEntity getById(@PathVariable Long id) {
        return service.getByIdVisible(id);
    }

    // ADMIN only
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookEntity create(@RequestBody BookEntity req) {
        return service.create(req);
    }

    // ADMIN only
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public BookEntity update(@PathVariable Long id, @RequestBody BookEntity req) {
        return service.update(id, req);
    }

    // ADMIN only
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
