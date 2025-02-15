package com.wora.qatrat7ayat.models.entities;

import com.wora.qatrat7ayat.models.enumes.UrgencyLevel;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "requests")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class Request extends Action {
    private Float bloodVolume;

    @Column(name = "urgency_level", columnDefinition = "urgency_level")
    @Enumerated(EnumType.STRING)
    private UrgencyLevel urgencyLevel;
}
