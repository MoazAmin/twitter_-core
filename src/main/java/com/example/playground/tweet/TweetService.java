package com.example.playground.tweet;

public interface TweetService {
    UserTweetDTO getUserAndTheirTweets(Long id);

    void createTweet(Long id, FreshTweetDto freshTweetDto);
}
