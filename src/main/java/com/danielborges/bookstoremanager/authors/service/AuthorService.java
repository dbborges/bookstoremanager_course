package com.danielborges.bookstoremanager.authors.service;

import com.danielborges.bookstoremanager.authors.controller.AuthorControllerDocs;
import com.danielborges.bookstoremanager.authors.mapper.AuthorMapper;
import com.danielborges.bookstoremanager.authors.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    private final static AuthorMapper authorMapper = AuthorMapper.INSTANCE;

    private AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }

}
