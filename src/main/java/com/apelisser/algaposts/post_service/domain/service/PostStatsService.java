package com.apelisser.algaposts.post_service.domain.service;

import java.util.UUID;

public interface PostStatsService {

    void calculateStats(UUID postId, String postContent);

}
