package com.apelisser.algaposts.post_service.api.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class PostOutput {

    private String id;
    private String title;
    private String author;
    private Integer wordCount;
    private BigDecimal calculatedValue;
    private String body;

}
