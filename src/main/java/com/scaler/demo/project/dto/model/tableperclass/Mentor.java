package com.scaler.demo.project.dto.model.tableperclass;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "tp_mentor")
public class Mentor extends User {
    private Double overallRating;
}
