package com.example.playground.tweet.comment;

import com.example.playground.tweet.FreshTweetDto;

public interface CommentService {
    void CreateCommentForTweet(Long tweetId, Long userReplyId, FreshTweetDto freshTweetDto);
}
