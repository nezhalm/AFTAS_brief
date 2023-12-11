package com.example.aftas.repositories;
import com.example.aftas.DTOs.member.memberDTO;
import com.example.aftas.models.Competition;
import com.example.aftas.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public interface MemberRepository extends JpaRepository<Member, String> {
    Member findByNum(Integer num);

    Member findByNameContaining(String name);

    Member findByFamilyNameContaining(String familyName);

    List<Member> findTop3ByCompetitionOrderByScoreDesc(Competition competition);


}
