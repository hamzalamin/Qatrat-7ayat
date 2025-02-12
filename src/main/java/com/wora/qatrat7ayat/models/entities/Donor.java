package com.wora.qatrat7ayat.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "donors")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Donor extends Action {
    @NotBlank
    private String availabilityPeriod;

}
