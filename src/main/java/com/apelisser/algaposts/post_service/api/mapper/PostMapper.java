package com.apelisser.algaposts.post_service.api.mapper;

import com.apelisser.algaposts.post_service.api.model.PageModel;
import com.apelisser.algaposts.post_service.api.model.PostOutput;
import com.apelisser.algaposts.post_service.api.model.PostSummaryOutput;
import com.apelisser.algaposts.post_service.application.service.SummaryService;
import com.apelisser.algaposts.post_service.domain.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostMapper {

    private final SummaryService summaryService;

    public PostMapper(SummaryService summaryService) {
        this.summaryService = summaryService;
    }

    public PostOutput toOutput(Post post) {
        var output = new PostOutput();
        output.setId(post.getId().toString());
        output.setTitle(post.getTitle());
        output.setBody(post.getContent());
        output.setAuthor(post.getAuthor());
        output.setWordCount(post.getWordCount());
        output.setCalculatedValue(post.getCalculatedValue());
        return output;
    }

    public PageModel<PostSummaryOutput> toPageModel(Page<Post> entityPage) {
        List<PostSummaryOutput> postSummaryOutputs = entityPage.getContent().stream()
            .map(this::toSummaryOutput)
            .toList();

        PageModel<PostSummaryOutput> pageModel = new PageModel<>();
        pageModel.setContent(postSummaryOutputs);
        pageModel.setPage(entityPage.getNumber());
        pageModel.setSize(entityPage.getSize());
        pageModel.setTotalElements(entityPage.getTotalElements());
        pageModel.setTotalPages(entityPage.getTotalPages());

        return pageModel;
    }

    public PostSummaryOutput toSummaryOutput(Post post) {
        int maxLines = 3;
        var model = new PostSummaryOutput();
        model.setId(post.getId().toString());
        model.setTitle(post.getTitle());
        model.setSummary(summaryService.summarizeContent(post.getContent(), maxLines));
        model.setAuthor(post.getAuthor());
        return model;
    }

}
