package com.example.aftas.models;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Validated
@NoArgsConstructor
@Data
@Entity
public class Hunting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int numberOfFish;
    @ManyToOne
    @JoinColumn(name = "fish_id")
    private Fish fish;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "competition_id")
    private Competition competition;
}
