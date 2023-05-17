package com.example.playground.follower;

import com.example.playground.user.User;
import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@Entity
@Getter
@RequiredArgsConstructor
@Table(name = "follower")
public class Follower {

    @Id
    @SequenceGenerator(name = "followerIdGenerator", sequenceName = "seq_follower_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "followerIdGenerator")
    @Column(name = "ID", nullable = false, unique = true, insertable = false, updatable = false)
    private Long id;

    public User getUser() {
        return user;
    }

    //    @ManyToMany(mappedBy = "followers" ,fetch = FetchType.LAZY)
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

//    @ManyToMany(mappedBy = "following", fetch = FetchType.LAZY)
    @ManyToOne
    @JoinColumn(name = "follower_id")
    private User following;


    @Column(name = "deleted")
    @Builder.Default
    boolean deleted = false;


}
