package com.dixie.pastebin.application.model.entity;

import com.dixie.pastebin.security.authentication.model.entity.PastebinUser;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(schema = "pastebin", name = "snippets")
public class Snippet {

    @Id
    private String id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "author", referencedColumnName = "email")
    private PastebinUser author;

    @Column(name = "body")
    private String body;

    @Column(name = "creation_time")
    private LocalDateTime creationDateTime;

    @Column(name = "expiration_time")
    private LocalDateTime expirationDateTime;

    @Column(name = "link", unique = true)
    private String link;

}
