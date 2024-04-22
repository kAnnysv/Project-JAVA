package org.example.repositories;

import org.example.models.User;

import java.util.List;

public interface UsersRepository {
    List<User> findAll(int page, int size);
}
