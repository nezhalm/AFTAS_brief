package com.example.aftas.controllers;
import com.example.aftas.DTOs.member.memberDTO;
import com.example.aftas.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/members")
@CrossOrigin
public class memberController {
    private final MemberService memberService;

    @Autowired
    public memberController(MemberService completion) {
        memberService = completion;
    }



//
//    @PostMapping
//    public ResponseEntity<Map<String, Object>> save( @RequestBody CompetitionDTO competitionDTO) {
//        Map<String, Object> result = new HashMap<>();
//        try{
//            result.put("competition", competitionService.create(competitionDTO));
//            result.put("message", "competition created successfully");
//            return new ResponseEntity<>(result, HttpStatus.CREATED);
//        }catch (Exception e) {
//            result.put("error", e.getMessage());
//            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
//        }
//    }

//
//    @PostMapping("/reservePlace")
//    public ResponseEntity<Map<String, Object>> reserve( @RequestBody RankingDTO rankingDTO) {
//        Map<String, Object> result = new HashMap<>();
//        try{
//            result.put("reservation", competitionService.reserve(rankingDTO));
//            result.put("message", "reservation created successfully");
//            return new ResponseEntity<>(result, HttpStatus.CREATED);
//        }catch (Exception e) {
//            result.put("error", e.getMessage());
//            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
//        }
//    }


    @GetMapping
    public ResponseEntity<Map<String, Object>> members() throws Exception {
        Map<String, Object> message = new HashMap<>();
        try{
            if(memberService.getAll().isEmpty()) {
                message.put("message", "No members found!");
                return new ResponseEntity<>(message, HttpStatus.OK);
            }
            message.put("message", "members found");
            message.put("members", memberService.getAll());
            return new ResponseEntity<>(message, HttpStatus.OK);
        }catch(Exception e){
            throw new Exception("cannot find any member");
        }
    }


    @GetMapping("/search")
    public memberDTO searchMembers(
            @RequestParam(required = false) Integer num,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String familyName) {
        return memberService.searchMembers(num, name, familyName);
    }















//    @DeleteMapping("/{id}")
//    public ResponseEntity<Map<String, Object>> delete(@PathVariable Integer id) {
//        Map<String, Object> result = new HashMap<>();
//        try {
//            if (levelService.existsById(id)) {
//                levelService.delete(id);
//                result.put("message", "Level with ID " + id + " has been deleted successfully");
//                return new ResponseEntity<>(result, HttpStatus.OK);
//            } else {
//                result.put("error", "Level with ID " + id + " does not exist");
//                return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
//            }
//        } catch (Exception e) {
//            result.put("error", e.getMessage());
//            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Map<String, Object>> update(@PathVariable Integer id, @RequestBody LevelDTO subject) {
//        Map<String, Object> result = new HashMap<>();
//        try {
//            if (levelService.existsById(id)) {
//                subject.setId(id);
//                levelService.update(subject);
//                result.put("message", "level with ID " + id + " has been updated successfully");
//                return new ResponseEntity<>(result, HttpStatus.OK);
//            } else {
//                result.put("error", "level with ID " + id + " does not exist");
//                return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
//            }
//        } catch (Exception e) {
//            result.put("error", e.getMessage());
//            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

}
