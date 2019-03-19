package com.devserbyn.twatch.model.mainbot.currency;

import com.devserbyn.twatch.model.mainbot.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table (name = "currency_settings")
@Getter
@Setter
@NoArgsConstructor
public class CurrencySettings {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.REMOVE)
    private User user;

    private Currency firstCurrency;

    private Currency secondCurrency;
}
