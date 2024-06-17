package com.faceit.assignmentelibrary.core.service;

import com.faceit.assignmentelibrary.core.dto.BookGetDto;

import java.util.List;

public interface PatronService {

    List<BookGetDto> getPatronsBooks(Long patronId);
}
