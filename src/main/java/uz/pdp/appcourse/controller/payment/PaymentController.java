package uz.pdp.appcourse.controller.payment;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appcourse.controller.Base;
import uz.pdp.appcourse.dto.ApiResponse;
import uz.pdp.appcourse.dto.payment.PaymentDto;
import uz.pdp.appcourse.dto.payment.PaymentResponseDto;
import uz.pdp.appcourse.service.payment.PaymentService;

import javax.validation.Valid;

import static uz.pdp.appcourse.controller.Base.API;

@RestController
@RequiredArgsConstructor
@RequestMapping(API + "/payment")
public class PaymentController implements Base {

    private final PaymentService paymentService;

    @PostMapping(ADD)
    public ResponseEntity<?> addPayment (@Valid @RequestBody PaymentDto paymentDto) {
        ApiResponse<PaymentDto> apiResponse = paymentService.create(paymentDto);
        return ResponseEntity.status(apiResponse.isStatus() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    @GetMapping(LIST)
    public ResponseEntity<?> getList() {
        return ResponseEntity.ok(paymentService.getAll());
    }

    @GetMapping(GET + "/{id}")
    public ResponseEntity<?> getPayment (@PathVariable Long id) {
        ApiResponse<PaymentResponseDto> apiResponse = paymentService.getById(id);
        return ResponseEntity.status(apiResponse.isStatus() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @PutMapping(UPDATE + "/{id}")
    public ResponseEntity<?> updatePayment (@PathVariable Long id, @Valid @RequestBody PaymentDto paymentDto) {
        ApiResponse<Void> apiResponse = paymentService.update(id, paymentDto);
        return ResponseEntity.status(apiResponse.isStatus() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @DeleteMapping(DELETE + "/{id}")
    public ResponseEntity<?> deletePayment (@PathVariable Long id) {
        ApiResponse<Void> apiResponse = paymentService.delete(id);
        return ResponseEntity.status(apiResponse.isStatus() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse);
    }
}
