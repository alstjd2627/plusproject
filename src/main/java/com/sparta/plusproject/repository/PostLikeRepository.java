package com.sparta.plusproject.repository;

import com.sparta.plusproject.entity.Post;
import com.sparta.plusproject.entity.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    Optional<PostLike> findByPostId(Long postId);

    List<PostLike> findALlByPostId(Long postId);
}
