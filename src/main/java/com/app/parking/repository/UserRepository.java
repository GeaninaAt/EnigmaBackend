package com.app.parking.repository;

import com.app.parking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by gatomulesei on 3/28/2017.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    User findByEmail(String email);
}
