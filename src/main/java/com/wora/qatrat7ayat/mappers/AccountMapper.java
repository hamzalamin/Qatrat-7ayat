package com.wora.qatrat7ayat.mappers;

import com.wora.qatrat7ayat.mappers.api.GenericMapper;
import com.wora.qatrat7ayat.models.DTOs.account.AccountDto;
import com.wora.qatrat7ayat.models.DTOs.city.EmbeddedCityDto;
import com.wora.qatrat7ayat.models.entities.City;
import com.wora.qatrat7ayat.models.entities.User;
import com.wora.qatrat7ayat.security.DTO.Role.EmbeddedRoleDto;
import com.wora.qatrat7ayat.security.models.AuthenticatedUser;
import com.wora.qatrat7ayat.security.models.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface AccountMapper extends GenericMapper<AuthenticatedUser, AccountDto> {

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "role", source = "role", qualifiedByName = "mapRole")
    @Mapping(target = "city", source = "city", qualifiedByName = "mapCity")
    AccountDto toDto(AuthenticatedUser user);

    @Mapping(target = "articles", ignore = true)
    @Mapping(target = "isSuspended", ignore = true)
    AuthenticatedUser toEntity(AccountDto dto);

    default AccountDto userToAccountDto(User user) {
        if (user == null) {
            return null;
        }

        if (user instanceof AuthenticatedUser) {
            return toDto((AuthenticatedUser) user);
        }

        return AccountDto.builder()
                .id(user.getId())
                .phone(user.getPhone())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .bloodType(user.getBloodType())
                .city(user.getCity() != null ?
                        new EmbeddedCityDto(user.getCity().getId(), user.getCity().getCityName()) :
                        null)
                .build();
    }

    @Named("mapRole")
    default EmbeddedRoleDto mapRole(Role role) {
        if (role == null) {
            return null;
        }
        return new EmbeddedRoleDto(role.getId(), role.getName());
    }

    @Named("mapCity")
    default EmbeddedCityDto mapCity(City city) {
        if (city == null) {
            return null;
        }
        return new EmbeddedCityDto(city.getId(), city.getCityName());
    }
}