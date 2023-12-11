package com.example.aftas.models;

import com.example.aftas.enums.IdentityDocumentType;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Validated
@NoArgsConstructor
@Data
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer num;
    private String name;
    private String familyName;
    private LocalDate accessionDate;
    private String nationality;
    @Enumerated(EnumType.STRING)
    private IdentityDocumentType identityDocument;
    private String identityNumber;
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Hunting> huntings;
}