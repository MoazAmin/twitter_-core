package com.example.playground.tweet;

import com.example.playground.user.User;
import com.example.playground.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TweetServiceImpl implements TweetService{
    final UserRepository userRepository;
    final TweetRepository tweetRepository;


    @Override
    public UserTweetDTO getUserAndTheirTweets(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with that Id does not exist");

        List<TweetDTO> tweetDTO = tweetRepository.findAllById(id).stream().map(TweetDTO::of).toList();
        User currentUser = user.get();

        return   UserTweetDTO.builder()
                .username(currentUser.getUsername())
                .firstName(currentUser.getFirstName())
                .lastName(currentUser.getLastName())
                .tweets(tweetDTO).build();
    }

    @Override
    public void createTweet(Long id, FreshTweetDto freshTweetDto) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with that Id does not exist");


        Tweet tweet = Tweet.builder().user(user.get()).tweet(freshTweetDto.tweet()).build();
        tweetRepository.save(tweet);
    }
}
