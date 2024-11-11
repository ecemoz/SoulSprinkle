package com.yildiz.soulsprinkle.repository;

import com.yildiz.soulsprinkle.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    Optional<User>findByFirstnameAndLastname(String firstname, String lastname);

    Optional<User>findByEmailAndUsername(String email, String username);

    List<User>findByPictureUrlIsNotNull();
}
