package com.example.playground.tweet;

import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/tweet")
public class TweetController {

    private final TweetService tweetService;

    @GetMapping("{id}")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public UserTweetDTO getUserAndTweets(@PathVariable Long id){
            return tweetService.getUserAndTheirTweets(id);
    }

    @PostMapping (path = "{id}/create")
    public ResponseEntity<Object> createFreshTweet(@PathVariable Long id, @RequestBody FreshTweetDto tweetDto) {
        try {
            tweetService.createTweet(id, tweetDto);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Could not create Tweet");
        }
    }
}
