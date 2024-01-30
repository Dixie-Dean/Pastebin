package com.dixie.pastebin.controller;

import com.dixie.pastebin.dto.SnippetCreationDTO;
import com.dixie.pastebin.dto.SnippetUpdateDTO;
import com.dixie.pastebin.entity.Snippet;
import com.dixie.pastebin.service.SnippetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pastebin")
public class SnippetController {

    private final SnippetService snippetService;

    public SnippetController(SnippetService snippetService) {
        this.snippetService = snippetService;
    }

    @PostMapping()
    public String create(@RequestBody SnippetCreationDTO snippetCreationDTO) {
        return snippetService.create(snippetCreationDTO);
    }

    @GetMapping("/all")
    public List<Snippet> viewAll() {
        return snippetService.viewAll();
    }

    @PutMapping("/edit/{id}")
    public String edit(@PathVariable long id, @RequestBody SnippetUpdateDTO snippetUpdateDTO) {
        return snippetService.update(id, snippetUpdateDTO);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable long id) {
        return snippetService.delete(id);
    }

}
