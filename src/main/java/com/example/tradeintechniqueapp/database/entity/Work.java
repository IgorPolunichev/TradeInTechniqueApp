package com.example.tradeintechniqueapp.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@NamedEntityGraph(
        name = "load-act"
        , attributeNodes = {@NamedAttributeNode(value = "act")}
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "act_works")
public class Work {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate workDate;
    private LocalTime startWork;
    private LocalTime endWork;
    private LocalTime startLunch;
    private LocalTime endLunch;

    private String placeOfDeparture;

    private String placeOfArrival;

    private Integer mileage;
    private String workDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "act_id")
    private Act act;

    public void setAct(Act act) {
        this.act = act;

    }

    @Override
    public String toString() {

        return "Work{" +
               "workDate=" + workDate.toString() +
               ", startWork=" + startWork.toString() +
               ", endWork=" + endWork.toString() +
               ", workDescription='" + workDescription + '\'' +
               '}';
    }
}
