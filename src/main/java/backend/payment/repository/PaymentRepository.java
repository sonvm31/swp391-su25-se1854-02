package backend.payment.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import backend.payment.model.Payment;
import backend.schedule.model.Schedule;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByScheduleId(long scheduleId);

    List<Payment> findByStatus(String status);

    Optional<Payment> findByPaymentRef(String paymentRef);

    @Modifying
    @Query("DELETE Payment p WHERE p.schedule.id = :scheduleId")
    void deleteByScheduleId(Long scheduleId);

    void deleteBySchedule(Schedule schedule);

    void deleteById(long id);
}
