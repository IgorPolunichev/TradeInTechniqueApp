package com.example.tradeintechniqueapp.database.entity;

import com.example.tradeintechniqueapp.database.entity.audit.ActPay;
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
//    @ElementCollection
//    @CollectionTable(name = "act_works")
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "act_id")
    private List<Work> works = new ArrayList<>();

    private String number;
    private String numberApplication;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private Machine machine;
    String car;
    String licensePlate;
    String placeOfWork;
    String actDescription;
    @Enumerated(EnumType.STRING)
    ActPay actPay;

    @Builder.Default
    @OneToMany(mappedBy = "act", cascade = CascadeType.ALL)
    private List<ActUser> actUserList = new ArrayList<>();

    public void setWorks(List<Work> works) {
        this.works = works;
        for (Work work : works) {
            work.setAct(this);
        }
    }

    @Override
    public String toString() {
        return "Act{" +
               "id=" + id +
               ", date=" + date +
               ", number='" + number + '\'' +
               '}';
    }
}
