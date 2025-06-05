package backend.schedule.model;

import java.time.LocalDate;
import java.time.LocalTime;

import backend.user.model.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "schedule")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String type;

    private String status;

    private LocalDate date;
    
    private LocalTime slot;

    @OneToOne
    @JoinColumn(name = "doctorId", referencedColumnName = "id")
    private User doctor;
    
    @ManyToOne
    @JoinColumn(name = "patientId", referencedColumnName = "id")
    private User patient;
}