package com.example.kkkezdesy.repositories;

import com.example.kkkezdesy.entities.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepo extends JpaRepository<Chat, Long> {
}
