package com.psw.clinicalcentre.clinics;

import javax.persistence.*;
import javax.validation.constraints.Max;

@Entity
@Table(name = "CLINICS")
public class Clinic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
    private String address;

    @Max(5)
    private Integer score;

    public Clinic() {
    }

    public Clinic(Integer id, String name, String address, @Max(5) Integer score) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.score = score;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public Integer getScore() {
        return score;
    }
}
