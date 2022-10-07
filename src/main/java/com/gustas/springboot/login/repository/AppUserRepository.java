package com.gustas.springboot.login.repository;

import com.gustas.springboot.login.modules.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Boolean existsByEmail(String Email);
    Optional<AppUser> findById(Long Id);

    @Modifying
    @Transactional
    @Query("UPDATE AppUser c SET c.enabled = TRUE WHERE c.email = ?1")
    void findByEmail(String email);
}
