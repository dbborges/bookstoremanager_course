package com.danielborges.bookstoremanager.authors.controller;

import com.danielborges.bookstoremanager.authors.dto.AuthorDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Api("Authors Management")
public interface AuthorControllerDocs {

    @ApiOperation(value = "Author creation operation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success author creation"),
            @ApiResponse(code = 400, message = "Missing required fields, wrong field range value or author already registered on system")
    })
    AuthorDTO create(AuthorDTO authorDTO);

        @ApiOperation(value = "Find Author by id operation")
    @ApiResponses(value = {
            @ApiResponse(code = 0, message = "Success author found"),
            @ApiResponse(code = 404, message = "Author not found error code")
    })
    AuthorDTO findById(@PathVariable Long id);
}
