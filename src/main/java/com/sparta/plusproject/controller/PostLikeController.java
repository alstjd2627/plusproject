package com.sparta.plusproject.controller;

import com.sparta.plusproject.security.UserDetailsImpl;
import com.sparta.plusproject.service.PostLikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/api/posts/{postId}/likes")
@RequiredArgsConstructor
public class PostLikeController {
    private final PostLikeService postLikeService;

    @PostMapping
    public ResponseEntity<String> likePost(@PathVariable Long postId , @AuthenticationPrincipal UserDetailsImpl userDetails){
        try{
            postLikeService.likePost(postId,userDetails);
            return ResponseEntity.ok("좋아요 성공");
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
