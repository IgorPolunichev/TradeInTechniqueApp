package com.example.tradeintechniqueapp.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class Work {

    private LocalDate workDate;

    private LocalTime startWork;

    private LocalTime endWork;
    private String workDescription;

    @Override
    public String toString() {
        return "Work{" +
               "workDate=" + workDate +
               ", startWork=" + startWork +
               ", endWork=" + endWork +
               ", workDescription='" + workDescription + '\'' +
               '}';
    }
}
