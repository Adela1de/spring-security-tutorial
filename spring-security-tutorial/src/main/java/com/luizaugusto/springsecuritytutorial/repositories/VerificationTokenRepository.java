package com.luizaugusto.springsecuritytutorial.repositories;

import com.luizaugusto.springsecuritytutorial.entitites.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
}
