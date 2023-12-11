package com.example.aftas.DTOs.competition;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalTime;
@Data
    @NoArgsConstructor
    public class CompetitionDTO {
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private int numberOfParticipants;
    private String location;
    private Integer amount;
}



