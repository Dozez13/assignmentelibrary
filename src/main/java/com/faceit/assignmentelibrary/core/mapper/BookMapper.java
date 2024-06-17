package com.faceit.assignmentelibrary.core.mapper;


import com.faceit.assignmentelibrary.core.dto.BookGetDto;
import com.faceit.assignmentelibrary.domain.data.access.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.time.Year;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookMapper {

    BookGetDto toBookGetDto(Book book);

    default Short getPublishingYearValue(Year value) {
        return (short) value.getValue();
    }
}
