package com.example.tradeintechniqueapp.database.entity;

import com.example.tradeintechniqueapp.database.entity.audit.AuditingEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import java.time.Year;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
@Table(name = "machines")
public class Machine extends AuditingEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private String serialNumber;
    private Integer operatingTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;
   @Basic
   private Year yearOfRelease;

   public void setCompany(Company company){
       this.company = company;
       company.getMachines().add(this);
   }


    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
               "id = " + id + ", " +
               "type = " + type + ", " +
               "serialNumber = " + serialNumber + ", " +
               "operatingTime = " + operatingTime + ", " +
               "yearOfRelease = " + yearOfRelease + ")";
    }
}
