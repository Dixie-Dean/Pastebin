package com.dixie.pastebin.service;

import com.dixie.pastebin.entity.Snippet;
import com.dixie.pastebin.repository.SnippetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SnippetServiceImpl implements SnippetService {

    private final SnippetRepository snippetRepository;

    public SnippetServiceImpl(SnippetRepository snippetRepository) {
        this.snippetRepository = snippetRepository;
    }

    @Override
    public String create(String body, long expirationTime) {
        return snippetRepository.create("MOCKED_AUTHOR", body, "MOCKED_LINK", expirationTime);
    }

    @Override
    public List<Snippet> viewAll() {
        return snippetRepository.getAllSnippets();
    }

    @Override
    public String update(long id, String body) {
        return snippetRepository.update(id, body);
    }

    @Override
    public String delete(long id) {
        return snippetRepository.delete(id);
    }

}
