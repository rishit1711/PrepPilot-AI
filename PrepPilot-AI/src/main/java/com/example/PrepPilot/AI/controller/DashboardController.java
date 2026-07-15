package com.example.PrepPilot.AI.controller;

import com.example.PrepPilot.AI.dto.DashboardResponse;
import com.example.PrepPilot.AI.entity.User;
import com.example.PrepPilot.AI.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping
    public ResponseEntity<DashboardResponse> getDashboard(
            @AuthenticationPrincipal User user) {

        return ResponseEntity.ok(dashboardService.getDashboard(user));
    }
}