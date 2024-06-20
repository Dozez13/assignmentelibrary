package com.faceit.assignmentelibrary.core.service;

import com.faceit.assignmentelibrary.core.dto.BookGetDto;
import com.faceit.assignmentelibrary.core.dto.BookPostDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BookService {

    List<BookGetDto> getAllBooks();

    void addBook(BookPostDto bookPostDto, MultipartFile bookPreview);

    void deleteBook(Long bookId);
}
