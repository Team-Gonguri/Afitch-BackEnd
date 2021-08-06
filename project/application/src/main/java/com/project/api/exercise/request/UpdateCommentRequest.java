package com.project.api.exercise.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
@AllArgsConstructor
public class UpdateCommentRequest {

    @NotEmpty
    private String text;
}
