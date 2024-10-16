package uz.ilmnajot.revolution_task.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Size;
import lombok.*;
import uz.ilmnajot.revolution_task.enums.PaymentMethod;
import uz.ilmnajot.revolution_task.enums.PaymentStatus;
import uz.ilmnajot.revolution_task.template.AbsEntity;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "payment")
@Builder
public class Payment extends AbsEntity {

    private String cardHolderName;
    @Size(min = 15, max =17, message = "card number should be 16 digit")
    private String cardNumber;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    private LocalDate paymentDate;

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @ManyToOne
    private Reservation reservation;

}
