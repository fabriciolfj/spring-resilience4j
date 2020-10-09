package com.github.fabriciolfj.bookservice.api.mapper;

import com.github.fabriciolfj.bookservice.api.dto.BookDTO;
import com.github.fabriciolfj.bookservice.domain.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface BookMapper {

    @Mapping(source = "name", target = "name")
    Book toEntity(final BookDTO dto);
}
