package com.faceit.assignmentelibrary.domain.data.access.entity;

import com.faceit.assignmentelibrary.domain.data.access.entity.embeddable.PatronBookID;
import com.faceit.assignmentelibrary.domain.data.access.entity.enums.BookStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Data
public class PatronBook {

    @EmbeddedId
    private PatronBookID patronBookID;

    @Column(name = "expires_at")
    private LocalDate expiresAt;

    @Column(name = "borrowed_date")
    private LocalDate borrowedDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "book_status")
    private BookStatus bookStatus;

}
