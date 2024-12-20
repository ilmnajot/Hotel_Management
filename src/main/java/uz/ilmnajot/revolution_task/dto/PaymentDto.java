package uz.ilmnajot.revolution_task.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Size;
import lombok.*;
import uz.ilmnajot.revolution_task.entity.Reservation;
import uz.ilmnajot.revolution_task.enums.PaymentMethod;
import uz.ilmnajot.revolution_task.enums.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentDto {

    private Long id;
    private String cardHolderName;
    private String cardNumber;
    private PaymentMethod paymentMethod;
    private LocalDate paymentDate;
    private BigDecimal amount;
    private PaymentStatus status;
    private Long reservationId;
}
