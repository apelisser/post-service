package com.apelisser.algaposts.post_service.domain.service.impl;

import com.apelisser.algaposts.post_service.domain.exception.EntityNotFoundException;
import com.apelisser.algaposts.post_service.domain.model.Post;
import com.apelisser.algaposts.post_service.domain.repository.PostRepository;
import com.apelisser.algaposts.post_service.domain.service.PostService;
import com.apelisser.algaposts.post_service.domain.service.PostStatsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PostStatsService postStatsService;

    public PostServiceImpl(PostRepository postRepository, PostStatsService postStatsService) {
        this.postRepository = postRepository;
        this.postStatsService = postStatsService;
    }

    @Override
    public Post create(String title, String body, String author) {
        Post post = new Post();
        post.setTitle(title);
        post.setContent(body);
        post.setAuthor(author);

        Post savedPost = postRepository.saveAndFlush(post);
        postStatsService.calculateStats(savedPost.getId(), savedPost.getContent());
        return savedPost;
    }

    @Override
    public Post findPostOrFail(UUID postId) {
        return postRepository.findById(postId).orElseThrow(() -> {
            EntityNotFoundException ex = new EntityNotFoundException(Post.class);
            ex.addProperty("id", postId.toString());
            return ex;
        });
    }

    @Override
    public Page<Post> getAllPosts(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @Override
    public Post setPostStats(UUID postId, int wordCount, BigDecimal calculatedValue) {
        Post post = this.findPostOrFail(postId);
        post.setWordCount(wordCount);
        post.setCalculatedValue(calculatedValue);

        return postRepository.saveAndFlush(post);
    }

}
