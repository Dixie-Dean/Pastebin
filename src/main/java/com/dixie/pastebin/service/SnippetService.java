package com.dixie.pastebin.service;

import com.dixie.pastebin.dto.SnippetCreationDTO;
import com.dixie.pastebin.dto.SnippetDTO;
import com.dixie.pastebin.dto.SnippetUpdateDTO;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;

public interface SnippetService {
    String create(SnippetCreationDTO snippetCreationDTO) throws URISyntaxException, MalformedURLException;

    List<SnippetDTO> viewAll();

    SnippetDTO getSnippet(String id);

    String update(String id, SnippetUpdateDTO snippetUpdateDTO);

    String delete(String id);
}
