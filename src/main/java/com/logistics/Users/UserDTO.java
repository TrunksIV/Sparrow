package com.logistics.Users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Repository
@Transactional(readOnly = true)
public interface UserDTO extends JpaRepository<User,Long> {
    Optional<User>  findByEmail(String email);
}
