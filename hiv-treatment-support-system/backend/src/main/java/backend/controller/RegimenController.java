package backend.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.model.Regimen;
import backend.service.ReginmenService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/regimen")
@RequiredArgsConstructor
public class RegimenController {
    private final ReginmenService reginmenService;

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Regimen>> getRegimenById(@PathVariable int id) {
        Optional<Regimen> response = reginmenService.getRegimenById(id);
        return ResponseEntity.ok(response);
    }
}
