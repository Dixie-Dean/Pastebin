package com.dixie.pastebin.repository;

import com.dixie.pastebin.entity.Snippet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SnippetRepository extends JpaRepository<Snippet, Long> {

    @Query(value = "select * from pastebin.snippets", nativeQuery = true)
    List<Snippet> getAllSnippets();

    Snippet getSnippetsById(long id);

    @Query(value = "update pastebin.snippets set body = :body " +
            "where id = :id returning 'Snippet updated!'", nativeQuery = true)
    String update(@Param("id") long id, @Param("body") String body);

    @Query(value = "delete from pastebin.snippets where id = :id returning 'Snipped deleted!'", nativeQuery = true)
    String delete(@Param("id") long id);

}
