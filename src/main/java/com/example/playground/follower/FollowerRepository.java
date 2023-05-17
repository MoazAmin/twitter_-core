package com.example.playground.follower;

import com.example.playground.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.Set;

public interface FollowerRepository extends JpaRepository<Follower, Long> {


    //Get all following
    @Query("select f.following from Follower f where f.user.id = :id")
    Set<User> getFollowingById(Long id);

    //Get all followers
    @Query("select f.user from Follower f where f.following.id = :id")
    Set<User> getFollowerByIdIs(Long id);

    @Query("select f from Follower f where f.user.id = :id_u and f.following.id = :id_f")
    Optional<Follower> isFollowing(Long id_u, Long id_f );
}
