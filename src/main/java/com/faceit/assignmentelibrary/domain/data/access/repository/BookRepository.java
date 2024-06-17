package com.faceit.assignmentelibrary.domain.data.access.repository;

import com.faceit.assignmentelibrary.domain.data.access.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT b FROM Book b"
            + " LEFT JOIN FETCH b.patronBooks pb"
            + " LEFT JOIN FETCH pb.patronBookID.patron p"
            + " WHERE p.id = :patronId")
    List<Book> findBooksByPatronId(@Param("patronId") Long patronId);
}