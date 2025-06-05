package backend.testresult.model;

import java.time.LocalDateTime;

import backend.healthrecord.model.HealthRecord;
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
@Table(name = "test_result")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String type;

    private String result;
    
    private String unit;

    @Column(columnDefinition = "NVARCHAR")
    private String note;
    
    private LocalDateTime expectedResultTime;
    
    private LocalDateTime actualResultTime;
    
    @ManyToOne
    @JoinColumn(name = "healthRecordId", referencedColumnName = "id")
    private HealthRecord healthRecord;
}
