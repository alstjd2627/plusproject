package com.sparta.plusproject.controller;


import com.sparta.plusproject.dto.CommentRequestDto;
import com.sparta.plusproject.security.UserDetailsImpl;
import com.sparta.plusproject.service.CommentService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/posts/{postId}/comments")
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<String> createComment(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails,
                                                @RequestBody CommentRequestDto requestDto){
        try{
            commentService.createComment(postId, userDetails,requestDto);
            return ResponseEntity.ok("댓글 작성 성공");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
