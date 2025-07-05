package com.apelisser.algaposts.post_service.infrastructure.service.textprocessor;

import com.apelisser.algaposts.post_service.domain.service.PostStatsService;
import com.apelisser.algaposts.post_service.infrastructure.service.textprocessor.model.PostStatsInput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.apelisser.algaposts.post_service.core.rabbitmq.RabbitMQConfig.*;

@Slf4j
@Service
public class PostStatsServiceAdapter implements PostStatsService {

    private final RabbitTemplate rabbitTemplate;

    public PostStatsServiceAdapter(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void calculateStats(UUID postId, String postContent) {
        PostStatsInput postInput = new PostStatsInput(postId.toString(), postContent);

        log.info("Calculating stats for postId: {}", postInput.postId());
        rabbitTemplate.convertAndSend(DIRECT_EXCHANGE_POST_PROCESSING, ROUTING_KEY_POST_PROCESSING, postInput);
    }

}
