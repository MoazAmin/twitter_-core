package com.example.playground.tweet;

public record FreshTweetDto(String tweet) {
    public static FreshTweetDto of (Tweet tweet) {
        return new FreshTweetDto(tweet.getTweet());
    }
}
