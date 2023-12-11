package com.example.aftas.controllers;
import com.example.aftas.DTOs.hunting.huntingDTO;
import com.example.aftas.DTOs.member.memberDTO;
import com.example.aftas.services.HuntingService;
import com.example.aftas.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;


@RestController
@RequestMapping("/api/hunting")
@CrossOrigin
public class huntingController {
    private final HuntingService huntingService;
    private final MemberService memberService;


    @Autowired
    public huntingController(HuntingService hunting, MemberService mService) {
        huntingService = hunting;
        memberService = mService;
    }




    @PostMapping
    public ResponseEntity<Map<String, Object>> save( @RequestBody huntingDTO huntingDTO) {
        Map<String, Object> result = new HashMap<>();
        try{
            result.put("score", huntingService.create(huntingDTO));
            result.put("message", "hunting created successfully");
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }catch (Exception e) {
            result.put("error", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
    }





    @GetMapping
    public ResponseEntity<Map<String, Object>> huntings() throws Exception {
        Map<String, Object> message = new HashMap<>();
        try{
            if(huntingService.getAll().isEmpty()) {
                message.put("message", "No huntings found!");
                return new ResponseEntity<>(message, HttpStatus.OK);
            }
            message.put("message", "huntings found");
            message.put("huntings", huntingService.getAll());
            return new ResponseEntity<>(message, HttpStatus.OK);
        }catch(Exception e){
            throw new Exception("cannot find any level");
        }
    }



    @GetMapping("/topThree/{competitionId}")
    public ResponseEntity<Map<String, Object>> getTopThreeInCompetition(@PathVariable String competitionId) {
        Map<String, Object> message = new HashMap<>();
        try {
            List<memberDTO> topThreeMembers = huntingService.getTopThreeInCompetition(competitionId);

            if (topThreeMembers.isEmpty()) {
                message.put("message", "No huntings found for the given competition!");
                return new ResponseEntity<>(message, HttpStatus.OK);
            }

            message.put("message", "Top three huntings found");
            message.put("topThreeHuntings", topThreeMembers);
            return new ResponseEntity<>(message, HttpStatus.OK);
        } catch (Exception e) {
            message.put("error", "Internal Server Error");
            return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
