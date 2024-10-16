package uz.ilmnajot.revolution_task.service.interfaces;

import uz.ilmnajot.revolution_task.model.common.ApiResponse;
import uz.ilmnajot.revolution_task.model.request.PaymentRequest;

public interface PaymentService {
    ApiResponse makePayment(PaymentRequest payment);
}
