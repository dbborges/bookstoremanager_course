package com.danielborges.bookstoremanager.publishers.service;

import com.danielborges.bookstoremanager.authors.dto.AuthorDTO;
import com.danielborges.bookstoremanager.authors.entity.Author;
import com.danielborges.bookstoremanager.authors.exception.AuthorNotFoundException;
import com.danielborges.bookstoremanager.publishers.dto.PublisherDTO;
import com.danielborges.bookstoremanager.publishers.entity.Publisher;
import com.danielborges.bookstoremanager.publishers.exception.PublisherAlreadyExistsException;
import com.danielborges.bookstoremanager.publishers.exception.PublisherNotFoundException;
import com.danielborges.bookstoremanager.publishers.mapper.PublisherMapper;
import com.danielborges.bookstoremanager.publishers.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PublisherService {

    private final static PublisherMapper publisherMapper = PublisherMapper.INSTANCE;

    private PublisherRepository publisherRepository;

    @Autowired
    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    public PublisherDTO create(PublisherDTO publisherDTO){
        verifyIfExists(publisherDTO.getName(), publisherDTO.getCode());
        Publisher publisherToCreate =   publisherMapper.toModel(publisherDTO);
        Publisher createdPublisher = publisherRepository.save(publisherToCreate);
        return publisherMapper.toDTO(createdPublisher);
    }

    private void verifyIfExists(String name, String code) {
        Optional<Publisher> duplicatedPublisher = publisherRepository
                .findByNameOrCode(name, code);
        if(duplicatedPublisher.isPresent()){
            throw new PublisherAlreadyExistsException(name, code);
        }
    }

    public PublisherDTO findById(Long id){
        return publisherRepository.findById(id)
                .map(publisherMapper::toDTO)
                .orElseThrow(() -> new PublisherNotFoundException(id));
    }
}
