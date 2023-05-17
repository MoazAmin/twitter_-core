package com.example.playground.follower;

import com.example.playground.user.User;
import com.example.playground.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class FollowerServiceImpl implements FollowerService{
    private  final  FollowerRepository followerRepository;
    private final UserRepository userRepository;

    @Override
    public FollowerFollowingDto followerDto(Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        if(userOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with that Id does not exist");

        User user = userOptional.get();
        List<FollowerDTO> followerDTOS = followerRepository.getFollowingById(id).stream().map(FollowerDTO::of).toList();
        List<FollowerDTO> followingDTOS = followerRepository.getFollowerByIdIs(id).stream().map(FollowerDTO::of).toList();

        return FollowerFollowingDto.of(user, followerDTOS, followingDTOS);
    }

    @Override
    public void userGotAFollower(Long user, Long follower) {
        User userFollowed;
        User userFollower;

        Optional<User> optionalUser = userRepository.findById(user);
        Optional<User> optionalFollower = userRepository.findById(follower);

        if (optionalUser.isPresent()) {
            userFollowed = optionalUser.get();
        } else
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with that Id does not exist");

        if (optionalFollower.isPresent()) {
            userFollower = optionalFollower.get();
        } else
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with that Id does not exist");

        Optional<Follower> existsFollower = followerRepository.isFollowing(userFollowed.getId(), userFollower.getId());
        Follower createFollower;

        if (existsFollower.isEmpty()) {
            createFollower = Follower.builder().user(userFollowed).following(userFollower).build();
            followerRepository.save(createFollower);
        } else {
            createFollower = existsFollower.get();
            createFollower.setDeleted(!createFollower.deleted);
        }
        followerRepository.save(createFollower);
    }

    @Override
    public List<FollowerFollowingDto> allUsersAndTheirFollows() {
        List<User> userList = userRepository.findAll();
        User user;
        List<FollowerDTO> followerDTOS ;
        List<FollowerDTO> followingDTOS ;
        List<FollowerFollowingDto> allUsers = new ArrayList<>();

        for (int i = 0; i < userRepository.count(); i++) {
            user = userList.get(i);
            followerDTOS = followerRepository.getFollowingById(user.getId()).stream().map(FollowerDTO::of).toList();
            followingDTOS = followerRepository.getFollowerByIdIs(user.getId()).stream().map(FollowerDTO::of).toList();
            allUsers.add(FollowerFollowingDto.of(user, followerDTOS, followingDTOS));
        }
        return  allUsers;
    }

}
