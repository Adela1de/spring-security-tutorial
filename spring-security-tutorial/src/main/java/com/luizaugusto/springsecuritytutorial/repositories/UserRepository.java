package com.luizaugusto.springsecuritytutorial.repositories;

import com.luizaugusto.springsecuritytutorial.entitites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}