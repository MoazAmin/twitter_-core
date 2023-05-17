package com.example.playground.tweet.comment;

import com.example.playground.tweet.FreshTweetDto;
import com.example.playground.tweet.Tweet;
import com.example.playground.tweet.TweetRepository;
import com.example.playground.user.User;
import com.example.playground.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService{
    private final TweetRepository tweetRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @Override
    public void CreateCommentForTweet(Long tweetId, Long userReplyId, FreshTweetDto freshTweetDto) {
        Optional<Tweet> currentTweet = tweetRepository.findById(tweetId);
        if (currentTweet.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not find tweet");

        Optional<User> currentReplier = userRepository.findById(userReplyId);
        if (currentReplier.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not find replier");

        Tweet tweet = Tweet.builder().user(currentReplier.get()).tweet(freshTweetDto.tweet()).build();
        Comment comment = Comment.builder().tweet(currentTweet.get()).build();

        tweetRepository.save(tweet);
        commentRepository.save(comment);
    }
}
