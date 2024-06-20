package com.faceit.assignmentelibrary.core.dto;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class BookDetailsGetDto {

    private Long id;

    private PublisherGetDto publisher;

    private Integer pagesNumber;

    private Short publishingYear;

    private String genre;

}
