package com.khangwei.dogfunfacts.mappers;

import org.mapstruct.Mapper;

import com.khangwei.dogfunfacts.dtos.DogFactDto;
import com.khangwei.dogfunfacts.entities.DogFact;

@Mapper(componentModel = "spring")
public interface DogFactMapper {
    DogFactDto toDto(DogFact dog_fact);
}