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

        return "Payment created successfully with ID: "+ payment.getId() + ".";
    } 

    // Xem danh sách thanh toán
    public List<Payment> list() {
        List<Payment> payments = paymentRepository.findAll();
        if (payments.isEmpty()) 
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NO PAYMENT FOUND");

        return paymentRepository.findAll();
    }

    // Xem chi tiết thanh toán 
    public Payment get(int id) {
        return paymentRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "NO PAYMENT FOUND WITH ID: " + id));
    }

    // Xem danh sách thanh toán theo trạng thái
    public List<Payment> getByStatus(String status) {
        List<Payment> paymentList = paymentRepository.findByStatus(status);
        if (paymentList.isEmpty()) 
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NO PAYMENT FOUND WITH STATUS: " + status);
        
        return paymentList;
    }
}