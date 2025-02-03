package com.scaler.demo.project.dto.model.mappedSuperClass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "ms_instrucrtor")
public class Instructor extends User{
    private String specialisation;
}
