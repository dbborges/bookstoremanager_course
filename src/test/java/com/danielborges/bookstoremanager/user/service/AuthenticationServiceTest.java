package com.danielborges.bookstoremanager.user.service;

import com.danielborges.bookstoremanager.user.builder.UserDTOBuilder;
import com.danielborges.bookstoremanager.users.dto.UserDTO;
import com.danielborges.bookstoremanager.users.entity.User;
import com.danielborges.bookstoremanager.users.mapper.UserMapper;
import com.danielborges.bookstoremanager.users.repository.UserRepository;
import com.danielborges.bookstoremanager.users.service.AuthenticationService;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthenticationServiceTest {

    private final UserMapper userMapper = UserMapper.INSTANCE;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AuthenticationService authenticationService;

        private UserDTOBuilder userDTOBuilder;

    @BeforeEach
    void setUp() {
        userDTOBuilder = UserDTOBuilder.builder().build();;
    }

    @Test
    void whenUsernameIsInformedThenUsershouldBeReturned() {
        UserDTO expectedFoundUserDTO = userDTOBuilder.buildUserDTO();
        User expectedFoundUser = userMapper.toModel(expectedFoundUserDTO);
        SimpleGrantedAuthority expectedUserRole  = new SimpleGrantedAuthority("ROLE_" + expectedFoundUserDTO.getRole().getDescription());

        when(userRepository.findByUsername(expectedFoundUserDTO.getUsername())).thenReturn(Optional.of(expectedFoundUser));

        UserDetails userDetails = authenticationService.loadUserByUsername(expectedFoundUserDTO.getUsername());

        assertThat(userDetails.getUsername(), is(expectedFoundUser.getUsername()));
        assertThat(userDetails.getPassword(), is(expectedFoundUser.getPassword()));
        assertTrue(userDetails.getAuthorities().contains(expectedUserRole));
    }

    @Test
    void whenInvalidUsernameIsInformedThenAnExceptionShouldBeThrown() {
        UserDTO expectedFoundUserDTO = userDTOBuilder.buildUserDTO();
        String expectedUsername = expectedFoundUserDTO.getUsername();

        when(userRepository.findByUsername(expectedFoundUserDTO.getUsername())).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> authenticationService.loadUserByUsername(expectedUsername));
    }
}
