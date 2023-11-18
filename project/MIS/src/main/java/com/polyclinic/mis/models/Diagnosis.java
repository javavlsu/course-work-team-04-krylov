package com.polyclinic.mis.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor
public class Diagnosis {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "Поле Код не может быть пустым")
    private String id;
    @NotNull(message = "Поле Описание не может быть пустым")
    private String description;

    public Diagnosis(String id, String description) {
        this.id = id;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Diagnosis{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public String ReturnIdAndDescription()
    {
            return id + " " + description;
    }

}
