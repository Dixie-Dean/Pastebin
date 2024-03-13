package com.dixie.pastebin.mapper;

import com.dixie.pastebin.application.model.dto.SnippetDTO;
import com.dixie.pastebin.application.model.entity.Snippet;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SnippetMapper {

    SnippetDTO turnIntoDTO(Snippet snippet);

    Snippet turnIntoSnippet(SnippetDTO snippetDTO);
}
