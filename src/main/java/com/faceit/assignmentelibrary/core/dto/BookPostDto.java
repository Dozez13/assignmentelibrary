package com.faceit.assignmentelibrary.core.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class BookPostDto {


    private String title;

    private String description;

    private BookDetailsPostDto bookDetails;
}
