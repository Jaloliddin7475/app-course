package uz.pdp.appcourse.service.payment;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.pdp.appcourse.dto.ApiResponse;
import uz.pdp.appcourse.dto.Response;
import uz.pdp.appcourse.dto.pay_type.PayTypeDto;
import uz.pdp.appcourse.dto.pay_type.PayTypeResponseDto;
import uz.pdp.appcourse.model.payment.PayType;
import uz.pdp.appcourse.repository.PayTypeRepository;
import uz.pdp.appcourse.service.BaseService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PayTypeService implements BaseService<PayTypeDto, PayTypeResponseDto,Long, PayType>, Response {

    private final PayTypeRepository payTypeRepository;
    private final ModelMapper modelMapper;

    @Override
    public ApiResponse<PayTypeDto> create(PayTypeDto payTypeDto) {
        boolean exists = payTypeRepository.existsByName(payTypeDto.getName());
        if (exists) {
            return new ApiResponse<>(ALREADY_EXIST,false);
        }
        PayType payType = modelMapper.map(payTypeDto, PayType.class);
        payTypeRepository.save(payType);
        return new ApiResponse<>(SUCCESSFULLY_SAVED,true);
    }

    @Override
    public ApiResponse<Void> delete(Long id) {
        Optional<PayType> optionalPayType = payTypeRepository.findById(id);
        if (optionalPayType.isEmpty()) {
            return new ApiResponse<>( PAY_TYPE + NOT_FOUND,false);
        }
        payTypeRepository.delete(optionalPayType.get());
        return new ApiResponse<>(SUCCESSFULLY_DELETED,true);
    }

    @Override
    public ApiResponse<Void> update(Long id, PayTypeDto payTypeDto) {
        Optional<PayType> optionalPayType = payTypeRepository.findById(id);
        if (optionalPayType.isEmpty()) {
            return new ApiResponse<>(PAY_TYPE + NOT_FOUND,false);
        }
        PayType payType = optionalPayType.get();
        modelMapper.map(payTypeDto,payType);
        payTypeRepository.save(payType);
        return new ApiResponse<>(SUCCESSFULLY_UPDATED,true);
    }

    @Override
    public ApiResponse<List<PayType>> getAll() {
        return new ApiResponse<>(DATA_LIST,true,payTypeRepository.findAll());
    }

    @Override
    public ApiResponse<PayTypeResponseDto> getById(Long id) {
        Optional<PayType> optionalPayType = payTypeRepository.findById(id);
        if (optionalPayType.isEmpty()) {
            return new ApiResponse<>(PAY_TYPE + NOT_FOUND,false);
        }
        PayType payType = optionalPayType.get();
        PayTypeResponseDto responseDto = modelMapper.map(payType, PayTypeResponseDto.class);
        return new ApiResponse<>(PAY_TYPE,true,responseDto);
    }
}
