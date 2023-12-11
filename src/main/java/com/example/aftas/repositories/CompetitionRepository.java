package com.example.aftas.repositories;
import com.example.aftas.models.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, String> {
    boolean existsByLocationAndDate(String location, LocalDate date);
    Competition findByCode(String code);


}