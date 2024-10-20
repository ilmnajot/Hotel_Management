package uz.ilmnajot.revolution_task.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.ilmnajot.revolution_task.payload.common.ApiResponse;
import uz.ilmnajot.revolution_task.payload.request.PaymentRequest;
import uz.ilmnajot.revolution_task.service.interfaces.PaymentService;
import uz.ilmnajot.revolution_task.validation.CheckAuthority;
@SecurityRequirement(name="Bearer")
@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @CheckAuthority("MAKE_PAYMENT")
    @PostMapping("/pay")
    public HttpEntity<ApiResponse> makePayment(@RequestBody PaymentRequest payment) {
        ApiResponse apiResponse = paymentService.makePayment(payment);
        return ResponseEntity.ok(apiResponse);
    }

    @CheckAuthority("UPDATE_PAYMENT")
    @PutMapping("/updatePayment/{paymentId}")
    public HttpEntity<ApiResponse> updatePayment(@PathVariable("paymentId") Long paymentId,
                                                 @RequestBody PaymentRequest payment) {
        ApiResponse apiResponse = paymentService.updatePayment(paymentId, payment);
        return ResponseEntity.ok(apiResponse);
    }

    @CheckAuthority("GET_PAYMENT")
    @GetMapping("/getPayment/{paymentId}")
    public HttpEntity<ApiResponse> getPayment(@PathVariable("paymentId") Long paymentId) {
        ApiResponse apiResponse = paymentService.getPayment(paymentId);
        return ResponseEntity.ok(apiResponse);
    }

    @CheckAuthority("GET_ALL_PAYMENT")
    @GetMapping("/get_all_payments")
    public HttpEntity<ApiResponse> getPayments(@RequestParam(value = "page", defaultValue = "0") int page,
                                               @RequestParam(value = "size", defaultValue = "10") int size) {
        ApiResponse apiResponse = paymentService.getPayments(page, size);
        return ResponseEntity.ok(apiResponse);
    }

    @CheckAuthority("DELETE_PAYMENT")
    @DeleteMapping("/deletePayment/{paymentId}")
    public HttpEntity<ApiResponse> deletePayment(@PathVariable("paymentId") Long paymentId) {
        ApiResponse apiResponse = paymentService.deletePayment(paymentId);
        return ResponseEntity.ok(apiResponse);
    }

}
