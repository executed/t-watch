package com.devserbyn.twatch.service.mainbot.repository;

import com.devserbyn.twatch.model.mainbot.User;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryService {

    List<User> findAll();

    Optional<User> save(User user);

    Optional<User> findByChatId(Long chatId);

    Optional<User> findByUsername(String username);

    void deleteByChatId(Long chatId);
}
