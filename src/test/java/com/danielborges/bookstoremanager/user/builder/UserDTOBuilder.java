package com.danielborges.bookstoremanager.user.builder;

import com.danielborges.bookstoremanager.users.dto.UserDTO;
import com.danielborges.bookstoremanager.users.enums.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Builder
public class UserDTOBuilder {

    @Builder.Default
    private Long id = 1L;

    @Builder.Default
    private String name = "Daniel Borges";

    @Builder.Default
    private int age = 30;

    @Builder.Default
    private Gender gender = Gender.MALE;

    @Builder.Default
    private String email = "dbborges.rj@gmail.com";

    @Builder.Default
    private String username = "danielborges";

    @Builder.Default
    private String senha = "123456";

    @Builder.Default
    private LocalDate birthDate = LocalDate.of(1990, 8, 12);

    public UserDTO buildUserDTO(){
        return new UserDTO(id, name, age, gender, email, username, senha, birthDate);
    }
}
