package com.example.tradeintechniqueapp.database.repository.userRepo;

import com.example.tradeintechniqueapp.database.entity.User;
import com.example.tradeintechniqueapp.database.entity.audit.AuditingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("UPDATE User u set u.counterActs = (u.counterActs + 1) where u.id = :id")
    int updateActCounterByUserId(Long id);



//    @Modifying(flushAutomatically = true, clearAutomatically = true)
//    @Query(value = "select created_at, created_by ,modified_at, last_modified_by from users ",  nativeQuery = true)
//    List<AuditingEntity<Long>> findAllWithAuditData();




}
