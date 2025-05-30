package backend.repository;

import backend.model.Payment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    public ArrayList<Payment> findPaymentByUser_id(int userID);
}
