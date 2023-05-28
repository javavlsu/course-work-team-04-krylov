package com.polyclinic.mis.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class ExaminationCabinet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "Поле Название кабинета не может быть пустым")
    private String name;
    @NotNull(message = "Поле Номер кабинета не может быть пустым")
    private String number;
    private String schedule;

    public String ReturnNameAndNumber(){
        return name + " " + number;
    }
}
