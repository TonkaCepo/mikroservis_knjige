package com.example.demo.book.service;

import com.example.demo.book.entity.BookEntity;
import com.example.demo.book.repo.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BookService {

    private final BookRepository repo;

    public BookService(BookRepository repo) {
        this.repo = repo;
    }

    public List<BookEntity> getAllVisible() {
        if (isAdmin()) {
            return repo.findAll();
        }
        return repo.findByIsPublicTrue();
    }

    public BookEntity getByIdVisible(Long id) {
        BookEntity book = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));

        if (!isAdmin() && !book.isPublic()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Not allowed to access this book");
        }
        return book;
    }

    public BookEntity create(BookEntity b) {
        b.setPublic(b.isPublic()); // default true ako ne pošalješ ništa
        return repo.save(b);
    }

    public BookEntity update(Long id, BookEntity req) {
        BookEntity book = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));

        book.setTitle(req.getTitle());
        book.setAuthor(req.getAuthor());
        book.setPublishedYear(req.getPublishedYear());
        book.setPublic(req.isPublic());

        return repo.save(book);
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found");
        }
        repo.deleteById(id);
    }

    private boolean isAdmin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) return false;
        return auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
    }
}
