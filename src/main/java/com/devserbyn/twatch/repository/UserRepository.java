package com.devserbyn.twatch.repository;

import com.devserbyn.twatch.model.mainbot.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
