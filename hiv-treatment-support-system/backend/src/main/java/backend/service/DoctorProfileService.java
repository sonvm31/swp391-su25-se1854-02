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
        return doctorProfileRepository.findById(id);
    }
}
