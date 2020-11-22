package com.danielborges.bookstoremanager.publishers.controller;

import com.danielborges.bookstoremanager.author.builder.AuthorDTOBuilder;
import com.danielborges.bookstoremanager.authors.dto.AuthorDTO;
import com.danielborges.bookstoremanager.publishers.builder.PublisherDTOBuilder;
import com.danielborges.bookstoremanager.publishers.dto.PublisherDTO;
import com.danielborges.bookstoremanager.publishers.service.PublisherService;
import com.danielborges.bookstoremanager.utils.JsonConversionUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Collections;

import static com.danielborges.bookstoremanager.utils.JsonConversionUtils.*;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class PublisherControllerTest {

    private final static String PUBLISHERS_API_URL_PATH = "/api/v1/publishers";

    private MockMvc mockMvc;

    @Mock
    private PublisherService publisherService;

    @InjectMocks
    private PublisherController publisherController;

    private PublisherDTOBuilder publisherDTOBuilder;

    @BeforeEach
    void setUp() {
        publisherDTOBuilder = PublisherDTOBuilder.builder().build();
        mockMvc = MockMvcBuilders.standaloneSetup(publisherController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    void whenPOSTIsCalledThenStatusCreatedShouldBeReturned() throws Exception {
        PublisherDTO expectedCreatedPublisherDTO = publisherDTOBuilder.buildPublisherDTO();

        when(publisherService.create(expectedCreatedPublisherDTO))
                .thenReturn(expectedCreatedPublisherDTO);

        mockMvc.perform(post(PUBLISHERS_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(expectedCreatedPublisherDTO)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(expectedCreatedPublisherDTO.getId().intValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", is(expectedCreatedPublisherDTO.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", is(expectedCreatedPublisherDTO.getCode())));
    }

    @Test
    void whenPOSTIsCalledWithoutRequiredFieldThenBadRequestStatusShouldBeInformed() throws Exception {
        PublisherDTO expectedCreatedPublisherDTO = publisherDTOBuilder.buildPublisherDTO();
        expectedCreatedPublisherDTO.setName(null);

        mockMvc.perform(post(PUBLISHERS_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(expectedCreatedPublisherDTO)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void whenGETWithValidIdIsCalledThenStatusOKShouldBeReturned() throws Exception {
        PublisherDTO expectedFoundPublisherDTO = publisherDTOBuilder.buildPublisherDTO();
        var expectedCreatedPublisherDTOId = expectedFoundPublisherDTO.getId();

        when(publisherService.findById(expectedCreatedPublisherDTOId))
                .thenReturn(expectedFoundPublisherDTO);

        mockMvc.perform(get(PUBLISHERS_API_URL_PATH + "/" + expectedCreatedPublisherDTOId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(expectedFoundPublisherDTO.getId().intValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", is(expectedFoundPublisherDTO.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", is(expectedFoundPublisherDTO.getCode())));
    }

    @Test
    void whenGETListIsCalledThenStatusOKShouldBeReturned() throws Exception {
        PublisherDTO expectedFoundPublisherDTO = publisherDTOBuilder.buildPublisherDTO();

        when(publisherService.findAll()).thenReturn(Collections.singletonList(expectedFoundPublisherDTO));

        mockMvc.perform(get(PUBLISHERS_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", is(expectedFoundPublisherDTO.getId().intValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", is(expectedFoundPublisherDTO.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].code", is(expectedFoundPublisherDTO.getCode())));
    }

    @Test
    void whenDELETEWithValidIdIsCalledThenNoContentShouldBeReturned() throws Exception {
        PublisherDTO expectedPublisherDeletedDTO = publisherDTOBuilder.buildPublisherDTO();

        var expectedPublisherDeletedId = expectedPublisherDeletedDTO.getId();
        doNothing().when(publisherService).delete(expectedPublisherDeletedId);

        mockMvc.perform(delete(PUBLISHERS_API_URL_PATH + "/" + expectedPublisherDeletedId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
