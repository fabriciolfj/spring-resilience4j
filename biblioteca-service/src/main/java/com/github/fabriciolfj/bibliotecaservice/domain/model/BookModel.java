package com.github.fabriciolfj.bibliotecaservice.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookModel {

    private Long id;
    private String name;
}
