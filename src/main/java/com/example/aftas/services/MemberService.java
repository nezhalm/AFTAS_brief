package com.example.aftas.services;
import com.example.aftas.DTOs.member.memberDTO;
import com.example.aftas.models.Member;
import com.example.aftas.repositories.MemberRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Optional;
import org.springframework.stereotype.Service;


@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public MemberService(MemberRepository repository,ModelMapper mapper) {

        memberRepository = repository;
        modelMapper = mapper;
    }

    public List<memberDTO> getAll() {
        return Arrays.asList(modelMapper.map(memberRepository.findAll(), memberDTO[].class));
    }


    public memberDTO searchMembers(Integer num, String name, String familyName) {
        Member member;

        if (num != null) {
            member = memberRepository.findByNum(num);
        } else if (name != null) {
            member = memberRepository.findByNameContaining(name);
        } else if (familyName != null) {
            member = memberRepository.findByFamilyNameContaining(familyName);
        } else {
            return null; // Aucun critère de recherche spécifié, renvoyer null ou un objet DTO par défaut
        }

        return (member != null) ? modelMapper.map(member, memberDTO.class) : null;
    }


}