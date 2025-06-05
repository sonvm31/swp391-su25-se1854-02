package backend.doctorprofile.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.doctorprofile.dto.CreateDoctorProfileRequest;
import backend.doctorprofile.dto.UpdateDoctorProfileRequest;
import backend.doctorprofile.model.DoctorProfile;
import backend.doctorprofile.service.DoctorProfileService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/doctor-profile")
@RequiredArgsConstructor
public class DoctorProfileController {
    private final DoctorProfileService doctorProfileService;

    @PostMapping()
    public ResponseEntity<Map<String, String>> create(@RequestBody CreateDoctorProfileRequest request) {
        return ResponseEntity.ok(Map.of("message", doctorProfileService.create(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> update(@PathVariable int id, @RequestBody UpdateDoctorProfileRequest request) {
        return ResponseEntity.ok(Map.of("message", doctorProfileService.update(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable int id) {
        return ResponseEntity.ok(Map.of("message", doctorProfileService.delete(id)));
    }
   
    @GetMapping("/doctor-id/{doctorId}")
    public ResponseEntity<DoctorProfile> get(@PathVariable int doctorId) {
        return ResponseEntity.ok(doctorProfileService.get(doctorId));
    }
}
