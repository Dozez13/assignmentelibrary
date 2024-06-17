package com.faceit.assignmentelibrary.core.service.impl;

import com.faceit.assignmentelibrary.core.dto.BookGetDto;
import com.faceit.assignmentelibrary.core.mapper.BookMapper;
import com.faceit.assignmentelibrary.core.service.PatronService;
import com.faceit.assignmentelibrary.domain.data.access.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatronServiceImpl implements PatronService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public List<BookGetDto> getPatronsBooks(Long patronId) {
        return bookRepository.findBooksByPatronId(patronId)
                .stream()
                .map(bookMapper::toBookGetDto)
                .collect(Collectors.toList());
    }
}
