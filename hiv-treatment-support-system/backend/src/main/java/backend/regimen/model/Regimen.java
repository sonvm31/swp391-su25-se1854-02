package backend.regimen.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Regimen")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Regimen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String regimenName;
    
    private String components;

    @Column(columnDefinition = "NVARCHAR")
    private String description;

    @Column(columnDefinition = "NVARCHAR")
    private String indications;

    @Column(columnDefinition = "NVARCHAR")
    private String contradications;

    @Column(columnDefinition = "NVARCHAR")
    private String note;
}
