package com.sparta.plusproject.service;

import com.sparta.plusproject.dto.PostRequestDto;
import com.sparta.plusproject.dto.PostResponseDto;
import com.sparta.plusproject.entity.Post;
import com.sparta.plusproject.entity.User;
import com.sparta.plusproject.repository.PostLikeRepository;
import com.sparta.plusproject.repository.PostRepository;
import com.sparta.plusproject.security.UserDetailsImpl;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.EmptyStackException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final PostLikeRepository postLikeRepository;

    public void createPost(@AuthenticationPrincipal UserDetailsImpl userDetails,PostRequestDto requestDto) throws Exception {
        String title = requestDto.getTitle();
        String content = requestDto.getContent();
        User user = userDetails.getUser();

        System.out.println(title.length());
        System.out.println(content);
        Post post = new Post(user,title,content);
        postRepository.save(post);
    }

    public List<PostResponseDto> getAllPosts(){
        return postRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(PostResponseDto::new)
                .collect(Collectors.toList());
    }

    public PostResponseDto getPost(Long postId) throws Exception {
        Post post = postRepository.findById(postId).orElseThrow( () ->
                new Exception("게시글이 존재하지 않습니다."));
        PostResponseDto postResponseDto = new PostResponseDto(post);
        return postResponseDto;
    }

    @Transactional
    public PostResponseDto updatePost(User user, Long id, PostRequestDto postRequestDto) throws Exception {
        Post post = postRepository.findById(id).orElseThrow( () ->
                new Exception("존재하지 않는 글입니다."));

        if(!user.getId().equals(post.getUser().getId())){
            throw new Exception("글 작성자가 아닙니다.");
        }
        post.update(postRequestDto);
        return new PostResponseDto(post);
    }

    public ResponseEntity<String> deletePost(User user, Long id) throws Exception {
        Post post = postRepository.findById(id).orElseThrow( () ->
                new Exception("존재하지 않는 글입니다."));

        if(!user.getId().equals(post.getUser().getId())){
            throw new Exception("글 작성자가 아닙니다.");
        }
        postRepository.delete(post);
        return ResponseEntity.ok("삭제 성공");
    }
}
