package com.apelisser.algaposts.post_service.domain.service;

import com.apelisser.algaposts.post_service.domain.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.UUID;

public interface PostService {

    Post create(String title, String body, String author);

    Post findPostOrFail(UUID postId);

    Page<Post> getAllPosts(Pageable pageable);

    Post setPostStats(UUID postId, int wordCount, BigDecimal calculatedValue);

}
