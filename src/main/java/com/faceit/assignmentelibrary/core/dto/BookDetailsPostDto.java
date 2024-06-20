package com.faceit.assignmentelibrary.core.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class BookDetailsPostDto {


    private PublisherPostDto publisher;

    private Integer pagesNumber;

    private Short publishingYear;

    private String genre;

}