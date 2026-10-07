package com.library.librarysystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "*")
public class BookController {

    @Autowired
    private BookRepository bookRepo;

    // 1. Get all books
    @GetMapping
    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }

    // 2. Add New Book
    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book savedBook = bookRepo.save(book);
        return ResponseEntity.ok(savedBook);
    }

    // 3. Borrow Book
    @PostMapping("/{id}/borrow")
    public ResponseEntity<?> borrowBook(@PathVariable Long id) {
        Book book = bookRepo.findById(id).orElse(null);

        if (book == null) {
            return ResponseEntity.notFound().build();
        }

        if (book.getAvailableCopies() <= 0) {
            return ResponseEntity.badRequest().body("Out of stock!");
        }

        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepo.save(book);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Book issued successfully");
        response.put("dueDate", LocalDate.now().plusDays(14).toString());
        response.put("remainingCopies", book.getAvailableCopies());

        return ResponseEntity.ok(response);
    }

    // 4. Return Book
    @PostMapping("/{id}/return")
    public ResponseEntity<?> returnBook(@PathVariable Long id) {
        Book book = bookRepo.findById(id).orElse(null);

        if (book == null) {
            return ResponseEntity.notFound().build();
        }

        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepo.save(book);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Book returned successfully");
        response.put("remainingCopies", book.getAvailableCopies());

        return ResponseEntity.ok(response);
    }
}