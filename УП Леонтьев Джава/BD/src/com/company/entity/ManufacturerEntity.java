package com.company.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.Objects;

@AllArgsConstructor
@Data
public class ManufacturerEntity {
    private int ID;
    private String name;
    private Date startDate;

    public ManufacturerEntity(String name, Date startDate) {
        this.ID = -1;
        this.name = name;
        this.startDate = startDate;
    }
}
