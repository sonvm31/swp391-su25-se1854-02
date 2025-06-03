package backend.doctorprofile.controller;

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
@RequestMapping("/api/doctor")
@RequiredArgsConstructor
public class DoctorProfileController {
    private final DoctorProfileService doctorProfileService;

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody CreateDoctorProfileRequest request) {
        String response = doctorProfileService.create(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{doctorId}")
    public ResponseEntity<DoctorProfile> get(@PathVariable int doctorId) {
        DoctorProfile response = doctorProfileService.get(doctorId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable int id, @RequestBody UpdateDoctorProfileRequest request) {
        String response = doctorProfileService.update(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        String response = doctorProfileService.delete(id);
        return ResponseEntity.ok(response);
    }
}
