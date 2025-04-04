package com.haven.estio.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.haven.estio.entities.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByFirstName(String firstName);
    Optional<UserEntity> findByLastName(String lastName);
    Optional<UserEntity> findByEmailAndPassword(String email, String password);
}