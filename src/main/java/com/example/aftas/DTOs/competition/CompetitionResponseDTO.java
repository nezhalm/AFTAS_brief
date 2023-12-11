package com.example.aftas.DTOs.competition;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;
@Data

public class CompetitionResponseDTO {
    private String code;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private int numberOfParticipants;
    private String location;
    private Integer amount;
}
