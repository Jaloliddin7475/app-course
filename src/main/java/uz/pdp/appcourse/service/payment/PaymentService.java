package uz.pdp.appcourse.service.payment;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.pdp.appcourse.dto.ApiResponse;
import uz.pdp.appcourse.dto.Response;
import uz.pdp.appcourse.dto.payment.PaymentDto;
import uz.pdp.appcourse.dto.payment.PaymentResponseDto;
import uz.pdp.appcourse.model.payment.PayType;
import uz.pdp.appcourse.model.payment.PaymentEntity;
import uz.pdp.appcourse.model.student.StudentEntity;
import uz.pdp.appcourse.repository.PayTypeRepository;
import uz.pdp.appcourse.repository.PaymentRepository;
import uz.pdp.appcourse.repository.StudentRepository;
import uz.pdp.appcourse.service.BaseService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentService implements BaseService<PaymentDto, PaymentResponseDto,Long, PaymentEntity>, Response {

    private final PaymentRepository paymentRepository;
    private final ModelMapper modelMapper;
    private final StudentRepository studentRepository;
    private final PayTypeRepository payTypeRepository;

    @Override
    public ApiResponse<PaymentDto> create(PaymentDto paymentDto) {
        Optional<PayType> optionalPayType =
                payTypeRepository.findById(paymentDto.getPayTypeId());
        if (optionalPayType.isEmpty()) {
            return new ApiResponse<>(PAY_TYPE + NOT_FOUND,false);
        }
        Optional<StudentEntity> optionalStudent =
                studentRepository.findById(paymentDto.getStudentId());
        if (optionalStudent.isEmpty()) {
            return new ApiResponse<>(STUDENT + NOT_FOUND,false);
        }
        PaymentEntity paymentEntity = modelMapper.map(paymentDto, PaymentEntity.class);
        paymentEntity.setStudent(optionalStudent.get());
        paymentEntity.setPayType(optionalPayType.get());
        paymentRepository.save(paymentEntity);
        return new ApiResponse<>(SUCCESSFULLY_SAVED,true);
    }

    @Override
    public ApiResponse<Void> delete(Long id) {
        Optional<PaymentEntity> optionalPaymentEntity = paymentRepository.findById(id);
        if (optionalPaymentEntity.isEmpty()) {
            return new ApiResponse<>(PAYMENT + NOT_FOUND,false);
        }
        paymentRepository.delete(optionalPaymentEntity.get());
        return new ApiResponse<>(SUCCESSFULLY_DELETED,true);
    }

    @Override
    public ApiResponse<Void> update(Long id, PaymentDto paymentDto) {
        Optional<PaymentEntity> optionalPaymentEntity = paymentRepository.findById(id);
        if (optionalPaymentEntity.isEmpty()) {
            return new ApiResponse<>(PAYMENT + NOT_FOUND,false);
        }
        PaymentEntity paymentEntity = optionalPaymentEntity.get();
        modelMapper.map(paymentDto, paymentEntity);
        paymentRepository.save(paymentEntity);
        return new ApiResponse<>(SUCCESSFULLY_UPDATED,true);
    }

    @Override
    public ApiResponse<List<PaymentEntity>> getAll() {
        return new ApiResponse<>(DATA_LIST,true,paymentRepository.findAll());
    }

    @Override
    public ApiResponse<PaymentResponseDto> getById(Long id) {
        Optional<PaymentEntity> optionalPaymentEntity = paymentRepository.findById(id);
        if (optionalPaymentEntity.isEmpty()) {
            return new ApiResponse<>(PAYMENT + NOT_FOUND,false);
        }
        PaymentResponseDto paymentResponseDto =
                modelMapper.map(optionalPaymentEntity.get(), PaymentResponseDto.class);
        return new ApiResponse<>(PAYMENT,true,paymentResponseDto);
    }
}
