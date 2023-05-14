package com.example.playground.follower;

import com.example.playground.user.User;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@Entity
@RequiredArgsConstructor
@Table(name = "follower")
public class Follower {

    @Id
    @SequenceGenerator(name = "tweetIdGenerator", sequenceName = "seq_tweet_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tweetIdGenerator")
    @Column(name = "ID", nullable = false, unique = true, insertable = false, updatable = false)
    private Long id;


    @ManyToMany
    private List<User> users;

    @ManyToMany
    private List<User> followee;


    @Column(name = "isDeleted")
    @Builder.Default
    boolean isDeleted = false;


}
