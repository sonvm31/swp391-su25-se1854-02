package backend.payment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import backend.payment.dto.CreatePaymentRequest;
import backend.payment.model.Payment;
import backend.payment.repository.PaymentRepository;
import backend.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentService {
    @Autowired
    private final PaymentRepository paymentRepository;

    @Autowired
    private final ScheduleRepository checkupScheduleRepository;
    
    // Tạo thanh toán
    public String create(CreatePaymentRequest request) {
        Payment payment = Payment.builder()
            .name(request.name())
            .account(request.account())
            .description(request.description())
            .amount(request.amount())
            .schedule(checkupScheduleRepository.findById(request.scheduleId()).get())
            .build();
        paymentRepository.save(payment);

        return "PAYMENT CREATED SUCCESSFULLY WITH ID: "+ payment.getId();
    } 

    // Xem danh sách thanh toán
    public List<Payment> list() {
        return paymentRepository.findAll();
    }

    // Xem chi tiết thanh toán 
    public Payment get(long id) {
        return paymentRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "NO PAYMENT FOUND WITH ID: " + id));
    }

    // Xem danh sách thanh toán theo trạng thái
    public List<Payment> getByStatus(String status) {
        return paymentRepository.findByStatus(status);
    }
}