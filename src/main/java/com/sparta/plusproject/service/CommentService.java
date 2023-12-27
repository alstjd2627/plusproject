package com.sparta.plusproject.service;

import com.sparta.plusproject.dto.CommentRequestDto;
import com.sparta.plusproject.dto.PostResponseDto;
import com.sparta.plusproject.entity.Comment;
import com.sparta.plusproject.entity.Post;
import com.sparta.plusproject.entity.User;
import com.sparta.plusproject.repository.CommentRepository;
import com.sparta.plusproject.repository.PostRepository;
import com.sparta.plusproject.security.UserDetailsImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public void createComment(Long postId, UserDetailsImpl userDetails, CommentRequestDto requestDto) throws Exception {
        User user = userDetails.getUser();
        Post post = postRepository.findById(postId).orElseThrow(() ->
                new Exception("게시글이 존재하지 않습니다."));
        Comment comment = new Comment(user,post,requestDto.getContent());
        commentRepository.save(comment);
    }


}
