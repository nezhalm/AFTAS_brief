package com.example.aftas.repositories;

import com.example.aftas.models.Fish;
import com.example.aftas.models.Hunting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository

public interface FishRepository extends JpaRepository<Fish, String> {
    Fish findByName(String fishName);
}
