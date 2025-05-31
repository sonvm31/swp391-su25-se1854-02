package backend.model;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "checkup_record")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class CheckupRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int recordID;
    private String roomCode;
    private String insuranceNumber;
    private String hivStatus;
    private String bloodType;
    private String note;
    private float weight;
    private float height;
    private boolean isAnonymous;
    private boolean isFinishedTreatment;
    private int checkupID;
    private int regimenID;
    private int resultID;
}