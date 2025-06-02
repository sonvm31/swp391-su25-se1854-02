package backend.repository;

import backend.model.Payment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    Payment findPaymentByCheckupId(int checkupId);
}
