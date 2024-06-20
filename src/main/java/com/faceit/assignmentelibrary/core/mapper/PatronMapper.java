package com.faceit.assignmentelibrary.core.mapper;

import com.faceit.assignmentelibrary.core.dto.PatronSignupRequestDto;
import com.faceit.assignmentelibrary.domain.data.access.entity.Patron;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class PatronMapper {
    protected PasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Mapping(target = "userRole", constant = "PATRON")
    @Mapping(target = "password", expression = "java(passwordEncoder.encode(patronSignupRequestDto.getPassword()))")
    public abstract Patron toPatronFromUserSignupRequestDto(PatronSignupRequestDto patronSignupRequestDto);

}