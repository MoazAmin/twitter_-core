package com.example.playground.tweet.comment;

import com.example.playground.tweet.FreshTweetDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/tweet/comment")
public class CommentController {
    private final CommentService commentService;


    @PostMapping(path = "{tweetId}/{userId}")
    public ResponseEntity<Object> createComment(@PathVariable Long tweetId,
                                                @PathVariable Long userId,
                                                @RequestBody FreshTweetDto freshTweetDto) {
        System.out.println("here");
        try {

            commentService.CreateCommentForTweet(tweetId, userId, freshTweetDto);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.accepted().build();
    }
}
