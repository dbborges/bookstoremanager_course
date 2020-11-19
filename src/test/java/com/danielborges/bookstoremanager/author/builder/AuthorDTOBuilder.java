package com.danielborges.bookstoremanager.author.builder;

import com.danielborges.bookstoremanager.authors.dto.AuthorDTO;
import lombok.Builder;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Builder
public class AuthorDTOBuilder {

    @Builder.Default
    private final Long id = 1L;

    @Builder.Default
    private final String name = "Daniel Borges";

    @Builder.Default
    private int age = 30;

    public AuthorDTO buildAuthorDTO () {
        return new AuthorDTO(id, name, age);
    }
}
