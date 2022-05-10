package com.example.kkkezdesy.repositories;

import com.example.kkkezdesy.entities.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatMessageRepo extends JpaRepository<ChatMessage, Long> {
}
