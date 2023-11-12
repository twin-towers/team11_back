package com.example.hackathon11.service;

import com.example.hackathon11.config.CustomUserDetailsService;
import com.example.hackathon11.constants.CustomConstants;
import com.example.hackathon11.converter.UserConverter;
import com.example.hackathon11.dto.JwtRequest;
import com.example.hackathon11.dto.JwtResponse;
import com.example.hackathon11.dto.RegisterUserDto;
import com.example.hackathon11.dto.UserDto;
import com.example.hackathon11.entity.User;
import com.example.hackathon11.exception.CustomBadCredentialsException;
import com.example.hackathon11.exception.InputDataErrorException;
import com.example.hackathon11.repository.UserRepository;
import com.example.hackathon11.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements CustomConstants {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserConverter userConverter;
    private final CustomUserDetailsService userDetailsService;
    private final JwtTokenUtil jwtTokenUtil;

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public JwtResponse createNewUser(RegisterUserDto registerUserDto) throws InputDataErrorException {
        if (registerUserDto.getPassword()==null) {
            throw new InputDataErrorException(PASSWORD_CANNOT_BE_EMPTY);
        } else {
            User user = new User();
            String encryptedPassword = passwordEncoder.encode(registerUserDto.getPassword());

            String validationMessage = validateAndSaveFields(registerUserDto, encryptedPassword, user);
            if (validationMessage != null) {
                throw new InputDataErrorException(validationMessage);
            }
            userRepository.save(user);

            try {
                authenticationManager.authenticate
                        (new UsernamePasswordAuthenticationToken(registerUserDto.getEmail(),
                                registerUserDto.getPassword()));
            } catch (BadCredentialsException e) {
                throw new CustomBadCredentialsException(INCORRECT_EMAIL_OR_PASSWORD);
            }
            UserDetails userDetails = userDetailsService.loadUserByUsername(registerUserDto.getEmail());
            String token = jwtTokenUtil.generateToken(userDetails);

            return new JwtResponse(token, userConverter.entityToDto(user));
        }
    }

    private String validateAndSaveFields(RegisterUserDto registerUserDto, String encryptedPassword,
                                         User user) {

        String username = registerUserDto.getUsername();
        String email = registerUserDto.getEmail();
        String firstName = registerUserDto.getFirstName();
        String lastName = registerUserDto.getLastName();
        String password = registerUserDto.getPassword();

        if (username==null || username.isBlank()) {
            username = email;
        }
        user.setNickname(username);             //  если нет username, сохраняем email
                                                //  сохраняем username как NICKNAME

        if (password==null || password.isBlank()) {
            return PASSWORD_CANNOT_BE_EMPTY;
        } else {
            user.setPassword(encryptedPassword);
        }

        if (email==null || email.isBlank()) return EMAIL_CANNOT_BE_EMPTY;
        EmailValidator validator = EmailValidator.getInstance();
        if (validator.isValid(email)) {
            user.setUsername(email);                // сохраняем email как USERNAME
        } else {
            return INCORRECT_EMAIL;
        }

        if (firstName!=null && !firstName.isBlank()) {
            user.setFirstName(firstName);
        } else {
            user.setFirstName("");
        }

        if (lastName!=null && !lastName.isBlank()) {
            user.setLastName(lastName);
        } else {
            user.setLastName("");
        }

        return null;    //  валидация прошла успешно
    }

    public JwtResponse createAuthToken(JwtRequest authRequest) {
        String email = authRequest.getEmail();
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
                    (email, authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new CustomBadCredentialsException(INCORRECT_EMAIL_OR_PASSWORD);
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        User user = findByUsername(email).orElseThrow
                (() -> new UsernameNotFoundException(String.format("User '%s' not found", email)));
        String token = jwtTokenUtil.generateToken(userDetails);
        return new JwtResponse(token, userConverter.entityToDto(user));
    }

    public UserDto getUserDetails(String token) throws InputDataErrorException {
        try {
            String email = jwtTokenUtil.getUsernameFromToken(token);

        User user = findByUsername(email).orElseThrow
                (() -> new UsernameNotFoundException(String.format("User '%s' not found", email)));
        return userConverter.entityToDto(user);
        } catch (Exception e) {
            throw new InputDataErrorException(INVALID_TOKEN);
        }
    }
}
