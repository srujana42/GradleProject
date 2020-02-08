package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins="http://localhost:3000",maxAge = 3600)
@RestController
public class BookController {
    @Autowired
    BookRepository bookRepository;

    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/books/{id}")
    public Optional<Book> getAllBooksById(@PathVariable(value = "id") Integer id) {
        return bookRepository.findById(id);
    }

    @PostMapping("/books")
    public @Valid Book createBook(@Valid @RequestBody Book book) {
        return bookRepository.save(book);
    }

    @PutMapping("/books/{id}")
    public @Valid Book updateBook(@PathVariable(value = "id") Integer id, @Valid @RequestBody Book book)
            throws BookNotFoundException {
        Book b1 = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        b1.setName(book.getName());
        b1.setAuthor(book.getAuthor());
        Book updBook = bookRepository.save(b1);
        return updBook;
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable(value = "id") int id) throws BookNotFoundException {
        Book b2 = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        bookRepository.delete(b2);
        return ResponseEntity.ok().build();
    }
}

