package backend.model;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
    private int id;
    private String roomCode;
    private String insuranceNumber;
    private String hivStatus;
    private String bloodType;
    private String note;
    private float weight;
    private float height;
    private boolean isAnonymous;
    private boolean isFinishedTreatment;
    private int checkupId;
    private int regimenId;
    private int resultId;
    @ManyToOne
    @JoinColumn(name = "id", referencedColumnName = "checkupId")
    private CheckupSchedule checkup;
    @ManyToOne
    @JoinColumn(name = "id", referencedColumnName = "regimenId")
    private Regimen regimen;
    @OneToMany
    @JoinColumn(name = "id", referencedColumnName = "resultId")
    private TestResult test;
}