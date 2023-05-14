package com.example.playground.user.create;

import com.example.playground.user.Role;
import com.example.playground.user.User;
import com.example.playground.user.UserRole;
import lombok.Builder;

import java.util.List;

@Builder
public record CreateUserRecord(String username, String password, String email, String firstName, String lastName,
                               String profileImage, List<Role> roles) {

    public static CreateUserRecord of(User user) {
        return new CreateUserRecord(
            user.getUsername(),
            user.getPassword(),
            user.getEmail(),
            user.getFirstName(),
            user.getLastName(),
            user.getProfile_image(),
            user.getUserRoles()
                    .stream().map(UserRole::getRole).toList());
    }
}
