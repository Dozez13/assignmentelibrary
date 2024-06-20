package com.faceit.assignmentelibrary.domain.data.access.entity;

import com.faceit.assignmentelibrary.domain.data.access.converter.YearAttributeConverter;
import com.faceit.assignmentelibrary.domain.data.access.entity.enums.Genre;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;

@Entity
@NoArgsConstructor
@Data
public class BookDetails {
    @Id
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    @Column(name = "pages_number")
    private Integer pagesNumber;

    @Convert(converter = YearAttributeConverter.class)
    @Column(name = "publishing_year")
    private Year publishingYear;

    @Enumerated(EnumType.STRING)
    @Column(name = "genre")
    private Genre genre;

    @Column(name = "pdf_preview_file_location_path")
    private String pdfPreviewFileLocationPath;
}
