package com.wora.qatrat7ayat.services.IMPL;

import com.wora.qatrat7ayat.models.entities.Action;
import com.wora.qatrat7ayat.repositories.ActionRepository;
import com.wora.qatrat7ayat.services.INTER.IActionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ActionService implements IActionService {
    private final ActionRepository actionRepository;

    @Override
    public Action createActionEntity(Action action) {
        return actionRepository.save(action);
    }
}
