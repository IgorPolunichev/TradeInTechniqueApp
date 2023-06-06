package com.example.tradeintechniqueapp.database.repository.actRepo;

import com.example.tradeintechniqueapp.database.entity.Act;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ActRepository extends JpaRepository<Act, Long> {

    Optional<Act> findByNumber(String number);
    @EntityGraph(attributePaths = "{user, act}")
    @Query(value = "select a from ActUser a " +
                   "where a.user.id = :userId " +
                   "and a.act.id = (select max(a.id) from ActUser where a.user.id = :userId)")
    Optional<String> findLastAcyByUserId(Long userId);

}
