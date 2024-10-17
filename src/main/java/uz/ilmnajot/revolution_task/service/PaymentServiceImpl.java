package uz.ilmnajot.revolution_task.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.ilmnajot.revolution_task.entity.Payment;
import uz.ilmnajot.revolution_task.entity.Reservation;
import uz.ilmnajot.revolution_task.exception.NotFoundException;
import uz.ilmnajot.revolution_task.payload.common.ApiResponse;
import uz.ilmnajot.revolution_task.payload.request.PaymentRequest;
import uz.ilmnajot.revolution_task.payload.response.PaymentResponse;
import uz.ilmnajot.revolution_task.repository.PaymentRepository;
import uz.ilmnajot.revolution_task.repository.ReservationRepository;
import uz.ilmnajot.revolution_task.service.interfaces.PaymentService;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final ReservationRepository reservationRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository, ReservationRepository reservationRepository) {
        this.paymentRepository = paymentRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public ApiResponse makePayment(PaymentRequest request) {
        Payment payment = new Payment();
        payment.setCardHolderName(request.getCardHolderName());
        payment.setCardNumber(request.getCardNumber());
        payment.setPaymentMethod(request.getPaymentMethod());
        payment.setAmount(request.getAmount());
        Payment savedPayment = paymentRepository.save(payment);
        PaymentResponse paymentResponse = new PaymentResponse();
        PaymentResponse response = paymentResponse.toPaymentResponse(savedPayment);
        return new ApiResponse(true, "success", response);
    }

    @Override
    public ApiResponse updatePayment(Long paymentId, PaymentRequest request) {
        Payment payment = paymentRepository.findById(paymentId).orElseThrow(()
                -> new NotFoundException("Payment not found", HttpStatus.BAD_REQUEST));
        Reservation reservation = reservationRepository.findById(request.getReservationId()).orElseThrow(()
                -> new NotFoundException("Reservation not found", HttpStatus.BAD_REQUEST));
        payment.setCardHolderName(request.getCardHolderName());
        payment.setCardNumber(request.getCardNumber());
        payment.setPaymentMethod(request.getPaymentMethod());
        payment.setPaymentDate(request.getPaymentDate());
        payment.setAmount(request.getAmount());
        payment.setStatus(request.getStatus());
        payment.setReservation(reservation);
        Payment savedOne = paymentRepository.save(payment);
        PaymentResponse paymentResponse = new PaymentResponse().toPaymentResponse(savedOne);
        return new ApiResponse(true, "success", paymentResponse);
    }

    @Override
    public ApiResponse getPayment(Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId).orElseThrow(()
                -> new NotFoundException("Payment not found", HttpStatus.BAD_REQUEST));
        PaymentResponse paymentResponse = new PaymentResponse().toPaymentResponse(payment);
        return new ApiResponse(true, "success", paymentResponse);
    }

    @Override
    public ApiResponse getPayments(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Payment> payments = paymentRepository.findAll(pageRequest);
        List<PaymentResponse> paymentResponses =
                payments.stream().map(payment -> new PaymentResponse().toPaymentResponse(payment)).toList();
        return new ApiResponse(true, "success", paymentResponses);
    }

    @Override
    public ApiResponse deletePayment(Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId).orElseThrow(()
                -> new NotFoundException("Payment not found", HttpStatus.BAD_REQUEST));
        payment.setDisabled(true);
        paymentRepository.save(payment);
        return new ApiResponse(true, "success", "payment has been deleted with ID: " + paymentId);
    }
}
