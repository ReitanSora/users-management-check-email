package com.reitansora.usersmanagementcheckemail.controller;

import com.reitansora.usersmanagementcheckemail.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Checks if an email is already used.
     *
     * @param email the email to check
     * @return a ResponseEntity containing a map with the email and a boolean indicating if it is used
     */
    @GetMapping(path = "/isEmailUsed")
    public ResponseEntity<?> isEmailUsed(@RequestParam(name = "email", required=false) String email){
        Map<String, Object> response = new HashMap<>();

        if (email == null || email.isBlank()) {
            Map<String,Object> err = Map.of(
                    "error", "Email param is required"
            );
            return ResponseEntity
                    .badRequest()
                    .body(err);
        }

        try {
            boolean isUsed = userService.findByEmailPublic(email);
            response.put("email", email);
            response.put("isUsed", isUsed);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("error", "Error checking if email is used:");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

}
