package com.wora.qatrat7ayat.services.inter;

import com.wora.qatrat7ayat.models.DTOs.account.AccountDto;
import com.wora.qatrat7ayat.models.DTOs.account.CreateUserAccountDto;
import com.wora.qatrat7ayat.models.DTOs.account.UpdateUserAccountDto;
import com.wora.qatrat7ayat.security.DTO.SignupResponse;
import com.wora.qatrat7ayat.services.GenericService;
import org.springframework.data.domain.Page;

public interface IAccountService extends GenericService<CreateUserAccountDto, UpdateUserAccountDto, SignupResponse, Long> {
    boolean toggleSuspension(Long id);
    Page<AccountDto> findAllPage(Integer pageNumber, Integer size);
}
