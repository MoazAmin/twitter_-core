package com.example.playground.user;

import com.example.playground.follower.Follower;
import com.example.playground.tweet.Tweet;
import com.example.playground.tweet.comment.Comment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "um_user")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @SequenceGenerator(name = "userIdGenerator", sequenceName = "seq_um_user_id", initialValue = 10000, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userIdGenerator")
    @Column(name = "ID", nullable = false, unique = true, insertable = false, updatable = false)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "profile_image")
    @Builder.Default
    private String profile_image = "/Users/moazamin/Desktop/twitterImages/DEFAULT.png";

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<UserRole> userRoles;

    @OneToMany(mappedBy = "user")
    private Set<Follower> followers ;

    @OneToMany(mappedBy = "following")
    private Set<Follower> following;

    @OneToMany
    private List<Tweet> tweets;

    @OneToMany
    private Set<Comment> comments;

    public SecurityUser toSecurityUser() {
        List<SimpleGrantedAuthority> simpleGrantedAuthorities =
            userRoles.stream().map(ur -> new SimpleGrantedAuthority(ur.getRole().name())).toList();
        return SecurityUser
            .builder()
            .withUserName(username)
            .withPassword(password)
            .withGrantedAuthorityList(simpleGrantedAuthorities);
    }

}
