package com.example.inventory.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.inventory.entity.Users;

public interface UserRepo extends JpaRepository<Users, String> {
	Optional<Users> findByEmail(String email);
    Optional<Users> findByUserIdOrEmail(String username, String email);
    Optional<Users> findByUserId(String username);
//    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    List<Users> findAllByRole(String role);

}
