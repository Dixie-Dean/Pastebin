package com.dixie.pastebin.service;

import org.springframework.http.ResponseEntity;

public interface SnippetService {

    ResponseEntity<String> create(String body, long expiration);

    ResponseEntity<String> viewAll();

    ResponseEntity<String> update(long id, String body);

    ResponseEntity<String> delete(long id);

}
