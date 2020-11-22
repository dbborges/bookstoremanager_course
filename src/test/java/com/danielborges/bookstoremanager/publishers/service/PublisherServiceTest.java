package com.danielborges.bookstoremanager.publishers.service;

import com.danielborges.bookstoremanager.publishers.builder.PublisherDTOBuilder;
import com.danielborges.bookstoremanager.publishers.mapper.PublisherMapper;
import com.danielborges.bookstoremanager.publishers.repository.PublisherRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PublisherServiceTest {

    private final PublisherMapper publisherMapper = PublisherMapper.INSTANCE;

    @Mock
    private PublisherRepository publisherRepository;

    @InjectMocks
    private PublisherService publisherService;

    private PublisherDTOBuilder publisherDTOBuilder;

    @BeforeEach
    void setUp() {
        publisherDTOBuilder = PublisherDTOBuilder.builder().build();
    }

}
