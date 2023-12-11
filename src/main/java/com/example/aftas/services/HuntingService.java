package com.example.aftas.services;
import com.example.aftas.DTOs.hunting.huntingDTO;
import com.example.aftas.DTOs.hunting.huntingResponseDTO;
import com.example.aftas.DTOs.member.memberDTO;
import com.example.aftas.models.*;
import com.example.aftas.repositories.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HuntingService {
    private final HuntingRepository huntingRepository;
    private final MemberRepository memberRepository;
    private final RankingRepository rankingRepository;
    private final CompetitionRepository competitionRepository;
    private final FishRepository fishRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public HuntingService(RankingRepository rrepository,CompetitionRepository crepository,MemberRepository mrepository,FishRepository frepository,HuntingRepository repository,ModelMapper mapper) {
        fishRepository = frepository;
        rankingRepository = rrepository;
        huntingRepository = repository;
        memberRepository = mrepository;
        competitionRepository = crepository;
        modelMapper = mapper;
    }

    public List<huntingResponseDTO> getAll() {
        return Arrays.asList(modelMapper.map(huntingRepository.findAll(), huntingResponseDTO[].class));
    }

    public int create(huntingDTO huntingDTO) {
        Fish fish = fishRepository.findByName(huntingDTO.getFishId());
        if (fish == null) {
            throw new RuntimeException("Fish not found with ID: " + huntingDTO.getFishId());
        }
        Level level = fish.getLevel();
        if (level == null) {
            throw new RuntimeException("Level not found for Fish with ID: " + fish.getName());
        }
        Hunting existingHunting = huntingRepository.findByFishAndMemberAndCompetition(
                fish,
                memberRepository.findByNum(huntingDTO.getMemberId()),
                competitionRepository.findByCode(huntingDTO.getCompetitionId())
        );
        int points = level.getPoints();
        int score = huntingDTO.getNumberOfFish() * points;
        if (existingHunting != null) {
            existingHunting.setNumberOfFish(existingHunting.getNumberOfFish() + huntingDTO.getNumberOfFish());
            huntingRepository.save(existingHunting);
        } else {
            Hunting newHunting = modelMapper.map(huntingDTO, Hunting.class);
            newHunting.setNumberOfFish(huntingDTO.getNumberOfFish());
            newHunting.setFish(fish);
            newHunting.setMember(memberRepository.findByNum(huntingDTO.getMemberId()));
            newHunting.setCompetition(competitionRepository.findById(huntingDTO.getCompetitionId()).orElse(null));
            huntingRepository.save(newHunting);
        }
        updateRanking(huntingDTO.getCompetitionId(), huntingDTO.getMemberId(), score);
        return score;
    }


    private void updateRanking(String competitionId, Integer memberId, int score) {
        Ranking ranking = rankingRepository.findById_CompetitionIdAndId_MemberId(competitionId, memberId);
        ranking.setScore(ranking.getScore() + score);
        rankingRepository.save(ranking);
    }

    public List<memberDTO> getTopThreeInCompetition(String competitionId) {
        Competition competition = competitionRepository.findByCode(competitionId);
        List<Member> topThreeMembers = memberRepository.findTop3ByCompetitionOrderByScoreDesc(competition);
        return topThreeMembers.stream()
                .map(member -> modelMapper.map(member, memberDTO.class))
                .collect(Collectors.toList());
    }
}