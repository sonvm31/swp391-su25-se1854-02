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
@Table(name = "Regimen")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Regimen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String regimenName;
    private String components;
    private String description;
    private String indications;
    private String contradications;
    private String note;
    private boolean isDefault;
}
