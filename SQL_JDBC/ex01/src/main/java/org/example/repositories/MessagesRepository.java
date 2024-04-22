package org.example.repositories;



import org.example.models.Message;

import java.util.Optional;

public interface MessagesRepository {
    Optional<Message> finById(Long id);
}
