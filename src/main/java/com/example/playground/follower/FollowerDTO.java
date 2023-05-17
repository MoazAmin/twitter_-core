package com.example.playground.follower;

import com.example.playground.user.User;
import lombok.Builder;



@Builder
public record FollowerDTO(String username, String firstName, String lastName) {
    public static FollowerDTO of (User user) {
        return new FollowerDTO(
                user.getUsername(),
                user.getFirstName(),
                user.getLastName()

        );
    }
}

