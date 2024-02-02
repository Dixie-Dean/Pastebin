package com.dixie.pastebin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SnippetDTO {
    private long id;
    private String author;
    private String body;
    private long expirationTime;
    private String link;
    private boolean isExpired;
}
