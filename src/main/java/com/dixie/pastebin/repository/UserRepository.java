package com.dixie.pastebin.repository;

import com.dixie.pastebin.entity.PastebinUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<PastebinUser, Long> {
    boolean existsByEmail(String email);
    Optional<PastebinUser> findPastebinUserByEmail(String email);
}
