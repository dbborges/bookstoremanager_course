package com.danielborges.bookstoremanager.users.service;

import com.danielborges.bookstoremanager.authors.mapper.AuthorMapper;
import com.danielborges.bookstoremanager.authors.repository.AuthorRepository;
import com.danielborges.bookstoremanager.users.dto.MessageDTO;
import com.danielborges.bookstoremanager.users.dto.UserDTO;
import com.danielborges.bookstoremanager.users.entity.User;
import com.danielborges.bookstoremanager.users.exception.UserAlreadyExistsexception;
import com.danielborges.bookstoremanager.users.mapper.UserMapper;
import com.danielborges.bookstoremanager.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class UserService {

    private final static UserMapper userMapper = UserMapper.INSTANCE;

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public MessageDTO create(UserDTO userToCreateDTO) {
        verifyIfExists(userToCreateDTO.getEmail(), userToCreateDTO.getUsername());
        User userToCreate = userMapper.toModel(userToCreateDTO);
        User createdUser = userRepository.save(userToCreate);
        return creationMessage(createdUser);
    }

    private void verifyIfExists(String email, String username) {
        Optional<User> foundUser = userRepository.findByEmailOrUsername(email, username);
        if(foundUser.isPresent()){
            throw new UserAlreadyExistsexception(email, username);
        }
    }

    private MessageDTO creationMessage(User createdUser) {
        String createdUsername = createdUser.getUsername();
        Long createdId = createdUser.getId();
        String createdUserMessage = String.format("User %s with ID %s successfully created", createdUsername, createdId);
        return MessageDTO.builder()
                .message(createdUserMessage)
                .build();
    }
}
