package com.devserbyn.twatch.service.mainbot.repository.impl;

import com.devserbyn.twatch.model.mainbot.User;
import com.devserbyn.twatch.repository.UserRepository;
import com.devserbyn.twatch.service.mainbot.repository.UserRepositoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserRepositoryServiceImpl implements UserRepositoryService {

    private final UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> save(User user) {
        return Optional.of(userRepository.save(user));
    }

    @Override
    public Optional<User> findByChatId(Long chatId) {
        return this.findAll().stream().filter(x -> x.getChatId() == chatId)
                                      .findFirst();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return this.findAll().stream().filter(x -> x.getUsername().equals(username))
                                      .findFirst();
    }

    @Override
    public void deleteByChatId(Long chatId) {
        Long foundId = this.findByChatId(chatId).orElseThrow(RuntimeException::new)
                           .getId();
        userRepository.deleteById(foundId);
    }
}
