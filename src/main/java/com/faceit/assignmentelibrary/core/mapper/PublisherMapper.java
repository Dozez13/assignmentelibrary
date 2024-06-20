package com.faceit.assignmentelibrary.core.mapper;

import com.faceit.assignmentelibrary.core.dto.PublisherGetDto;
import com.faceit.assignmentelibrary.core.dto.PublisherPostDto;
import com.faceit.assignmentelibrary.domain.data.access.entity.Publisher;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PublisherMapper {

    PublisherGetDto toPublisherGetDto(Publisher publisher);

    Publisher fromPublisherPostDto(PublisherPostDto publisherPostDto);
}
