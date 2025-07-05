package com.apelisser.algaposts.post_service.infrastructure.service.textprocessor.model;

import java.math.BigDecimal;

public record PostStatsOutput(
    String postId,
    Integer wordCount,
    BigDecimal calculatedValue) {
}
