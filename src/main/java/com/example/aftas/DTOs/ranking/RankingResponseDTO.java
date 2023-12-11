package com.example.aftas.DTOs.ranking;
import lombok.Data;

@Data
    public class RankingResponseDTO {
        private Long memberId;
        private String competitionId;
        private Integer rank;
        private Integer score;
    }

