package com.apelisser.algaposts.post_service.domain.repository;

import com.apelisser.algaposts.post_service.domain.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {
}
