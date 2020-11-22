package com.danielborges.bookstoremanager.users.service;

import com.danielborges.bookstoremanager.authors.mapper.AuthorMapper;
import com.danielborges.bookstoremanager.authors.repository.AuthorRepository;
import com.danielborges.bookstoremanager.users.mapper.UserMapper;
import com.danielborges.bookstoremanager.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final static UserMapper userMapper = UserMapper.INSTANCE;

    private UserRepository authorRepository;

    @Autowired
    public UserService(UserRepository authorRepository) {
        this.authorRepository = authorRepository;
    }
}
