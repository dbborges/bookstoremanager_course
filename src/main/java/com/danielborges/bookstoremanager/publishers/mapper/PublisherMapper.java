package com.danielborges.bookstoremanager.publishers.mapper;

import com.danielborges.bookstoremanager.publishers.dto.PublisherDTO;
import com.danielborges.bookstoremanager.publishers.entity.Publisher;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PublisherMapper {

    PublisherMapper INSTANCE = Mappers.getMapper(PublisherMapper.class);

    Publisher toModel(PublisherDTO publisherDTO);

    PublisherDTO toDTO(Publisher publisher);
}
