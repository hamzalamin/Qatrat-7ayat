package com.wora.qatrat7ayat.services.inter;

import com.wora.qatrat7ayat.models.DTOs.action.request.CreateRequestDto;
import com.wora.qatrat7ayat.models.DTOs.action.request.RequestDto;
import com.wora.qatrat7ayat.models.DTOs.action.request.UpdateRequestDto;
import com.wora.qatrat7ayat.services.GenericService;
import org.springframework.data.domain.Page;

public interface IRequestService extends GenericService<CreateRequestDto, UpdateRequestDto, RequestDto, Long> {
    Page<RequestDto> findAllPage(Integer pageNumber, Integer size);
}
