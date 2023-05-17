package com.example.playground.tweet.comment;

import com.example.playground.tweet.Tweet;
import com.example.playground.user.User;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "comment")
public class Comment {

    @Id
    @SequenceGenerator(name = "commentIdGenerator", sequenceName = "seq_comment_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tweetIdGenerator")
    @Column(name = "ID", nullable = false, unique = true, insertable = false, updatable = false)
    private Long id;

    @ManyToOne
    private Tweet tweet;


}
