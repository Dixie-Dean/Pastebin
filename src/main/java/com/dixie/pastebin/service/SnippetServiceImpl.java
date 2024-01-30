package com.dixie.pastebin.service;

import com.dixie.pastebin.dto.SnippetCreationDTO;
import com.dixie.pastebin.dto.SnippetUpdateDTO;
import com.dixie.pastebin.entity.Snippet;
import com.dixie.pastebin.repository.SnippetRepository;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class SnippetServiceImpl implements SnippetService {

    private final SnippetRepository snippetRepository;
    private final AtomicLong idCounter = new AtomicLong(0);

    public SnippetServiceImpl(SnippetRepository snippetRepository) {
        this.snippetRepository = snippetRepository;
    }

    @Override
    public String create(SnippetCreationDTO snippetCreationDTO) throws URISyntaxException, MalformedURLException {

        var snippetID = idCounter.incrementAndGet();

        URIBuilder builder = new URIBuilder();
        builder.setScheme("http");
        builder.setHost("localhost");
        builder.setPort(8080);
        builder.setPath("/pastebin/" + snippetID);
        URL url = builder.build().toURL();

        Snippet snippet = new Snippet(snippetID, "MOCKED_AUTHOR",
                snippetCreationDTO.getBody(), snippetCreationDTO.getExpirationTime(), url.toString());

        snippetRepository.save(snippet);
        return "File uploaded!";
    }

    @Override
    public List<Snippet> viewAll() {
        return snippetRepository.getAllSnippets();
    }

    @Override
    public String update(long id, SnippetUpdateDTO snippetUpdateDTO) {
        return snippetRepository.update(id, snippetUpdateDTO.getBody());
    }

    @Override
    public String delete(long id) {
        return snippetRepository.delete(id);
    }

}
