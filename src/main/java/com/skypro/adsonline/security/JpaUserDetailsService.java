package com.skypro.adsonline.security;

import com.skypro.adsonline.exception.UserNotFoundException;
import com.skypro.adsonline.model.UserEntity;
import com.skypro.adsonline.repository.UserRepository;
import com.skypro.adsonline.utils.UserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.skypro.adsonline.constant.ErrorMessage.USER_NOT_FOUND_MSG;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder encoder;
    private final SecurityUser securityUser;

    public JpaUserDetailsService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder encoder, SecurityUser securityUser) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.encoder = encoder;
        this.securityUser = securityUser;
    }

    @Override
    //@Transactional
    public UserDetails loadUserByUsername(String username) {
        UserEntity userFromDb = userRepository.findByUsername(username);
        if (userFromDb == null) {
            throw new UserNotFoundException(USER_NOT_FOUND_MSG.formatted(username));
        }
        securityUser.setUser(userFromDb);
        return securityUser;
    }
}
