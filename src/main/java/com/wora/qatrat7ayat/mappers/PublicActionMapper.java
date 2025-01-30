package com.wora.qatrat7ayat.mappers;

import com.wora.qatrat7ayat.mappers.api.GenericMapper;
import com.wora.qatrat7ayat.models.DTOs.action.ActionDto;
import com.wora.qatrat7ayat.models.DTOs.action.publicAction.CreatePublicActionDto;
import com.wora.qatrat7ayat.models.DTOs.action.publicAction.UpdatePublicActionDto;
import com.wora.qatrat7ayat.models.entities.Action;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PublicActionMapper extends GenericMapper<Action, ActionDto> {
    Action toEntity(ActionDto dto);
    Action toEntity(CreatePublicActionDto dto);
    Action toEntity(UpdatePublicActionDto dto);
    ActionDto toDto(Action action);
}
