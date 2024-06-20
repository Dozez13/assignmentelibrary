package com.faceit.assignmentelibrary.core.mapper;


import com.faceit.assignmentelibrary.core.dto.BookGetDto;
import com.faceit.assignmentelibrary.core.dto.BookPostDto;
import com.faceit.assignmentelibrary.core.service.ImageService;
import com.faceit.assignmentelibrary.core.service.StorageService;
import com.faceit.assignmentelibrary.domain.data.access.entity.Book;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {StorageService.class, BookDetailsMapper.class})
public abstract class BookMapper {
    protected ImageService imageService;

    @Autowired
    public void setPasswordEncoder(ImageService imageService) {
        this.imageService = imageService;
    }

    @Mapping(target = "previewImageBase64", expression = "java(imageService.base64ImageFromFilePath(book.getPreviewImageLocationPath()))")
    public abstract BookGetDto toBookGetDto(Book book);

    @Mapping(target = "previewImageLocationPath", source = "multipartFile")
    public abstract Book fromBookPostDto(BookPostDto bookPostDto, MultipartFile multipartFile);

}
