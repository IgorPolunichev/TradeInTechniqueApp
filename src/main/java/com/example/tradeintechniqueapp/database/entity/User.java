package com.example.tradeintechniqueapp.database.entity;

import com.example.tradeintechniqueapp.database.entity.audit.AuditingEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User extends AuditingEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String firstname;
    private String lastname;
    private String surname;
    private LocalDate birthDate;
    private String password;
    private Integer counterActs;

    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<ActUser> actUserList = new ArrayList<>();


    @Enumerated(EnumType.STRING)
    private Position position;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @Override
    public String toString() {
        return "User{" +
               "id=" + id +
               ", username='" + username + '\'' +
               ", firstname='" + firstname + '\'' +
               ", lastname='" + lastname + '\'' +
               ", surname='" + surname + '\'' +
               ", birthDate=" + birthDate +
               ", password='" + password + '\'' +
               ", position=" + position +
               ", role=" + role +
               '}';
    }


    public User(Long id) {
        this.id = id;
    }
}
