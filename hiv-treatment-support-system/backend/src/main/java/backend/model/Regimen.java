package backend.model;

import lombok.*;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Regimen")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Regimen {
    private int reginmenID;
    private String regimenName;
    private String components;
    private String indications;
    private String contradications;
    private String specialNote;
    private boolean isDefault;
}
