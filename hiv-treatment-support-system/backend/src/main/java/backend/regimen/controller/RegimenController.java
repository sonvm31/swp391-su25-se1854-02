package backend.regimen.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.regimen.dto.CreateRegimenRequest;
import backend.regimen.dto.UpdateRegimenRequest;
import backend.regimen.model.Regimen;
import backend.regimen.service.ReginmenService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/regimen")
@RequiredArgsConstructor
public class RegimenController {
    private final ReginmenService reginmenService;

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody CreateRegimenRequest request) {
        String response = reginmenService.create(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Regimen>> list() {
        List<Regimen> response = reginmenService.list();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Regimen> get(@PathVariable int id) {
        Regimen response = reginmenService.get(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable int id, @RequestBody UpdateRegimenRequest request) {
        String response = reginmenService.update(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        String response = reginmenService.delete(id);
        return ResponseEntity.ok(response);
    }
}
