package com.dixie.pastebin.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(schema = "pastebin", name = "snippets")
public class Snippet {

    @Id
    private String id;

    @Column(name = "author")
    private String author;

    @Column(name = "body")
    private String body;

    @Column(name = "creation_time")
    private LocalDateTime creationDateTime;

    @Column(name = "expiration_time")
    private LocalDateTime expirationDateTime;

    @Column(name = "link", unique = true)
    private String link;

}
