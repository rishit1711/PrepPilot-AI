package com.example.PrepPilot.AI.controller;

import com.example.PrepPilot.AI.dto.BluePrintRequest;
import com.example.PrepPilot.AI.dto.BluePrintResponse;
import com.example.PrepPilot.AI.service.InterviewBluePrintService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class InterviewBluePrintController {
    private final InterviewBluePrintService bluePrintService;

    // Kam hai to just give a clear overview of what section to be targetted in interview

    @PostMapping(path = "/blueprint")
    public ResponseEntity<BluePrintResponse> blueprint(@RequestBody BluePrintRequest request){
        return ResponseEntity.ok(bluePrintService.GenerateBluePrint(request));

    }

}
