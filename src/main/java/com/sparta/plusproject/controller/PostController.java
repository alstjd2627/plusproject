package com.sparta.plusproject.controller;

import com.sparta.plusproject.dto.PostRequestDto;
import com.sparta.plusproject.dto.PostResponseDto;
import com.sparta.plusproject.security.UserDetailsImpl;
import com.sparta.plusproject.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Fetch;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController{

    private final PostService postService;

    @PostMapping
    public ResponseEntity<String> createPost(@AuthenticationPrincipal UserDetailsImpl userDetails,@RequestBody PostRequestDto requestDto){
        try{
            postService.createPost(userDetails,requestDto);
            return ResponseEntity.ok("글 작성 성공");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<PostResponseDto>> getAllPosts(){
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostResponseDto> getPost(@PathVariable Long postId) throws Exception {
        return ResponseEntity.ok(postService.getPost(postId));
    }

    @PatchMapping("/{postId}")
    public ResponseEntity<String> updatePost(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                      @PathVariable Long postId, @RequestBody PostRequestDto postRequestDto) throws Exception {
        try{
            postService.updatePost(userDetails.getUser(), postId, postRequestDto);
            return ResponseEntity.ok("수정 성공");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePost(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                             @PathVariable Long postId) throws Exception {
        return postService.deletePost(userDetails.getUser(), postId);
    }
}
