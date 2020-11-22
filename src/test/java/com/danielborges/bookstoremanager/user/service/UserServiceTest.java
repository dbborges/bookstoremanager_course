package com.danielborges.bookstoremanager.user.service;

import com.danielborges.bookstoremanager.publishers.builder.PublisherDTOBuilder;
import com.danielborges.bookstoremanager.publishers.mapper.PublisherMapper;
import com.danielborges.bookstoremanager.publishers.repository.PublisherRepository;
import com.danielborges.bookstoremanager.publishers.service.PublisherService;
import com.danielborges.bookstoremanager.user.builder.UserDTOBuilder;
import com.danielborges.bookstoremanager.users.mapper.UserMapper;
import com.danielborges.bookstoremanager.users.repository.UserRepository;
import com.danielborges.bookstoremanager.users.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
}
