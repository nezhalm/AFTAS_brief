package com.example.aftas.DTOs.member;

import com.example.aftas.enums.IdentityDocumentType;
import lombok.Data;

import java.time.LocalDate;
@Data
public class memberDTO {
    private Integer num;
    private String name;
    private String familyName;
    private LocalDate accessionDate;
    private String nationality;
    private IdentityDocumentType identityDocument;
    private String identityNumber;
//    private List<HuntingDTO> huntings;
}
