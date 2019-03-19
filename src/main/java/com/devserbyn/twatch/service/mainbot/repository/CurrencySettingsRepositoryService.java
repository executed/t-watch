package com.devserbyn.twatch.service.mainbot.repository;

import com.devserbyn.twatch.model.mainbot.User;
import com.devserbyn.twatch.model.mainbot.currency.Currency;
import com.devserbyn.twatch.model.mainbot.currency.CurrencySettings;

import java.util.List;
import java.util.Optional;

public interface CurrencySettingsRepositoryService {

    Optional<CurrencySettings> save(CurrencySettings currencySettings);

    Optional<CurrencySettings> saveByUsername(CurrencySettings settings, String username);

    List<CurrencySettings> findAll();

    Optional<CurrencySettings> findByUser(User user);
}
