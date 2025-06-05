package backend.doctorprofile.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import backend.doctorprofile.dto.CreateDoctorProfileRequest;
import backend.doctorprofile.dto.UpdateDoctorProfileRequest;
import backend.doctorprofile.model.DoctorProfile;
import backend.doctorprofile.repository.DoctorProfileRepository;
import backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DoctorProfileService {
    private final DoctorProfileRepository doctorProfileRepository;
    private final UserRepository userRepository;

    // Tạo hồ sơ bác sĩ
    public String create(CreateDoctorProfileRequest request) {
        var doctorProfile = DoctorProfile.builder()
        .qualifications(request.qualifications())
        .licenseNumber(request.licenseNumber())
        .background(request.background())
        .biography(request.biography())
        .startYear(request.startYear())
        .user(userRepository.findById(request.userId()).get())
        .build();
        doctorProfileRepository.save(doctorProfile);

        return "Doctor profile created successfully with ID: " + doctorProfile.getId() + ".";
    }

    // Xem danh sách hồ sơ bác sĩ 
    public List<DoctorProfile> list() {
        List<DoctorProfile> doctorProfiles = doctorProfileRepository.findAll();
        if (doctorProfiles.isEmpty()) 
            throw new RuntimeException("No doctor profile found");

        return doctorProfiles;
    }

    // Xem chi tiết hồ sơ của bác sĩ 
    public DoctorProfile get(int id) {
        return doctorProfileRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Doctor profile not found with ID: " + id + "."));
    }

    // Chỉnh sửa hồ sơ bác sĩ
    public String update(int id, UpdateDoctorProfileRequest request) {
        DoctorProfile doctorProfile = doctorProfileRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Doctor profile not found with ID: " + id + "."));
        
        Optional.ofNullable(request.qualifications()).ifPresent(doctorProfile::setQualifications);
        Optional.ofNullable(request.licenseNumber()).ifPresent(doctorProfile::setLicenseNumber);
        Optional.ofNullable(request.background()).ifPresent(doctorProfile::setBackground);
        Optional.ofNullable(request.biography()).ifPresent(doctorProfile::setBiography);
        Optional.ofNullable(request.startYear()).ifPresent(doctorProfile::setStartYear);
        Optional.ofNullable(userRepository.findById(id).get()).ifPresent(doctorProfile::setUser);
        doctorProfileRepository.save(doctorProfile);

        return "Doctor profile updated successfully with ID: " + id + ".";
    }

    // Xóa hồ sơ bác sĩ
    public String delete(int id) {
        doctorProfileRepository.delete(doctorProfileRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Doctor profile not found with ID: " + id + ".")));
        
        return "Doctor profile deleted successfully with ID: " + id + ".";
    }
}
