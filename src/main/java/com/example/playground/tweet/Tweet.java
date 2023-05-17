package com.example.playground.tweet;

import com.example.playground.tweet.comment.Comment;
import com.example.playground.user.User;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name = "tweet")
public class Tweet {
    @Id
    @SequenceGenerator(name = "tweetIdGenerator", sequenceName = "seq_tweet_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tweetIdGenerator")
    @Column(name = "ID", nullable = false, unique = true, insertable = false, updatable = false)
    private Long id;

    @Column(name = "tweet_string")
    @NonNull
    private String tweet;

    @Column(name = "created")
    @Builder.Default
    private LocalDateTime created = LocalDateTime.now();

    @Column(name = "deleted")
    @Builder.Default
    boolean deleted = false;

    @Column(name = "likes")
    @Builder.Default
    private Long likes = 0L;

    @Nullable
    @Column(name = "picture")
    private String picture;

    @ManyToOne
    private User user;

    @OneToMany
    private Set<Comment> comments ;

}
