package com.improving.bootcamp.api;

import com.fasterxml.jackson.annotation.JsonView;
import com.improving.bootcamp.Book;
import com.improving.bootcamp.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class SimpleApiController { //create different controller because want all these to resolve to JSON

    private final BookRepository bookRepository;

    public SimpleApiController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @JsonView(JsonViews.SummaryView.class)
    @GetMapping("/books")
    public List<Book> books() {
        return bookRepository.getBooks();
    }

    @JsonView(JsonViews.DetailsView.class)
    @GetMapping("/book/{id}")
    public Book getBook(@PathVariable Integer id){
        return bookRepository.getBook(id);
    }

    @PutMapping("/book")//actually create a new book
    @ResponseStatus(HttpStatus.CREATED)
    public Book addBook(@Valid @RequestBody Book book) {
        bookRepository.add(book);
        return book;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> errorHandler(MethodArgumentNotValidException e) {
        Map<String,String> errors = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(fieldError -> {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }); //put any field and error message in errors map...then return that below
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
