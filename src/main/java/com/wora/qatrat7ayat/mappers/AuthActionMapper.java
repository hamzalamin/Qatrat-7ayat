package com.wora.qatrat7ayat.mappers;

import com.wora.qatrat7ayat.mappers.api.GenericMapper;
import com.wora.qatrat7ayat.models.DTOs.action.ActionDto;
import com.wora.qatrat7ayat.models.DTOs.action.AuthAction.CreateAuthActionDto;
import com.wora.qatrat7ayat.models.DTOs.action.AuthAction.UpdateAuthActionDto;
import com.wora.qatrat7ayat.models.entities.Action;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthActionMapper extends GenericMapper<Action, ActionDto> {
    Action toEntity(ActionDto dto);
    Action toEntity(CreateAuthActionDto dto);
    Action toEntity(UpdateAuthActionDto dto);
    ActionDto toDto(Action action);
}
