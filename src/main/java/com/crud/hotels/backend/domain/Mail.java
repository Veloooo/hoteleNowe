package com.crud.hotels.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class Mail {
    private String mailTo;
    private String subject;
    private String message;
    private String toCc;
}
