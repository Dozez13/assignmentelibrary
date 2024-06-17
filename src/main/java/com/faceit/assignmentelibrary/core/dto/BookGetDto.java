package com.faceit.assignmentelibrary.core.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class BookGetDto {

    private Long id;

    private String name;

    private Short publishingYear;

    private String genre;
}
