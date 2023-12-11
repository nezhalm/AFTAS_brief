package com.example.aftas.DTOs.hunting;
import com.example.aftas.DTOs.competition.CompetitionDTO;
import com.example.aftas.DTOs.fish.fishDTO;
import com.example.aftas.DTOs.member.memberDTO;
import lombok.Data;

@Data

public class huntingResponseDTO {
    private Integer id;
    private int numberOfFish;
    private fishDTO fish;
    private memberDTO member;
    private CompetitionDTO competition;
}
