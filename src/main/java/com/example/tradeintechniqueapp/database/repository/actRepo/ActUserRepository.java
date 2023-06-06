package com.example.tradeintechniqueapp.database.repository.actRepo;

import com.example.tradeintechniqueapp.database.entity.ActUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActUserRepository extends JpaRepository <ActUser, Long> {
}
