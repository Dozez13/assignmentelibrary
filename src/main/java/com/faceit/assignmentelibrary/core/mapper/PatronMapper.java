package com.faceit.assignmentelibrary.core.mapper;

import com.faceit.assignmentelibrary.core.dto.PatronSignupRequestDto;
import com.faceit.assignmentelibrary.domain.data.access.entity.Patron;
import org.mapstruct.AfterMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PatronMapper {
    @Mapping(target = "userRole", constant = "PATRON")
    Patron toPatronFromUserSignupRequestDto(PatronSignupRequestDto patronSignupRequestDto);

    @AfterMapping
    default void map(@MappingTarget Patron target, PatronSignupRequestDto source, @Context PasswordEncoder passwordEncoder) {
        target.setPassword(passwordEncoder.encode(source.getPassword()));
    }

}