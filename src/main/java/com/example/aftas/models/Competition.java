package com.example.aftas.models;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.validation.annotation.Validated;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Validated
@NoArgsConstructor
@Data
public class Competition {
    @Id
    private String code;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private int numberOfParticipants;
    private String location;
    private Integer amount;
    @OneToMany(mappedBy = "competition", cascade = CascadeType.ALL)
    private List<Hunting> huntings;
}
