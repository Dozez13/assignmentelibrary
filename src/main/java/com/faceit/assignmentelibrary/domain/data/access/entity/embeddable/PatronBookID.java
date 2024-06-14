package com.faceit.assignmentelibrary.domain.data.access.entity.embeddable;

import com.faceit.assignmentelibrary.domain.data.access.entity.Book;
import com.faceit.assignmentelibrary.domain.data.access.entity.Patron;
import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@Data
public class PatronBookID {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patron_id")
    private Patron patron;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;
}
