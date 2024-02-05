package com.dixie.pastebin.service;

import com.dixie.pastebin.entity.Snippet;
import com.dixie.pastebin.repository.SnippetRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class MaintenanceServiceImpl implements MaintenanceService {
    private final SnippetRepository snippetRepository;

    public MaintenanceServiceImpl(SnippetRepository snippetRepository) {
        this.snippetRepository = snippetRepository;
    }

    @Override
    @Scheduled(fixedDelay = 1, initialDelay = 1, timeUnit = TimeUnit.MINUTES)
    public void isSnippetExpired() {
        List<Snippet> snippets = snippetRepository.getAllSnippets();
        for (Snippet snippet : snippets) {
            if (LocalDateTime.now().isAfter(snippet.getExpirationDateTime())) {
                snippetRepository.delete(snippet.getId());
            }
        }
    }

}
