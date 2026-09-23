package com.example.PrepPilot.AI.controller;

import com.example.PrepPilot.AI.dto.VerificationQuestionResponse;
import com.example.PrepPilot.AI.service.ClaimVerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/claim-verifcation")
public class ClaimVerificationController {
    private final ClaimVerificationService claimVerificationService;
    @PostMapping("/{claimId}/question")
    public String generateQuestion(
            @PathVariable Long claimId) {

        return claimVerificationService.generateQuestion(claimId);
    }



}
