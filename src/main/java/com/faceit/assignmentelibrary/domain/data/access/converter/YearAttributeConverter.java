package com.faceit.assignmentelibrary.domain.data.access.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.time.Year;

@Converter
public class YearAttributeConverter implements AttributeConverter<Year, Short> {

    @Override
    public Short convertToDatabaseColumn(Year year) {
        return (short) year.getValue();
    }

    @Override
    public Year convertToEntityAttribute(Short aShort) {
        return Year.of(aShort);
    }
}
