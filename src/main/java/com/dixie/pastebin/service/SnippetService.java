package com.dixie.pastebin.service;

import com.dixie.pastebin.entity.Snippet;

import java.util.List;

public interface SnippetService {

    String create(String body, long expirationTime);

    List<Snippet> viewAll();

    String update(long id, String body);

    String delete(long id);

}
