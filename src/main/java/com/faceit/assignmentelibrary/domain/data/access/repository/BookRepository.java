package com.faceit.assignmentelibrary.domain.data.access.repository;

import com.faceit.assignmentelibrary.domain.data.access.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}