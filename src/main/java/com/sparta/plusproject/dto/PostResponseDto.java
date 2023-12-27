package com.sparta.plusproject.dto;

import com.sparta.plusproject.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
//@NoArgsConstructor
public class PostResponseDto {
    private String username;
    private String title;
    private String content;
    //private String nickname;
    private LocalDateTime createdAt;
    private LocalDateTime modifedAt;


    public PostResponseDto(Post post) {
        this.username = post.getUser().getUsername();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.createdAt = post.getCreatedAt();
        this.modifedAt = post.getModifiedAt();
        //this.nickname = post.getUser().getNickname();
    }
}
