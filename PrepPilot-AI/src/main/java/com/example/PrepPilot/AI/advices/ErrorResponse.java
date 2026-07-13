package com.example.PrepPilot.AI.advices;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponse {
    private LocalDateTime timeStamp;
    private Integer status;
    private String message;
    private String path;
    private String error;
}
