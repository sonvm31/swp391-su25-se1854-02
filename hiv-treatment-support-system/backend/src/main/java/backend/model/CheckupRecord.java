package backend.model;

import lombok.*;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "checkup_record")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class CheckupRecord {
    private int recordID;
    private String roomCode;
    private Date dateOfTest;
    private boolean hivStatus;
    private Date dateOfResult;
    private String bloodType;
    private float height;
    private float weight;
    private String insuranceNumber;
    private String note;
    private boolean isAnonymous;
    private boolean isFinishedTreatment;
    private String checkupID;
    private String regimenID;
    private String resultID;
}