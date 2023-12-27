package com.sparta.plusproject.service;

import com.sparta.plusproject.dto.PostRequestDto;
import com.sparta.plusproject.entity.Post;
import com.sparta.plusproject.repository.PostRepository;
import jakarta.servlet.http.HttpServletResponse;
import com.sparta.plusproject.dto.PostResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostService {
    private final PostRepository repository;

    public void createPost(PostRequestDto requestDto, HttpServletResponse res) throws Exception {
        String title = requestDto.getTitle();
        String content = requestDto.getContent();

        System.out.println(title.length());
        System.out.println(content);
        Post post = new Post(title,content);
        repository.save(post);
    }

    public List<PostResponseDto> getAllPosts(){
        return repository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(PostResponseDto::new)
                .collect(Collectors.toList());
    }
}
