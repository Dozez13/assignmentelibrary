package com.faceit.assignmentelibrary.core.service.impl;

import com.faceit.assignmentelibrary.core.dto.BookGetDto;
import com.faceit.assignmentelibrary.core.dto.BookPostDto;
import com.faceit.assignmentelibrary.core.exception.BookNotFoundException;
import com.faceit.assignmentelibrary.core.mapper.BookMapper;
import com.faceit.assignmentelibrary.core.service.BookService;
import com.faceit.assignmentelibrary.core.service.MessageService;
import com.faceit.assignmentelibrary.domain.data.access.entity.Book;
import com.faceit.assignmentelibrary.domain.data.access.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final MessageService messageService;

    @Override
    public List<BookGetDto> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(bookMapper::toBookGetDto)
                .collect(Collectors.toList());
    }

    @Override
    public BookGetDto getBookById(Long id) {
        return bookRepository.findById(id)
                .map(bookMapper::toBookGetDto)
                .orElseThrow(() -> new BookNotFoundException(messageService.getMessage("book.with.id.not.found", id)));
    }

    @Transactional
    @Override
    public void addBook(BookPostDto bookPostDto, MultipartFile bookPreview) {
        Book bookToAdd = bookMapper.fromBookPostDto(bookPostDto, bookPreview);
        bookRepository.save(bookToAdd);
    }

    @Transactional
    @Override
    public void deleteBook(Long bookId) {
        if (!bookRepository.existsById(bookId)) {
            throw new BookNotFoundException(messageService.getMessage("book.with.id.not.found", bookId));
        }
        bookRepository.deleteById(bookId);
    }

}
