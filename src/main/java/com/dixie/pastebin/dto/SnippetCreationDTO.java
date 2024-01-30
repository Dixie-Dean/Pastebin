package com.dixie.pastebin.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SnippetCreationDTO {
    private String body;
    private long expirationTime;
}
