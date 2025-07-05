package com.apelisser.algaposts.post_service.api.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class PageModel<T> {

    private List<T> content;
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;

}
