package com.dixie.pastebin.dto.snippet;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SnippetCreationDTO {
    private String body;
    private long minutesToLive;
}
