package com.apelisser.algaposts.post_service.api.controller;

import com.apelisser.algaposts.post_service.api.mapper.PostMapper;
import com.apelisser.algaposts.post_service.api.model.PageModel;
import com.apelisser.algaposts.post_service.api.model.PostOutput;
import com.apelisser.algaposts.post_service.api.model.PostSummaryOutput;
import com.apelisser.algaposts.post_service.api.model.input.PostInput;
import com.apelisser.algaposts.post_service.domain.model.Post;
import com.apelisser.algaposts.post_service.domain.service.PostService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "/api/posts", produces = MediaType.APPLICATION_JSON_VALUE)
public class PostController {

    private final PostService postService;
    private final PostMapper postMapper;

    public PostController(PostService postService, PostMapper postMapper) {
        this.postService = postService;
        this.postMapper = postMapper;
    }

    @GetMapping
    public PageModel<PostSummaryOutput> getAllPosts(@PageableDefault Pageable pageable) {
        Page<Post> page = postService.getAllPosts(pageable);
        return postMapper.toPageModel(page);
    }

    @GetMapping("/{postId}")
    public PostOutput getPost(@PathVariable UUID postId) {
        Post post = postService.findPostOrFail(postId);
        return postMapper.toOutput(post);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public PostSummaryOutput create(@Valid @RequestBody PostInput postInput) {
        log.info("Creating post from author: {}", postInput);

        Post createdPost = postService.create(
            postInput.getTitle(),
            postInput.getBody(),
            postInput.getAuthor());
        return postMapper.toSummaryOutput(createdPost);
    }

}
