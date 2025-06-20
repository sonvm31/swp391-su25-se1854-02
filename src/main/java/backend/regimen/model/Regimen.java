package backend.regimen.model;

import backend.user.model.User;
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
@Table(name = "regimen")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Regimen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(columnDefinition = "NVARCHAR(255)")
    private String components;

    @Column(columnDefinition = "NVARCHAR(255)")
    private String regimenName;
    
    @Column(columnDefinition = "NVARCHAR(MAX)")
    private String description;

    @Column(columnDefinition = "NVARCHAR(255)")
    private String indications;

    @Column(columnDefinition = "NVARCHAR(255)")
    private String contradications;

    @ManyToOne
    @JoinColumn(name = "doctorId", referencedColumnName = "id")
    private User user;
}
