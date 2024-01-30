package com.dixie.pastebin.service;

import com.dixie.pastebin.dto.SnippetCreationDTO;
import com.dixie.pastebin.dto.SnippetUpdateDTO;
import com.dixie.pastebin.entity.Snippet;
import com.dixie.pastebin.repository.SnippetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SnippetServiceImpl implements SnippetService {

    private final SnippetRepository snippetRepository;

    public SnippetServiceImpl(SnippetRepository snippetRepository) {
        this.snippetRepository = snippetRepository;
    }

    @Override
    public String create(SnippetCreationDTO snippetCreationDTO) {
        return snippetRepository.create(
                "MOCKED_AUTHOR",
                snippetCreationDTO.getBody(),
                "MOCKED_LINK" + UUID.randomUUID(),
                snippetCreationDTO.getExpirationTime());
    }

    @Override
    public List<Snippet> viewAll() {
        return snippetRepository.getAllSnippets();
    }

    @Override
    public String update(long id, SnippetUpdateDTO snippetUpdateDTO) {
        return snippetRepository.update(id, snippetUpdateDTO.getBody());
    }

    @Override
    public String delete(long id) {
        return snippetRepository.delete(id);
    }

}
