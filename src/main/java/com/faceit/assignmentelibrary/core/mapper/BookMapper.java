package com.faceit.assignmentelibrary.core.mapper;


import com.faceit.assignmentelibrary.core.dto.BookGetDto;
import com.faceit.assignmentelibrary.core.dto.BookPostDto;
import com.faceit.assignmentelibrary.core.service.StorageService;
import com.faceit.assignmentelibrary.domain.data.access.entity.Book;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.web.multipart.MultipartFile;

import java.time.Year;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = StorageService.class)
public interface BookMapper {

    BookGetDto toBookGetDto(Book book);

    @Mapping(target = "name", source = "bookPostDto.name")
    @Mapping(target = "pdfPreviewFileLocationPath", source = "multipartFile")
    Book fromBookPostDto(BookPostDto bookPostDto, MultipartFile multipartFile);

    default Short getPublishingYearValue(Year value) {
        return (short) value.getValue();
    }

    default Year getPublishingYearFromValue(Short value) {
        return Year.of(value);
    }
}
