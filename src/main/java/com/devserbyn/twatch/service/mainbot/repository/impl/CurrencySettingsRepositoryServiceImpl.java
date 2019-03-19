package com.devserbyn.twatch.service.mainbot.repository.impl;

import com.devserbyn.twatch.model.mainbot.User;
import com.devserbyn.twatch.model.mainbot.currency.CurrencySettings;
import com.devserbyn.twatch.repository.CurrencySettingsRepository;
import com.devserbyn.twatch.service.mainbot.repository.CurrencySettingsRepositoryService;
import com.devserbyn.twatch.service.mainbot.repository.UserRepositoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CurrencySettingsRepositoryServiceImpl implements CurrencySettingsRepositoryService {

    private final CurrencySettingsRepository currencySettingsRepo;
    private final UserRepositoryService userRepoService;


    @Override
    public Optional<CurrencySettings> save(CurrencySettings currencySettings) {
        return Optional.of(currencySettingsRepo.save(currencySettings));
    }

    @Override
    public Optional<CurrencySettings> saveByUsername(CurrencySettings settings, String username) {
        User user = userRepoService.findByUsername(username).orElseThrow(RuntimeException::new);
        settings.setUser(user);

        return Optional.of(currencySettingsRepo.save(settings));
    }

    @Override
    public List<CurrencySettings> findAll() {
        return currencySettingsRepo.findAll();
    }

    @Override
    public Optional<CurrencySettings> findByUser(User user) {
        return currencySettingsRepo.findAll().stream().filter(x -> x.getUser().getId() == user.getId())
                                                      .findFirst();
    }
}
