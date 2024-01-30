package com.dixie.pastebin.service;

import com.dixie.pastebin.dto.SnippetCreationDTO;
import com.dixie.pastebin.dto.SnippetDTO;
import com.dixie.pastebin.dto.SnippetUpdateDTO;
import com.dixie.pastebin.entity.Snippet;
import com.dixie.pastebin.mapper.SnippetMapper;
import com.dixie.pastebin.repository.SnippetRepository;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class SnippetServiceImpl implements SnippetService {

    private final SnippetRepository snippetRepository;

    private final SnippetMapper snippetMapper;

    private final AtomicLong idCounter = new AtomicLong(0);

    public SnippetServiceImpl(SnippetRepository snippetRepository, SnippetMapper snippetMapper) {
        this.snippetRepository = snippetRepository;
        this.snippetMapper = snippetMapper;
    }

    @Override
    public String create(SnippetCreationDTO snippetCreationDTO) throws URISyntaxException, MalformedURLException {

        var snippetID = idCounter.incrementAndGet();

        URIBuilder builder = new URIBuilder();
        builder.setScheme("http");
        builder.setHost("localhost");
        builder.setPort(8080);
        builder.setPath("/pastebin/snippet/" + snippetID);
        URL url = builder.build().toURL();

        Snippet snippet = new Snippet(snippetID, "MOCKED_AUTHOR",
                snippetCreationDTO.getBody(), snippetCreationDTO.getExpirationTime(), url.toString());

        snippetRepository.save(snippet);
        return "File uploaded!";
    }

    @Override
    public List<SnippetDTO> viewAll() {
        List<Snippet> snippets = snippetRepository.getAllSnippets();
        List<SnippetDTO> snippetDTOs = new ArrayList<>();

        for (Snippet snippet : snippets) {
            SnippetDTO snippetDTO = snippetMapper.turnIntoDTO(snippet);
            snippetDTOs.add(snippetDTO);
        }

        return snippetDTOs;
    }

    @Override
    public SnippetDTO getSnippet(long id) {
        Snippet snippet = snippetRepository.getSnippetsById(id);
        return snippetMapper.turnIntoDTO(snippet);
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
