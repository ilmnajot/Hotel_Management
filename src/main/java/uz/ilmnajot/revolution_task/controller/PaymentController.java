package uz.ilmnajot.revolution_task.controller;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.ilmnajot.revolution_task.model.common.ApiResponse;
import uz.ilmnajot.revolution_task.model.request.PaymentRequest;
import uz.ilmnajot.revolution_task.service.interfaces.PaymentService;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/pay")
    public HttpEntity<ApiResponse> makePayment(@RequestBody PaymentRequest payment) {
    paymentService.makePayment(payment);
    }
}
