package com.apelisser.algaposts.post_service.api.model.input;

import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class PostInput {

    @NotBlank
    private String title;

    @NotBlank
    private String author;

    @NotBlank
    private String body;

}
