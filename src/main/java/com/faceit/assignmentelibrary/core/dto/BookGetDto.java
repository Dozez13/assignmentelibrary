package com.faceit.assignmentelibrary.core.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class BookGetDto {

    private Long id;

    private String title;

    private String description;

    private String previewImageBase64;

    private BookDetailsGetDto bookDetails;
}
