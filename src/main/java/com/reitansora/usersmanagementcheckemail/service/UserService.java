package com.reitansora.usersmanagementcheckemail.service;

import com.reitansora.usersmanagementcheckemail.entity.UserEntity;
import com.reitansora.usersmanagementcheckemail.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service class for managing user-related operations.
 */
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    /**
     * Constructs a UserService with the given UserRepository.
     *
     * @param userRepository the user repository
     */
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Checks if a user exists by their email.
     *
     * @param email the email to search for
     * @return true if the user exists, false otherwise
     */
    public boolean findByEmailPublic(String email) {
        return userRepository.existsByEmail(email);
    }

}
