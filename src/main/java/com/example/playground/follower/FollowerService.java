package com.example.playground.follower;

import com.example.playground.user.User;

import java.util.List;

public interface FollowerService {
    FollowerFollowingDto followerDto(Long id);
    void userGotAFollower(Long user, Long follower);
    List<FollowerFollowingDto> allUsersAndTheirFollows();


}
