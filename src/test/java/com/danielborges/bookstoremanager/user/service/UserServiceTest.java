package com.danielborges.bookstoremanager.user.service;

import com.danielborges.bookstoremanager.publishers.dto.PublisherDTO;
import com.danielborges.bookstoremanager.publishers.entity.Publisher;
import com.danielborges.bookstoremanager.user.builder.UserDTOBuilder;
import com.danielborges.bookstoremanager.users.dto.MessageDTO;
import com.danielborges.bookstoremanager.users.dto.UserDTO;
import com.danielborges.bookstoremanager.users.entity.User;
import com.danielborges.bookstoremanager.users.exception.UserAlreadyExistsexception;
import com.danielborges.bookstoremanager.users.exception.UserNotFoundException;
import com.danielborges.bookstoremanager.users.mapper.UserMapper;
import com.danielborges.bookstoremanager.users.repository.UserRepository;
import com.danielborges.bookstoremanager.users.service.UserService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    private final UserMapper userMapper = UserMapper.INSTANCE;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private UserDTOBuilder userDTOBuilder;

    @BeforeEach
    void setUp() {
        userDTOBuilder = UserDTOBuilder.builder().build();
    }

    @Test
    void whenNewUserIsInformedThenItShouldBeCreated(){
        UserDTO expectedUserToCreateDTO = userDTOBuilder.buildUserDTO();
        User expectedCreatedUser = userMapper.toModel(expectedUserToCreateDTO);
        var expectedCreationMessage = "User danielborges with ID 1 successfully created";
        var email = expectedUserToCreateDTO.getEmail();
        var username = expectedUserToCreateDTO.getUsername();
        when(userRepository.save(expectedCreatedUser)).thenReturn(expectedCreatedUser);
        when(userRepository.findByEmailOrUsername(email, username)).thenReturn(Optional.empty());

        MessageDTO creationMessage = userService.create(expectedUserToCreateDTO);

       assertThat(expectedCreationMessage, Matchers.is(Matchers.equalTo(creationMessage.getMessage())));

    }

    @Test
    void whenExistsUserIsInformedThenAnExceptionShouldBeThrown(){
        UserDTO expectedUserToCreateDTO = userDTOBuilder.buildUserDTO();
        User expectedCreatedUser = userMapper.toModel(expectedUserToCreateDTO);
        var email = expectedUserToCreateDTO.getEmail();
        var username = expectedUserToCreateDTO.getUsername();

        when(userRepository.findByEmailOrUsername(email, username)).thenReturn(Optional.of(expectedCreatedUser));

        Assertions.assertThrows(UserAlreadyExistsexception.class, () -> userService.create(expectedUserToCreateDTO));
    }

    @Test
    void whenValidPublisherIdIsInformedThenItShouldBeDeleted() {
        UserDTO expectedDeletedUserDTO = userDTOBuilder.buildUserDTO();
        User expectedDeletedUser = userMapper.toModel(expectedDeletedUserDTO);
        var expectedDeletedUserId = expectedDeletedUserDTO.getId();

        when(userRepository.findById(expectedDeletedUserId)).thenReturn(Optional.of(expectedDeletedUser));
        doNothing().when(userRepository).deleteById(expectedDeletedUserId);

        userService.delete(expectedDeletedUserId);

        verify(userRepository, times(1)).deleteById(expectedDeletedUserId);
    }

    @Test
    void whenInvalidUserIdIsInformedThenAnExceptionShouldBeThrown() {
        UserDTO expectedDeletedUserDTO = userDTOBuilder.buildUserDTO();
        var expectedDeletedUserId = expectedDeletedUserDTO.getId();

        when(userRepository.findById(expectedDeletedUserId)).thenReturn(Optional.empty());

        Assertions.assertThrows(UserNotFoundException.class, () -> userService.delete(expectedDeletedUserId));
    }

}
