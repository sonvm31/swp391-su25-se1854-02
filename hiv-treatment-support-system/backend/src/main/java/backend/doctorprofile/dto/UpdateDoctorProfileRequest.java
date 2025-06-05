package backend.doctorprofile.dto;

public record UpdateDoctorProfileRequest(
    String licenseNumber,

    String startYear,

    String qualifications,

    String background,

    String biography,

    int userId
) {
}
