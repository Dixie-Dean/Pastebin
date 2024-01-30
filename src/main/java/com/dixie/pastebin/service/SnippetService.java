package com.dixie.pastebin.service;

import com.dixie.pastebin.dto.SnippetCreationDTO;
import com.dixie.pastebin.dto.SnippetUpdateDTO;
import com.dixie.pastebin.entity.Snippet;

import java.util.List;

public interface SnippetService {

    String create(SnippetCreationDTO snippetCreationDTO);

    List<Snippet> viewAll();

    String update(long id, SnippetUpdateDTO snippetUpdateDTO);

    String delete(long id);

}
