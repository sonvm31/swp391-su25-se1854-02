package backend.model;

import lombok.*;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "test_result")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class TestResult {
    private int resultID;
    private String type;
    private Date dateOfTest;
    private String result;
    private String unit;
    private String note;
}
