package com.example.playground.follower;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(value = "*")
@RequestMapping(path = "api/follow")
@RequiredArgsConstructor
public class FollowerController {

    private final FollowerService followerService;

    @GetMapping(path = "{id}")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    ResponseEntity<Object> getFollowerAndFollowingFromUserId(@PathVariable Long id) {
        try {
//            followerService.followerDto(id);
            return ResponseEntity.accepted().body(followerService.followerDto(id));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(path = "{id}/{id2}")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    ResponseEntity<Object> follow(@PathVariable Long id, @PathVariable Long id2) {
        try {
            followerService.userGotAFollower(id,id2);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    ResponseEntity<Object> getAllUsersAndTheirFollows() {
        try {
            return ResponseEntity.ok(followerService.allUsersAndTheirFollows());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Something went wrong");
        }
    }

}
