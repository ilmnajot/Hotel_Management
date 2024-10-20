package uz.ilmnajot.revolution_task.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.ilmnajot.revolution_task.entity.Payment;
import uz.ilmnajot.revolution_task.entity.Reservation;
import uz.ilmnajot.revolution_task.enums.PaymentStatus;
import uz.ilmnajot.revolution_task.exception.NotFoundException;
import uz.ilmnajot.revolution_task.payload.common.ApiResponse;
import uz.ilmnajot.revolution_task.payload.request.PaymentRequest;
import uz.ilmnajot.revolution_task.payload.response.PaymentResponse;
import uz.ilmnajot.revolution_task.repository.PaymentRepository;
import uz.ilmnajot.revolution_task.repository.ReservationRepository;
import uz.ilmnajot.revolution_task.service.interfaces.PaymentService;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Reservation reservation = reservationRepository.findById(request.getReservationId()).orElseThrow(()
                -> new NotFoundException("reservation not found", HttpStatus.BAD_REQUEST));

        Payment payment = new Payment();
        payment.setCardHolderName(request.getCardHolderName());
        payment.setCardNumber(request.getCardNumber());
        payment.setPaymentMethod(request.getPaymentMethod());
        payment.setPaymentDate(LocalDate.now());
        payment.setAmount(request.getAmount());
        payment.setStatus(PaymentStatus.PAID);
        payment.setReservation(reservation);
        Payment savedPayment = paymentRepository.save(payment);
        PaymentResponse paymentResponse = new PaymentResponse();
        PaymentResponse response = paymentResponse.toPaymentResponse(savedPayment);
        return new ApiResponse(true, "success", response,HttpStatus.OK);
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
        Pageable pageable = PageRequest.of(page, size);
        Page<Payment> payments = paymentRepository.findAll(pageable);
        if (payments.isEmpty()){
            throw new NotFoundException("No payments found", HttpStatus.OK);
        }
        List<PaymentResponse> paymentResponses =
                payments
                        .stream()
                        .map(payment -> new PaymentResponse().toPaymentResponse(payment))
                        .toList();
        Map<String, Object> response = new HashMap<>();
        response.put("data", paymentResponses);
        response.put("currentPage", payments.getNumber());
        response.put("totalItems", payments.getTotalElements());
        response.put("totalPages", payments.getTotalPages());
        response.put("pageSize", payments.getSize());
        return new ApiResponse(true, "success", response, HttpStatus.OK);
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
