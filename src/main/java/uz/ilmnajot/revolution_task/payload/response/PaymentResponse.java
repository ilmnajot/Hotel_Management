package uz.ilmnajot.revolution_task.payload.response;

import lombok.Getter;
import lombok.Setter;
import uz.ilmnajot.revolution_task.entity.Payment;
import uz.ilmnajot.revolution_task.enums.PaymentMethod;
import uz.ilmnajot.revolution_task.enums.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
public class PaymentResponse {

    private String cardHolderName;
    private String cardNumber;
    private PaymentMethod paymentMethod;
    private LocalDate paymentDate;
    private BigDecimal amount;
    private PaymentStatus status;
    private Long reservationId;


    public PaymentResponse toPaymentResponse(Payment payment) {
        PaymentResponse response = new PaymentResponse();
        response.setCardHolderName(payment.getCardHolderName());
        response.setCardNumber(payment.getCardNumber());
        response.setPaymentMethod(payment.getPaymentMethod());
        response.setPaymentDate(payment.getPaymentDate());
        response.setAmount(payment.getAmount());
        response.setStatus(payment.getStatus());
        response.setReservationId(payment.getReservation().getId());
        return response;
    }


}
