package com.danielborges.bookstoremanager.users.utils;

import com.danielborges.bookstoremanager.users.dto.MessageDTO;
import com.danielborges.bookstoremanager.users.entity.User;

public class MessageDTOUtils {
    public static MessageDTO creationMessage(User createdUser) {
        return returnMessage(createdUser, "created");
    }

    public static MessageDTO updateMessage(User updatedUser) {
        return returnMessage(updatedUser, "updated");
    }

    public static MessageDTO returnMessage(User user, String action) {
        String createdUsername = user.getUsername();
        Long createdId = user.getId();
        String userMessage = String.format("User %s with ID %s successfully %s", createdUsername, createdId, action);
        return MessageDTO.builder()
                .message(userMessage)
                .build();
    }
}
