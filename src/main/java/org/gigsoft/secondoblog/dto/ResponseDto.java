package org.gigsoft.secondoblog.dto;

import lombok.Builder;

@Builder
public record ResponseDto<T>(String message, T data) {
}
