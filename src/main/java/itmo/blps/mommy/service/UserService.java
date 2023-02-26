package itmo.blps.mommy.service;

import itmo.blps.mommy.config.jwt.JwtProvider;
import itmo.blps.mommy.dto.UserDTO;
import itmo.blps.mommy.entity.User;
import itmo.blps.mommy.exception.AuthException;
import itmo.blps.mommy.mapper.UserMapper;
import itmo.blps.mommy.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtProvider jwtProvider;

    public String register(UserDTO userDTO) throws AuthException {
        User user = userMapper.fromDto(userDTO);
        if (user.getEmail().length() == 0) {
            throw new AuthException("Please choose not an empty email");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User userByEmail = findByEmail(user.getEmail());
        if (userByEmail == null) {
            userRepository.save(user);
        } else {
            throw new AuthException("Email is already in use");
        }
        return jwtProvider.generateToken(user.getEmail());

    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public String auth(UserDTO user) {
        User userByEmail = findByEmail(user.getEmail());
        if (userByEmail == null) {
            throw new AuthException("User not found");
        }

        if (passwordEncoder.matches(user.getPassword(), userByEmail.getPassword())) {
            return jwtProvider.generateToken(user.getEmail());
        }

        throw new AuthException("Wrong email or password");
    }

}

