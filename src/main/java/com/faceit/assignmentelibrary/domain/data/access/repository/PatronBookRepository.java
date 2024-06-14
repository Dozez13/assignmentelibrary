package com.faceit.assignmentelibrary.domain.data.access.repository;

import com.faceit.assignmentelibrary.domain.data.access.entity.PatronBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatronBookRepository extends JpaRepository<PatronBook, Long> {
}