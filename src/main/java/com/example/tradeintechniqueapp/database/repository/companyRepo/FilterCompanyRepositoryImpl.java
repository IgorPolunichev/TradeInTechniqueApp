package com.example.tradeintechniqueapp.database.repository.companyRepo;

import com.example.tradeintechniqueapp.database.entity.Company;
import com.example.tradeintechniqueapp.dto.companiesDto.CompanyFilter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class FilterCompanyRepositoryImpl implements FilterCompanyRepository {

    private final EntityManager entityManager;

    @Override
    public List<Company> findAllCompanyByFilter(CompanyFilter companyFilter, Pageable pageable) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query1 = criteriaBuilder.createQuery(Long.class);
        CriteriaQuery<Company> query = criteriaBuilder.createQuery(Company.class);
        Root<Company> company = query.from(Company.class);
         query1.select(criteriaBuilder.count(query1.from(Company.class)));
        entityManager.createQuery(query1).getSingleResult();
        query.select(company);
        List<Predicate> predicates = new ArrayList<>();
        if (companyFilter.getNameCompany() != null) {
            predicates.add(criteriaBuilder.like(company.get("nameCompany"), companyFilter.getNameCompany()));
        }
        query.where(predicates.toArray(Predicate[]::new));
        List<Company> list = entityManager.createQuery(query).setFirstResult(0).setMaxResults(10).getResultList();
        return list;
    }
}
