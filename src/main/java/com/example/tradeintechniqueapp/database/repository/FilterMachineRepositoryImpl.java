package com.example.tradeintechniqueapp.database.repository;


import com.example.tradeintechniqueapp.database.entity.Machine;
import com.example.tradeintechniqueapp.dto.MachineDto;
import com.example.tradeintechniqueapp.dto.MachineFilter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class FilterMachineRepositoryImpl implements FilterMachineRepository {

    private final String FIINDE_BY_COMPANY_AND_TYPE = """
            SELECT serial_number, type
            FROM machines
            WHERE company_id = ? AND type = ?""";

    private final String UPDATE_COMPANY= """
            UPDATE machines
            SET company_id = ?
            WHERE id = ?""";
    private final EntityManager entityManager;
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Machine> findAllByFilter(MachineFilter machineFilter) {
        /**
         * QUERYDSL
         */
//        Predicate predicate = QPredicate.builder().add(machineFilter.serialNumber(),
//                        QMachine.machine.serialNumber::containsIgnoreCase).
//                add(machineFilter.type(), QMachine.machine.type::containsIgnoreCase)
//                .build();
//
//        return new JPAQuery<Machine>(entityManager).select(QMachine.machine).
//                from(QMachine.machine).
//                where(predicate).
//                fetch();
//        return null;


/**
 * CriteriaAPI
 */

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Machine> query = criteriaBuilder.createQuery(Machine.class);
        Root<Machine> from = query.from(Machine.class);

        List<Predicate> predicates = new ArrayList<>();
        if (machineFilter.serialNumber() != null) {
            predicates.add(criteriaBuilder.like(from.get("serialNumber"), machineFilter.serialNumber()));
        }
        if (machineFilter.type() != null) {
            predicates.add(criteriaBuilder.like(from.get("type"), machineFilter.type()));
        }
        if (machineFilter.operatingTime() != null) {
            predicates.add(criteriaBuilder.like(from.get("operatingTime"), String.valueOf(machineFilter.operatingTime())));
        }
        query.select(from);
        query.where(predicates.toArray(Predicate[]::new));

        return entityManager.createQuery(query).getResultList();


    }

    @Override
    public List<MachineDto> findAllByCompanyIdAndType(Long companyId, String type) {

        return jdbcTemplate.query(FIINDE_BY_COMPANY_AND_TYPE, (rs, rowNum) -> new MachineDto(null,
                rs.getString(2),
                rs.getString(1)), companyId, type );
    }

    @Override
    public void updateCompany(List<Machine> machines) {
        List<Object[]> args = machines.stream().
                map(machine -> new Object[]{machine.getCompany().getId(), machine.getId()}).
                toList();
        jdbcTemplate.batchUpdate(UPDATE_COMPANY, args);
    }
}
