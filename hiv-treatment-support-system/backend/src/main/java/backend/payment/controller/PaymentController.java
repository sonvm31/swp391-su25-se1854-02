package backend.payment.controller;

import java.util.List;
import java.util.Map;

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

    @PostMapping()
    public ResponseEntity<Map<String, String>> create(@RequestBody CreatePaymentRequest request) {
        return ResponseEntity.ok(Map.of("message", paymentService.create(request)));
    }
    @GetMapping()
    public ResponseEntity<List<Payment>> list() {
        return ResponseEntity.ok(paymentService.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> list(@PathVariable int id) {
        return ResponseEntity.ok(paymentService.get(id));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Payment>> getByStatus(@PathVariable String status) {
        return ResponseEntity.ok(paymentService.getByStatus(status));
    }
}
