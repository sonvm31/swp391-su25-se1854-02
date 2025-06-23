package backend.doctorprofile.model;

import backend.user.model.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "doctor_profile")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DoctorProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(columnDefinition = "VARCHAR(100)")
    private String licenseNumber;
       
    @Column(columnDefinition = "VARCHAR(100)")
    private String startYear;

    @Column(columnDefinition = "NVARCHAR(255)")
    private String qualifications;

    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String biography;

    @Column(columnDefinition = "NVARCHAR(255)")
    private String background;

    @ManyToOne
    @JoinColumn(name = "doctorId", referencedColumnName = "id")
    private User doctor;
}
