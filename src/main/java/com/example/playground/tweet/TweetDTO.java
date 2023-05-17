package com.example.playground.tweet;

import java.time.LocalDateTime;

public record TweetDTO(String tweet, Long likes, String picture, LocalDateTime created ) {
    public static TweetDTO of (Tweet tweet) {
        return new TweetDTO(
                tweet.getTweet(),
                tweet.getLikes(),
                tweet.getPicture(),
                tweet.getCreated());
    }
}
