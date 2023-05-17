package com.example.playground.tweet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TweetRepository extends JpaRepository<Tweet, Long> {
    @Query("select t from Tweet t where  t.user.id = :#{#id_} and t.deleted = false ")
    List<Tweet> findAllById(@Param("id_") Long id);
}