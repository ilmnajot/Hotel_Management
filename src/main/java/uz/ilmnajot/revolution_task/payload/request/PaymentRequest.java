package uz.ilmnajot.revolution_task.payload.request;

import lombok.Getter;
import lombok.Setter;
import uz.ilmnajot.revolution_task.enums.PaymentMethod;
import uz.ilmnajot.revolution_task.enums.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
public class PaymentRequest {

    private String cardHolderName;
    private String cardNumber;
    private PaymentMethod paymentMethod;
    private LocalDate paymentDate;
    private BigDecimal amount;
    private PaymentStatus status;
    private Long reservationId;



}
