package com.example.aftas.models;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;
import java.io.Serializable;
@Validated
@NoArgsConstructor
@Data
@Embeddable
public class MemberCompetitionKey  implements Serializable {

        @Column(name = "member_id")
        private Integer memberId;

        @Column(name = "competition_id")
        private String competitionId;

    }


