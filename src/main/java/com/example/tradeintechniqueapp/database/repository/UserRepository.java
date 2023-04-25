package com.example.tradeintechniqueapp.database.repository;

import com.example.tradeintechniqueapp.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("UPDATE User u set u.counterActs = (u.counterActs + 1) where u.id = :id")
    int updateActCounterByUserId(Long id);





}
