package backend.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import backend.model.DoctorProfile;
import backend.repository.DoctorProfileRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DoctorProfileService {
    private final DoctorProfileRepository doctorProfileRepository;

    public Optional<DoctorProfile> getDoctorProfileById(int id) {
        if (doctorProfileRepository.existsById(id)) {
            throw new RuntimeException("Doctor profile not found with ID: " + id + ".");
        }
        
        return doctorProfileRepository.findById(id);
    }
}
