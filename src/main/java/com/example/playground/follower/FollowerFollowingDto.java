package com.example.playground.follower;

import com.example.playground.user.User;

import java.util.List;

public record FollowerFollowingDto(String username, String firstName, String lastName
        , List<FollowerDTO> followers, List<FollowerDTO> following, Integer followersCount, Integer followingCount) {
    public static FollowerFollowingDto of (User user,  List<FollowerDTO> list1, List<FollowerDTO> ls2) {
        List<FollowerDTO> followerDTOList = user.getFollowing().stream().filter(follower -> !follower.deleted).map(Follower::getUser ).map(FollowerDTO::of).toList();
        List<FollowerDTO> followingDTOList =  user.getFollowers().stream().filter(follower -> !follower.deleted).map(Follower::getFollowing).map(FollowerDTO::of).toList();
        return new FollowerFollowingDto(
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                followerDTOList,
                followingDTOList,
                followerDTOList.size(),
                followingDTOList.size()
        );
    }
}
