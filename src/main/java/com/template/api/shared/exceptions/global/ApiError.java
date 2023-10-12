package com.template.api.shared.exceptions.global;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ApiError {
    private Integer statusCode;
    private String message;
    private String url;
    private String method;
}
