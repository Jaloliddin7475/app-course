package uz.pdp.appcourse.controller.payment;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appcourse.controller.Base;
import uz.pdp.appcourse.dto.ApiResponse;
import uz.pdp.appcourse.dto.pay_type.PayTypeDto;
import uz.pdp.appcourse.dto.pay_type.PayTypeResponseDto;
import uz.pdp.appcourse.service.payment.PayTypeService;

import javax.validation.Valid;

import static uz.pdp.appcourse.controller.Base.API;

@RestController
@RequiredArgsConstructor
@RequestMapping(API + "/payType")
public class PayTypeController implements Base {

    private final PayTypeService payTypeService;

    @PostMapping(ADD)
    public ResponseEntity<?> addPayType (@Valid @RequestBody PayTypeDto payTypeDto) {
        ApiResponse<PayTypeDto> apiResponse = payTypeService.create(payTypeDto);
        return ResponseEntity.status(apiResponse.isStatus() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    @GetMapping(LIST)
    public ResponseEntity<?> getList () {
        return ResponseEntity.ok(payTypeService.getAll());
    }

    @GetMapping(GET + "/{id}")
    public ResponseEntity<?> getPayType (@PathVariable Long id) {
        ApiResponse<PayTypeResponseDto> apiResponse = payTypeService.getById(id);
        return ResponseEntity.status(apiResponse.isStatus() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @PutMapping(UPDATE + "/{id}")
    public ResponseEntity<?> updatedPayType (@PathVariable Long id, @Valid @RequestBody PayTypeDto payTypeDto) {
        ApiResponse<Void> apiResponse = payTypeService.update(id, payTypeDto);
        return ResponseEntity.status(apiResponse.isStatus() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @DeleteMapping(DELETE + "/{id}")
    public ResponseEntity<?> deleteType (@PathVariable Long id) {
        ApiResponse<Void> apiResponse = payTypeService.delete(id);
        return ResponseEntity.status(apiResponse.isStatus() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse);
    }
}
