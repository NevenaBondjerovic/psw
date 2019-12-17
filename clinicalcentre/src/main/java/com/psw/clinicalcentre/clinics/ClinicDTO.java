package com.psw.clinicalcentre.clinics;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ClinicDTO {

    @NotNull
    private Integer id;

    @NotBlank
    private String name;

    @NotBlank
    private String address;

    @Max(5)
    private Integer score;

    public ClinicDTO(@NotNull Integer id, @NotBlank String name, @NotBlank String address, Integer score) {
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
