package com.dixie.pastebin.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(schema = "pastebin", name = "snippets")
public class Snippet {

    @Id
    private long id;

    @Column(name = "author")
    private String author;

    @Column(name = "body")
    private String body;

    @Column(name = "expiration_time")
    private long expirationTime;

    @Column(name = "link", unique = true)
    private String link;

}
