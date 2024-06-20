package com.faceit.assignmentelibrary.web.controller;

import com.faceit.assignmentelibrary.core.dto.BookPostDto;
import com.faceit.assignmentelibrary.core.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/secure/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;


    @GetMapping
    @PreAuthorize("authentication.principal.role == 'PATRON' or authentication.principal.role == 'ADMIN'")
    public ResponseEntity<?> getAllBooks() {
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("authentication.principal.role == 'PATRON' or authentication.principal.role == 'ADMIN'")
    public ResponseEntity<?> getBookById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(bookService.getBookById(id), HttpStatus.OK);
    }
//    @PatchMapping("/{id}")
//    @PreAuthorize("authentication.principal.role == 'ADMIN'")
//    public ResponseEntity<?> patchBookById(@PathVariable("id") Long id) {
//        return new ResponseEntity<>(bookService.getBookById(id), HttpStatus.OK);
//    }

    @PostMapping
    @PreAuthorize("authentication.principal.role == 'ADMIN'")
    public ResponseEntity<?> addBook(
            @RequestPart("bookPostDto") BookPostDto bookPostDto,
            @RequestPart("bookPreview") MultipartFile bookPreview) {

        bookService.addBook(bookPostDto, bookPreview);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("authentication.principal.role == 'ADMIN'")
    public ResponseEntity<?> deleteBook(@PathVariable("id") Long id) {

        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
