package uz.pdp.appcourse.service.status;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.pdp.appcourse.dto.ApiResponse;
import uz.pdp.appcourse.dto.Response;
import uz.pdp.appcourse.dto.status.StatusDto;
import uz.pdp.appcourse.dto.status.StatusResponseDto;
import uz.pdp.appcourse.model.status.StatusEntity;
import uz.pdp.appcourse.repository.StatusRepository;
import uz.pdp.appcourse.service.BaseService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StatusService implements BaseService<StatusDto, StatusResponseDto,Long, StatusEntity>, Response {

    private final StatusRepository statusRepository;
    private final ModelMapper modelMapper;

    @Override
    public ApiResponse<StatusDto> create(StatusDto statusDto) {
        boolean exists = statusRepository.existsByName(statusDto.getName());
        if (exists) {
            return new ApiResponse<>(ALREADY_EXIST,false);
        }
        StatusEntity status = modelMapper.map(statusDto, StatusEntity.class);
        statusRepository.save(status);
        return new ApiResponse<>(SUCCESSFULLY_SAVED,true);
    }

    @Override
    public ApiResponse<Void> delete(Long id) {
        Optional<StatusEntity> optionalStatus = statusRepository.findById(id);
        if (optionalStatus.isEmpty()) {
            return new ApiResponse<>(STATUS + NOT_FOUND,false);
        }
        statusRepository.delete(optionalStatus.get());
        return new ApiResponse<>(SUCCESSFULLY_DELETED,true);
    }

    @Override
    public ApiResponse<Void> update(Long id, StatusDto statusDto) {
        Optional<StatusEntity> optionalStatus = statusRepository.findById(id);
        if (optionalStatus.isEmpty()) {
            return new ApiResponse<>(STATUS + NOT_FOUND,false);
        }
        StatusEntity statusEntity = optionalStatus.get();
        modelMapper.map(statusDto,statusEntity);
        statusRepository.save(statusEntity);
        return new ApiResponse<>(SUCCESSFULLY_UPDATED,true);
    }

    @Override
    public ApiResponse<List<StatusEntity>> getAll() {
        return new ApiResponse<>(DATA_LIST,true,statusRepository.findAll());
    }

    @Override
    public ApiResponse<StatusResponseDto> getById(Long id) {
        Optional<StatusEntity> optionalStatusEntity = statusRepository.findById(id);
        if (optionalStatusEntity.isEmpty()) {
            return new ApiResponse<>(STATUS + NOT_FOUND,false);
        }
        StatusEntity statusEntity = optionalStatusEntity.get();
        StatusResponseDto responseDto = modelMapper.map(statusEntity, StatusResponseDto.class);
        return new ApiResponse<>(STATUS,true,responseDto);
    }
}
