package com.scaler.demo.project.dto.model.tableperclass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "tp_instrucrtor")
public class Instructor extends User {
    private String specialisation;
}
