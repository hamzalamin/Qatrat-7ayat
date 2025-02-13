package com.wora.qatrat7ayat.services.IMPL;

import com.wora.qatrat7ayat.models.entities.User;
import com.wora.qatrat7ayat.security.repositories.UserRepository;
import com.wora.qatrat7ayat.services.INTER.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;

    @Override
    public User createUserEntity(User user) {
        Date currentTime = new Date();
        user.setCreatedAt(currentTime);
        user.setUpdatedAt(currentTime);
        return userRepository.save(user);
    }
}
