package com.dixie.pastebin.repository;

import com.dixie.pastebin.entity.PastebinUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecurityRepository extends JpaRepository<PastebinUser, Long> {
    boolean existsByEmail(String email);
}
