package com.sparta.plusproject.service;

import com.sparta.plusproject.dto.PostRequestDto;
import com.sparta.plusproject.entity.Post;
import com.sparta.plusproject.repository.PostRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostService {
    private final PostRepository repository;

    public void createPost(PostRequestDto requestDto, HttpServletResponse res) {
        String title = requestDto.getTitle();
        String content = requestDto.getContent();
        System.out.println(title);
        System.out.println(content);
        Post post = new Post(title,content);
        repository.save(post);
    }
}
