package com.example.aftas.models;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;
import java.util.List;
@Entity
@Validated
@NoArgsConstructor
@Data
public class Fish {
    @Id
    private String name;
    private double averageWeight;
    @ManyToOne
    @JoinColumn(name = "level_id")
    private Level level;
    @OneToMany(mappedBy = "fish", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Hunting> huntings;


}
