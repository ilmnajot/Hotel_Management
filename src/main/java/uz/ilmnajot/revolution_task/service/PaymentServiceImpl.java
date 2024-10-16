package uz.ilmnajot.revolution_task.service;

import org.springframework.stereotype.Service;
import uz.ilmnajot.revolution_task.entity.Payment;
import uz.ilmnajot.revolution_task.model.common.ApiResponse;
import uz.ilmnajot.revolution_task.model.request.PaymentRequest;
import uz.ilmnajot.revolution_task.repository.PaymentRepository;
import uz.ilmnajot.revolution_task.service.interfaces.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public ApiResponse makePayment(PaymentRequest request) {
        Payment payment = new Payment();
        payment.setCardHolderName(request.getCardHolderName());
        payment.setCardNumber(request.getCardNumber());
        payment.setPaymentMethod(request.getPaymentMethod());
        payment.setAmount(request.getAmount());
        return new ApiResponse(true, "success", paymentRepository.save(payment));
    }
}
