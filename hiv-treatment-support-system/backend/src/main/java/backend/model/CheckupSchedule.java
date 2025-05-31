package backend.model;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "checkup_schedule")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class CheckupSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String type;
    private String status;
    private Date date;
    private LocalTime slot;
    private int userId;
    private int payId;
    @ManyToOne
    @JoinColumn(name = "id", referencedColumnName = "userId")
    private User user;
    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "paymentId")
    private Payment payment;
}