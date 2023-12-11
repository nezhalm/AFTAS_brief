package com.example.aftas.models;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;



@Entity
@Validated
@NoArgsConstructor
@Data
public class Ranking {
    @EmbeddedId
    private MemberCompetitionKey id;
    private Integer rank;
    private Integer score;
}
