package com.dixie.pastebin.service;

import com.dixie.pastebin.dto.SnippetCreationDTO;
import com.dixie.pastebin.dto.SnippetDTO;
import com.dixie.pastebin.dto.SnippetUpdateDTO;
import com.dixie.pastebin.entity.Snippet;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;

public interface SnippetService {

    String create(SnippetCreationDTO snippetCreationDTO) throws URISyntaxException, MalformedURLException;

    List<Snippet> viewAll();

    SnippetDTO getSnippet(long id);

    String update(long id, SnippetUpdateDTO snippetUpdateDTO);

    String delete(long id);

}
