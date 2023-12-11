package com.example.aftas.DTOs.ranking;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
    public class RankingDTO {
        @NotNull(message = "Member ID is required")
        private Long memberId;

        @NotNull(message = "Competition ID is required")
        private String competitionId;

    @NotNull(message = "Rank is required")
    @Min(value = 0, message = "Rank must not be negative")
    private Integer rank;

    @NotNull(message = "Score is required")
    @Min(value = 0, message = "Score must not be negative")
    private Integer score;
    }

