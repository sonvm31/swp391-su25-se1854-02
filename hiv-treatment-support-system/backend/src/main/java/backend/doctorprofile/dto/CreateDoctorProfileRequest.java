package backend.doctorprofile.dto;

public record CreateDoctorProfileRequest(
    String licenseNumber,

    String startYear,

    String qualifications,

    String background,

    String biography,
    
    int userId
){   
}
