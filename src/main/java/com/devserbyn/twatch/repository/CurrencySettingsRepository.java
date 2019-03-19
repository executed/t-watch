package com.devserbyn.twatch.repository;

import com.devserbyn.twatch.model.mainbot.currency.CurrencySettings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencySettingsRepository extends JpaRepository<CurrencySettings, Long> {

}
