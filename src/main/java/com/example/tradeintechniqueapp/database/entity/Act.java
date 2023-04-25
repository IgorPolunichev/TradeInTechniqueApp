package com.example.tradeintechniqueapp.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "acts")
public class Act implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    @Builder.Default
    @ElementCollection
    @CollectionTable(name = "act_works")
    private List<Work> works = new ArrayList<>();

    private String number;

    private String numberApplication;

    @OneToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @Builder.Default
    @OneToMany(mappedBy = "act", cascade = CascadeType.REMOVE)
    private List<ActUser> actUserList = new ArrayList<>();

    @Override
    public String toString() {
        return "Act{" +
               "id=" + id +
               ", date=" + date +
               ", number='" + number + '\'' +
               ", numberApplication='" + numberApplication + '\'' +
               '}';
    }
}
