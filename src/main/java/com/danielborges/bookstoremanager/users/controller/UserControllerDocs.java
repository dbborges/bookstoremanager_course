package com.danielborges.bookstoremanager.users.controller;

import com.danielborges.bookstoremanager.users.dto.MessageDTO;
import com.danielborges.bookstoremanager.users.dto.UserDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api("System user management")
public interface UserControllerDocs {

    @ApiOperation(value = "User creation operation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success user creation"),
            @ApiResponse(code = 400, message = "Missing required field, or an error on validation field rules")
    })
    MessageDTO create(UserDTO userToCreateDTO);
}
