package com.example.tradeintechniqueapp.database.entity;

import com.example.tradeintechniqueapp.dto.CustomUserDetails;
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
@Table(name = "act_user")
public class ActUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "act_id")
    private Act act;

    public void setUser(User user){
        this.user = user;
        this.user.getActUserList().add(this);
    }
    public void setAct(Act act){
        this.act = act;
        this.act.getActUserList().add(this);
    }

    @Override
    public String toString() {
        return "ActUser{" +
               "id=" + id +
               ", user=" + user +
               ", act=" + act +
               '}';
    }
}
