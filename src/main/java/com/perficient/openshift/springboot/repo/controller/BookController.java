package com.perficient.openshift.springboot.repo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import javax.validation.Valid;
import java.util.List;
import com.perficient.openshift.springboot.repo.model.Book;
import com.perficient.openshift.springboot.repo.repository.BookRepository;
import com.perficient.openshift.springboot.repo.exception.BookNotFoundException;
@RestController
public class BookController {

    @Autowired
    BookRepository bookRepository;

    // Get All Notes
    @GetMapping("/books")


    public List<Book> getAllNotes() {
        System.out.println("It works");
        return bookRepository.findAll();
    }

    // Create a new Note
    @PostMapping("/books")
    public Book createNote(@Valid @RequestBody Book book) {
        System.out.println("Webhook check");
        return bookRepository.save(book);
    }

    // Get a Single Note
    @GetMapping("/books/{id}")
    public Book getNoteById(@PathVariable(value = "id") Long bookId) throws BookNotFoundException {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));
    }

    // Update a Note
    @PutMapping("/books/{id}")
    public Book updateNote(@PathVariable(value = "id") Long bookId,
                           @Valid @RequestBody Book bookDetails) throws BookNotFoundException {

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));

        book.setTitle(bookDetails.getTitle());
        book.setAuthorName(bookDetails.getAuthorName());
        book.setIsbn(bookDetails.getIsbn());

        Book updatedBook = bookRepository.save(book);

        return updatedBook;
    }

    // Delete a Note
    @DeleteMapping("/books/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable(value = "id") Long bookId) throws BookNotFoundException {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));

        bookRepository.delete(book);

        return ResponseEntity.ok().build();
    }
}