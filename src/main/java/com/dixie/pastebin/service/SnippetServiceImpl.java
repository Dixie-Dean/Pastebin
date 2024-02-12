package com.dixie.pastebin.service;

import com.dixie.pastebin.dto.snippet.SnippetCreationDTO;
import com.dixie.pastebin.dto.snippet.SnippetDTO;
import com.dixie.pastebin.dto.snippet.SnippetUpdateDTO;
import com.dixie.pastebin.entity.Snippet;
import com.dixie.pastebin.mapper.SnippetMapper;
import com.dixie.pastebin.repository.SnippetRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class SnippetServiceImpl implements SnippetService {
    private final SnippetRepository snippetRepository;
    private final SnippetMapper snippetMapper;

    public SnippetServiceImpl(SnippetRepository snippetRepository, SnippetMapper snippetMapper) {
        this.snippetRepository = snippetRepository;
        this.snippetMapper = snippetMapper;
    }

    @Override
    public String create(SnippetCreationDTO snippetCreationDTO) throws URISyntaxException, MalformedURLException {
        var snippetID = RandomStringUtils.random(8, true, true);
        var url = buildURL(snippetID);
        var creationDateTime = LocalDateTime.now();
        var expirationDateTime = creationDateTime.plusMinutes(snippetCreationDTO.getMinutesToLive());

        Snippet snippet = new Snippet(
                snippetID, "MOCKED_AUTHOR",
                snippetCreationDTO.getBody(),
                creationDateTime, expirationDateTime, url);
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
    public SnippetDTO getSnippet(String id) {
        Snippet snippet = snippetRepository.getSnippetById(id);
        return snippetMapper.turnIntoDTO(snippet);
    }

    @Override
    public String update(String id, SnippetUpdateDTO snippetUpdateDTO) {
        return snippetRepository.update(id, snippetUpdateDTO.getBody());
    }

    @Override
    public String delete(String id) {
        return snippetRepository.delete(id);
    }

    private String buildURL(String snippetID) throws URISyntaxException, MalformedURLException {
        URIBuilder builder = new URIBuilder();
        builder.setScheme("http");
        builder.setHost("localhost");
        builder.setPort(8080);
        builder.setPath("/pastebin/snippet/" + snippetID);
        URL url = builder.build().toURL();
        return url.toString();
    }
}
