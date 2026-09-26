package com.example.PrepPilot.AI.controller;

import com.example.PrepPilot.AI.dto.ClaimAnswerEvaluation;
import com.example.PrepPilot.AI.dto.ClaimAnswerRequest;
import com.example.PrepPilot.AI.dto.VerificationQuestionResponse;
import com.example.PrepPilot.AI.service.ClaimVerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/claim-verifcation")
public class ClaimVerificationController {
    private final ClaimVerificationService claimVerificationService;
    @PostMapping("/{claimId}/question")
    public VerificationQuestionResponse generateQuestion(
            @PathVariable Long claimId) {

        return claimVerificationService.generateQuestion(claimId);
    }

    @PostMapping("/{claimId}/question/answer")
    public ResponseEntity<ClaimAnswerEvaluation> evaluate(@RequestBody ClaimAnswerRequest answerRequest){
        return ResponseEntity.ok().body(claimVerificationService.evaluateAnswer(answerRequest));
    }



}
