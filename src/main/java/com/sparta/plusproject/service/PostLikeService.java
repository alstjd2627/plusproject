package com.sparta.plusproject.service;


import com.sparta.plusproject.entity.Post;
import com.sparta.plusproject.entity.PostLike;
import com.sparta.plusproject.entity.User;
import com.sparta.plusproject.repository.PostLikeRepository;
import com.sparta.plusproject.repository.PostRepository;
import com.sparta.plusproject.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostLikeService {
    private final PostLikeRepository postLikeRepository;
    private final PostRepository postRepository;

    public void likePost(Long postId, UserDetailsImpl userDetails) throws Exception {
        User user = userDetails.getUser();
        Post post = postRepository.findById(postId).orElseThrow(() ->
                new Exception("게시글을 찾을 수 없습니다."));
        PostLike postLike = new PostLike(user,post);
        System.out.println(user.getUsername());

        postLikeRepository.save(postLike);
    }
}
