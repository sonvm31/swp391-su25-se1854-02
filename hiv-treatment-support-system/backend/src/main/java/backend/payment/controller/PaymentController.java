package backend.payment.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.payment.dto.CreatePaymentRequest;
import backend.payment.model.Payment;
import backend.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody CreatePaymentRequest request) {
        String response = paymentService.create(request);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/list")
    public ResponseEntity<List<Payment>> list() {
        List<Payment> response = paymentService.list();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> list(@PathVariable int id) {
        Payment response = paymentService.get(id);
        return ResponseEntity.ok(response);
    }
}
