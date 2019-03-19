package com.devserbyn.twatch.model.mainbot;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table (name = "[user]")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private long chatId;

    private String username;
}
