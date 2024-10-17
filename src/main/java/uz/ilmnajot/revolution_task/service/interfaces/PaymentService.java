package uz.ilmnajot.revolution_task.service.interfaces;

import uz.ilmnajot.revolution_task.payload.common.ApiResponse;
import uz.ilmnajot.revolution_task.payload.request.PaymentRequest;

public interface PaymentService {
    ApiResponse makePayment(PaymentRequest payment);

    ApiResponse updatePayment(Long paymentId, PaymentRequest payment);

    ApiResponse getPayment(Long paymentId);

    ApiResponse getPayments(int page, int size);

    ApiResponse deletePayment(Long paymentId);
}
