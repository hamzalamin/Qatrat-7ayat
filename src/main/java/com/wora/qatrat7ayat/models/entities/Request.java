package com.wora.qatrat7ayat.models.entities;

import com.wora.qatrat7ayat.models.enumes.UrgencyLevel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "requests")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Request extends Action {

    @NotNull
    private Float bloodVolume;

    private UrgencyLevel urgencyLevel;
}
