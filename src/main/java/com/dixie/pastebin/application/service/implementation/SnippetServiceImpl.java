package com.dixie.pastebin.application.service.implementation;

import com.dixie.pastebin.application.model.dto.SnippetCreationDTO;
import com.dixie.pastebin.application.model.dto.SnippetDTO;
import com.dixie.pastebin.application.model.dto.SnippetUpdateDTO;
import com.dixie.pastebin.application.model.entity.Snippet;
import com.dixie.pastebin.application.repository.SnippetRepository;
import com.dixie.pastebin.application.service.SnippetService;
import com.dixie.pastebin.mapper.SnippetMapper;
import com.dixie.pastebin.security.authentication.repository.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private final UserRepository userRepository;
    private final SnippetMapper snippetMapper;

    public SnippetServiceImpl(SnippetRepository snippetRepository, UserRepository userRepository, SnippetMapper snippetMapper) {
        this.snippetRepository = snippetRepository;
        this.userRepository = userRepository;
        this.snippetMapper = snippetMapper;
    }

    @Override
    public String create(SnippetCreationDTO snippetCreationDTO) throws URISyntaxException, MalformedURLException {
        var snippetID = RandomStringUtils.random(8, true, true);
        var url = buildURL(snippetID);
        var creationDateTime = LocalDateTime.now();
        var expirationDateTime = creationDateTime.plusMinutes(snippetCreationDTO.getMinutesToLive());
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var pastebinUser = userRepository.findPastebinUserByEmail(authentication.getName());

        if (pastebinUser.isPresent()) {
            Snippet snippet = new Snippet(snippetID, pastebinUser.get(),
                    snippetCreationDTO.getBody(), creationDateTime,
                    expirationDateTime, url);
            snippetRepository.save(snippet);
        }
        return "File uploaded!";
    }

    @Override
    public List<SnippetDTO> viewAll() {
        List<Snippet> snippets = snippetRepository.getAllSnippets();
        List<SnippetDTO> snippetDTOs = new ArrayList<>();

        for (Snippet snippet : snippets) {
            SnippetDTO snippetDTO = snippetMapper.toSnippetDTO(snippet);
            snippetDTOs.add(snippetDTO);
        }
        return snippetDTOs;
    }

    @Override
    public SnippetDTO getSnippet(String id) {
        Snippet snippet = snippetRepository.getSnippetById(id);
        return snippetMapper.toSnippetDTO(snippet);
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
