package backend.payment.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.payment.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    Optional<Payment> findByScheduleId(int scheduleId);

    List<Payment> findByStatus(String status);
}
