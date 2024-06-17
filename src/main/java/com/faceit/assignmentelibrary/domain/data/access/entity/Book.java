package com.faceit.assignmentelibrary.domain.data.access.entity;

import com.faceit.assignmentelibrary.domain.data.access.converter.YearAttributeConverter;
import com.faceit.assignmentelibrary.domain.data.access.entity.enums.Genre;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "book", fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    private BookPreview bookPreview;

    @OneToMany(mappedBy = "patronBookID.book", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PatronBook> patronBooks;

    @Column(name = "name")
    private String name;

    @Convert(converter = YearAttributeConverter.class)
    @Column(name = "publishing_year")
    private Year publishingYear;

    @Enumerated(EnumType.STRING)
    @Column(name = "genre")
    private Genre genre;


}
