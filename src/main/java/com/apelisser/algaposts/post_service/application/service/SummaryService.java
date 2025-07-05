package com.apelisser.algaposts.post_service.application.service;

import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class SummaryService {

    public String summarizeContent(String content, int maxLines) {
        if (content == null || content.isBlank()) {
            return content;
        }

        if (maxLines < 1) {
            return "";
        }

        return content.lines()
            .limit(maxLines)
            .collect(Collectors.joining("\n"));
    }

}
