package backend.doctorprofile.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import backend.doctorprofile.dto.CreateDoctorProfileRequest;
import backend.doctorprofile.dto.UpdateDoctorProfileRequest;
import backend.doctorprofile.model.DoctorProfile;
import backend.doctorprofile.repository.DoctorProfileRepository;
import backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class DoctorProfileService {
    @Autowired
    private final DoctorProfileRepository doctorProfileRepository;

    @Autowired
    private final UserRepository userRepository;

    // Create doctor profile
    public String create(CreateDoctorProfileRequest request) {
        var doctorProfile = DoctorProfile.builder()
                .qualifications(request.qualifications())
                .licenseNumber(request.licenseNumber())
                .background(request.background())
                .biography(request.biography())
                .startYear(request.startYear())
                .doctor(userRepository.findById(request.doctorId()).get())
                .build();
        doctorProfileRepository.save(doctorProfile);

        return "DOCTOR PROFILE CREATED SUCCESSFULLY WITH ID: " + doctorProfile.getId();
    }

    // List doctor profiles
    public List<DoctorProfile> list() {
        return doctorProfileRepository.findAll();
    }

    // Read doctor profile detail
    public DoctorProfile get(long id) {
        return doctorProfileRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "NO DOCTOR PROFILE FOUND"));
    }

    // Update doctor profile
    public String update(long id, UpdateDoctorProfileRequest request) {
        DoctorProfile doctorProfile = doctorProfileRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "NO DOCTOR PROFILE FOUND WITH ID: " + id));

        Optional.ofNullable(request.qualifications()).ifPresent(doctorProfile::setQualifications);
        Optional.ofNullable(request.licenseNumber()).ifPresent(doctorProfile::setLicenseNumber);
        Optional.ofNullable(request.background()).ifPresent(doctorProfile::setBackground);
        Optional.ofNullable(request.biography()).ifPresent(doctorProfile::setBiography);
        Optional.ofNullable(request.startYear()).ifPresent(doctorProfile::setStartYear);
        Optional.ofNullable(userRepository.findById(request.doctorId()).get()).ifPresent(doctorProfile::setDoctor);
        doctorProfileRepository.save(doctorProfile);

        return "DOCTOR PROFILE UPDATED SUCCESSFULLY WITH ID: " + id;
    }

    // Delete doctor profile
    public String delete(long id) {
        doctorProfileRepository.delete(doctorProfileRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "NO DOCTOR PROFILE FOUND WITH ID: " + id)));
        
        return "DOCTOR PROFILE DELETED SUCCESSFULLY WITH ID: " + id;
    }

    // Read doctor profile by doctor ID
    public DoctorProfile getByDoctorId(long id) {
        return doctorProfileRepository.findByDoctorId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "NO DOCTOR PROFILE FOUND"));
    }
}
