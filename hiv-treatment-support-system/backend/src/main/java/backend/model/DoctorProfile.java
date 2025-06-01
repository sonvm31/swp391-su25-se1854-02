package backend.model;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "doctor_profile")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String qualifications;
    private String licenseNumber;
    private String biography;
    private Date startYear;
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User user;
}
