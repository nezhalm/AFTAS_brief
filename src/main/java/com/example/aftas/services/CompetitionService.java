package com.example.aftas.services;
import com.example.aftas.DTOs.competition.CompetitionDTO;
import com.example.aftas.DTOs.competition.CompetitionResponseDTO;
import com.example.aftas.DTOs.ranking.RankingDTO;
import com.example.aftas.DTOs.ranking.RankingResponseDTO;
import com.example.aftas.models.Competition;
import com.example.aftas.models.Ranking;
import com.example.aftas.repositories.CompetitionRepository;
import com.example.aftas.repositories.RankingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;


@Service
public class CompetitionService {
    private final CompetitionRepository competitionRepository;
    private final RankingRepository rankingRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CompetitionService(RankingRepository RRepository, CompetitionRepository repository, ModelMapper mapper) {
        rankingRepository = RRepository;
        competitionRepository = repository;
        modelMapper = mapper;
    }

    public boolean create(CompetitionDTO competitionDTO) {
        // Vérifier si une compétition avec la même location et la même date existe déjà
        if (competitionRepository.existsByLocationAndDate(competitionDTO.getLocation(), competitionDTO.getDate())) {
            throw new RuntimeException("Une compétition avec la même location et la même date existe déjà.");
        }

        // Si la vérification est réussie, créer la compétition
        Competition competition = modelMapper.map(competitionDTO, Competition.class);
        String generatedCode = generateCode(competitionDTO.getDate(), competitionDTO.getLocation());

        if (isValidCode(generatedCode)) {
            competition.setCode(generatedCode);
            competitionRepository.save(competition);
            return true;
        } else {
            return false;
        }
    }


    public List<CompetitionResponseDTO> getAll() {
        return Arrays.asList(modelMapper.map(competitionRepository.findAll(), CompetitionResponseDTO[].class));
    }

    public RankingResponseDTO reserve(RankingDTO rankingDTO) {
        boolean valide = validateCompetitionDates(rankingDTO.getCompetitionId());
        if (valide) {
            Ranking ranking = modelMapper.map(rankingDTO, Ranking.class);
            rankingRepository.save(ranking);
            return null;
        } else {
            throw new RuntimeException("La date n'est pas valide pour cette compétition");
        }
    }



    private  boolean validateCompetitionDates(String competitionId) {
        Competition competition = competitionRepository.findById(competitionId).orElse(null);
        if (competition != null) {
            LocalDate inscriptionDate = LocalDate.now();
            LocalDate competitionStartDate = competition.getDate();
            long daysBetween = ChronoUnit.DAYS.between(inscriptionDate, competitionStartDate);
            return Math.abs(daysBetween) == 1; // Retourne true si la différence est égale à 1 jour, sinon false
        } else {
            return false;
        }
    }

    private String generateCode(LocalDate date, String location) {
        String formattedDate = date.format(DateTimeFormatter.ofPattern("dd-MM-yy"));
        String locationCode = location.substring(0, Math.min(location.length(), 3)).toLowerCase();
        return locationCode + "-" + formattedDate;
    }
    private boolean isValidCode(String code) {
        String pattern = "^[a-z]{3}-\\d{2}-\\d{2}-\\d{2}$";
        return Pattern.matches(pattern, code);
    }







}