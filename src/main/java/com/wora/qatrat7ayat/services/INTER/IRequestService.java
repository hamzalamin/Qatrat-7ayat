package com.wora.qatrat7ayat.services.INTER;

import com.wora.qatrat7ayat.models.DTOs.action.request.EmbeddedRequestDto;
import com.wora.qatrat7ayat.models.DTOs.action.request.RequestDto;
import com.wora.qatrat7ayat.models.DTOs.action.request.UpdateRequestDto;
import com.wora.qatrat7ayat.services.GenericService;

public interface IRequestService extends GenericService<EmbeddedRequestDto, UpdateRequestDto, RequestDto, Long> {
}
