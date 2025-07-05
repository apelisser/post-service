package com.apelisser.algaposts.post_service.infrastructure.service.textprocessor;

import com.apelisser.algaposts.post_service.core.rabbitmq.RabbitMQConfig;
import com.apelisser.algaposts.post_service.domain.service.PostService;
import com.apelisser.algaposts.post_service.infrastructure.service.textprocessor.model.PostStatsOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class PostStatsListener {

    private final PostService postService;

    public PostStatsListener(PostService postService) {
        this.postService = postService;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_POST_PROCESSING_RESULT, concurrency = "2-5")
    public void updatePostStats(PostStatsOutput postStats) {
        log.info("Updating stats for postId: {}", postStats.postId());
        postService.setPostStats(
            UUID.fromString(postStats.postId()),
            postStats.wordCount(),
            postStats.calculatedValue());
    }

}
