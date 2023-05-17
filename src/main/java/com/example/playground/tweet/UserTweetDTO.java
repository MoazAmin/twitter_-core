package com.example.playground.tweet;

import com.example.playground.user.Role;
import com.example.playground.user.User;
import lombok.Builder;

import java.util.List;
@Builder
public record UserTweetDTO(String username, String firstName, String lastName, List<TweetDTO> tweets) {
    public static UserTweetDTO of (User user, Tweet tweet) {
        List<TweetDTO> tweets = user.getTweets().stream().filter(tweet1 -> !tweet.deleted).map(TweetDTO::of).toList();

        return new UserTweetDTO(user.getUsername(),
        user.getFirstName(),
        user.getLastName(),
                tweets);
    }
}
