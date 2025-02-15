package com.wora.qatrat7ayat.services.INTER;

import com.wora.qatrat7ayat.models.DTOs.action.request.CreateRequestDto;
import com.wora.qatrat7ayat.models.DTOs.action.request.RequestDto;
import com.wora.qatrat7ayat.models.DTOs.action.request.UpdateRequestDto;
import com.wora.qatrat7ayat.services.GenericService;

public interface IRequestService extends GenericService<CreateRequestDto, UpdateRequestDto, RequestDto, Long> {
}
