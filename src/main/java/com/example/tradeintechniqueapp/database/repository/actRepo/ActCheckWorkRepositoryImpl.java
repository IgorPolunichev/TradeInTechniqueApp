package com.example.tradeintechniqueapp.database.repository.actRepo;

import com.example.tradeintechniqueapp.database.entity.ActUser;
import com.example.tradeintechniqueapp.database.entity.Work;
import com.example.tradeintechniqueapp.dto.workReadDto.WorkCheckDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ActCheckWorkRepositoryImpl implements ActCheckWorkRepository {
    private final EntityManager entityManager;

    @Override
    public Optional<List<Work>> checkWork(Work work, Long userId) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Work> query = criteriaBuilder.createQuery(Work.class);
        Root<Work> workRoot = query.from(Work.class);
        Root<ActUser> actUserRoot = query.from(ActUser.class);
        Join<Work, ActUser> actUserWorkJoin = actUserRoot.join("act", JoinType.INNER);
        List<Predicate> predicates = new ArrayList<>();
        Predicate userIdd = criteriaBuilder.equal(actUserRoot.get("user"), userId);
        Predicate date = criteriaBuilder.equal(workRoot.get("workDate"), work.getWorkDate());

        Predicate timeStart = criteriaBuilder
                .between(workRoot.get("startWork")
                        , work.getStartWork()
                        , work.getEndWork().minusSeconds(1L));

        Predicate timeEnd = criteriaBuilder
                .between(workRoot.get("endWork")
                        , work.getStartWork().plusSeconds(1L)
                        , work.getEndWork());

        Predicate timeBetween = criteriaBuilder.lessThanOrEqualTo(
                workRoot.get("startWork"), work.getStartWork()
        );

        Predicate timeBetween2 = criteriaBuilder.greaterThanOrEqualTo(
                workRoot.get("endWork"), work.getEndWork()
        );
        Predicate or = criteriaBuilder.or(timeStart, timeEnd);
        Predicate and = criteriaBuilder.and(timeBetween, timeBetween2);

        predicates.add(userIdd);
        predicates.add(date);
        predicates.add(criteriaBuilder.or(or, and));
        query.select(workRoot);
        query.where(predicates.toArray(new Predicate[]{}));
        List<Work> resultList = entityManager.createQuery(query).getResultList();
        return Optional.of(resultList);
    }
}
