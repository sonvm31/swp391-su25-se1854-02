package backend.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import backend.model.Regimen;
import backend.service.ReginmenService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/regimen")
@RequiredArgsConstructor
public class RegimenController {
    private final ReginmenService reginmenService;

    @GetMapping("/list")
    public ResponseEntity<Optional<Regimen>> getRegimenById(@RequestParam int id) {
        Optional<Regimen> response = reginmenService.getRegimenById(id);
        return ResponseEntity.ok(response);
    }

}
