package com.dixie.pastebin.controller;

import com.dixie.pastebin.dto.SnippetCreationDTO;
import com.dixie.pastebin.dto.SnippetDTO;
import com.dixie.pastebin.dto.SnippetUpdateDTO;
import com.dixie.pastebin.service.SnippetService;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/pastebin")
public class SnippetController {

    private final SnippetService snippetService;

    public SnippetController(SnippetService snippetService) {
        this.snippetService = snippetService;
    }

    @PostMapping()
    public String create(@RequestBody SnippetCreationDTO snippetCreationDTO) throws MalformedURLException, URISyntaxException {
        return snippetService.create(snippetCreationDTO);
    }

    @GetMapping("/all")
    public List<SnippetDTO> viewAll() {
        return snippetService.viewAll();
    }

    @GetMapping("/snippet/{id}")
    public SnippetDTO getSnippet(@PathVariable String id) {
        return snippetService.getSnippet(id);
    }

    @PutMapping("/edit/{id}")
    public String edit(@PathVariable String id, @RequestBody SnippetUpdateDTO snippetUpdateDTO) {
        return snippetService.update(id, snippetUpdateDTO);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        return snippetService.delete(id);
    }

}
