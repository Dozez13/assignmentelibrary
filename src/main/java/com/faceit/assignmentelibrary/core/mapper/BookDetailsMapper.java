package com.faceit.assignmentelibrary.core.mapper;

import com.faceit.assignmentelibrary.core.dto.BookDetailsGetDto;
import com.faceit.assignmentelibrary.core.dto.BookDetailsPostDto;
import com.faceit.assignmentelibrary.core.dto.BookPostDto;
import com.faceit.assignmentelibrary.domain.data.access.entity.BookDetails;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.time.Year;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = PublisherMapper.class)
public interface BookDetailsMapper {

    BookDetailsGetDto toBookDetailsGetDto(BookDetails bookDetails);
    BookDetails fromBookDetailsPostDto(BookDetailsPostDto bookDetailsPostDto);

    default Short getPublishingYearValue(Year value) {
        return (short) value.getValue();
    }

    default Year getPublishingYearFromValue(Short value) {
        return Year.of(value);
    }
}
