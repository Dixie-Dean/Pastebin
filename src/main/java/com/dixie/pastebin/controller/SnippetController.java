package com.dixie.pastebin.controller;

import com.dixie.pastebin.service.SnippetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pastebin")
public class SnippetController {

    private final SnippetService snippetService;

    public SnippetController(SnippetService snippetService) {
        this.snippetService = snippetService;
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody String body, @RequestBody long expirationTime) {
        return snippetService.create(body, expirationTime);
    }

    @GetMapping("/all")
    public ResponseEntity<String> viewAll() {
        return snippetService.viewAll();
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<String> edit(@PathVariable long id, @RequestBody String body) {
        return snippetService.update(id, body);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        return snippetService.delete(id);
    }

}
