package backend.controller;

import backend.dto.CheckupHistoryResponse;
import backend.dto.UserPrivateInformationRequest;
import backend.service.CheckupScheduleService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class CheckupHistoryController {
    final private CheckupScheduleService checkupHistoryService;

    @PostMapping("/list")
    public ResponseEntity<CheckupHistoryResponse> history(
            @RequestBody UserPrivateInformationRequest request) {
        CheckupHistoryResponse response = checkupHistoryService.getCheckupHistory(request);
        return ResponseEntity.ok(response);
    }
}
