package com.dixie.pastebin.service;

import com.dixie.pastebin.entity.Snippet;
import com.dixie.pastebin.repository.SnippetRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaintenanceServiceImpl implements MaintenanceService {
    private final SnippetRepository snippetRepository;

    public MaintenanceServiceImpl(SnippetRepository snippetRepository) {
        this.snippetRepository = snippetRepository;
    }

    @Override
    @Scheduled(fixedDelay = 60000)
    public void isSnippetExpired() {
        List<Snippet> snippets = snippetRepository.getAllSnippets();
        for (Snippet snippet : snippets) {
            if (snippet.getExpirationTime() < System.currentTimeMillis()) {
                snippetRepository.deleteById(snippet.getId());
            }
        }
    }

}
