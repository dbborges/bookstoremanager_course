package com.danielborges.bookstoremanager.users.repository;

import com.danielborges.bookstoremanager.users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
