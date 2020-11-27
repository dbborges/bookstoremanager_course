package com.danielborges.bookstoremanager.users.controller;

import com.danielborges.bookstoremanager.users.dto.MessageDTO;
import com.danielborges.bookstoremanager.users.dto.UserDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Api("System user management")
public interface UserControllerDocs {

    @ApiOperation(value = "User creation operation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success user creation"),
            @ApiResponse(code = 400, message = "Missing required field, or an error on validation field rules")
    })
    MessageDTO create(UserDTO userToCreateDTO);

    @ApiOperation(value = "User exclusion operation")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Success user exclusion"),
            @ApiResponse(code = 404, message = "User with informed id not found inthe system")
    })
    void delete(Long id);

    @ApiOperation(value = "User update operation")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success user updated"),
            @ApiResponse(code = 404, message = "User with informed id not found inthe system")
    })
    MessageDTO update(@PathVariable Long id, @RequestBody @Valid UserDTO userToUpdateDTO);
}
