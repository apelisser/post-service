package com.apelisser.algaposts.post_service.api.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class PostSummaryOutput {

    private String id;
    private String title;
    private String author;
    private String summary;

}
