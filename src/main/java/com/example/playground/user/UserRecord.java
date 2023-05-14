package com.example.playground.user;

import lombok.Builder;

import java.util.List;
@Builder
public record UserRecord(String username, String email, String firstName, String lastName, List<Role> roles) {

    public static UserRecord of(User user) {
        return new UserRecord(
            user.getUsername(),
            user.getEmail(),
            user.getFirstName(),
            user.getLastName(),
            user.getUserRoles()
                    .stream().map(UserRole::getRole).toList());
    }
}
