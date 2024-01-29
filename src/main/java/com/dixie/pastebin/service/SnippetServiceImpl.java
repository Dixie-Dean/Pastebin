package com.dixie.pastebin.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SnippetServiceImpl implements SnippetService {

    @Override
    public ResponseEntity<String> create(String body, long expirationTime) {
        return null;
    }

    @Override
    public ResponseEntity<String> viewAll() {
        return null;
    }

    @Override
    public ResponseEntity<String> update(long id, String body) {
        return null;
    }

    @Override
    public ResponseEntity<String> delete(long id) {
        return null;
    }

}
