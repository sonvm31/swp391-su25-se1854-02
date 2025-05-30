package backend.model;

import lombok.*;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "checkup_schedule")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class CheckupSchedule {
    private int checkupID;
    private String type;
    private Date date;
    private String slot;
    private boolean status;
    private String userID;
    private String payID;
}