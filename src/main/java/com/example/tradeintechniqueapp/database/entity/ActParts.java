package com.example.tradeintechniqueapp.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ActParts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "act_id")
    private Act act;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "part_id")
    private Part part;

    private Integer count;

    @Enumerated(EnumType.STRING)
    private Owner owner;
    public void setAct(Act act) {
        this.act = act;
        this.act.getActParts().add(this);
    }

    public void setPart(Part part) {
        this.part = part;
        this.part.getActParts().add(this);
    }

    @Override
    public String toString() {
        return "ActParts{" +
               "id=" + id +
               ", count=" + count +
               ", owner=" + owner +
               '}';
    }

}
