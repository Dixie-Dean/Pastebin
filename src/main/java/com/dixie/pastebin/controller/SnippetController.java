package com.dixie.pastebin.controller;

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

    @PostMapping
    public String create(@RequestBody String body, @RequestBody long expirationTime) {
        return snippetService.create(body, expirationTime);
    }

    @GetMapping("/all")
    public List<Snippet> viewAll() {
        return snippetService.viewAll();
    }

    @PutMapping("/edit/{id}")
    public String edit(@PathVariable long id, @RequestBody String body) {
        return snippetService.update(id, body);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable long id) {
        return snippetService.delete(id);
    }

}
