package backend.doctorprofile.dto;

import java.time.LocalDate;

public record CreateDoctorProfileRequest(
    String qualifications,
    String licenseNumber,
    String background,
    String biography,
    LocalDate startYear,
    int userId
){   
}
