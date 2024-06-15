package com.grigoriank.taskUserService.endpoint;

import com.grigoriank.taskUserService.dto.AuthResponseDTO;
import com.grigoriank.taskUserService.dto.LoginRequestDTO;
import com.grigoriank.taskUserService.entity.User;
import com.grigoriank.taskUserService.repository.UserRepository;
import com.grigoriank.taskUserService.security.CustomerUserServiceImpl;
import com.grigoriank.taskUserService.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthEndpoint {

    private final CustomerUserServiceImpl customerUserService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtTokenUtil jwtTokenUtil;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponseDTO> createUser(@RequestBody User user) throws Exception {

        String fullName = user.getFullName();
        String email = user.getEmail();
        String password = user.getPassword();
        String role = user.getRole();

        User isEmailExist = userRepository.findByEmail(email);
        if (isEmailExist != null) {
            throw new Exception("Email is already used with another account.");
        }

        User createUser = new User();
        createUser.setFullName(fullName);
        createUser.setEmail(email);
        createUser.setPassword(passwordEncoder.encode(password));
        createUser.setRole(role);

        User saveUser = userRepository.save(createUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenUtil.generateToken(authentication);

        AuthResponseDTO authResponseDTO = new AuthResponseDTO();
        authResponseDTO.setJwt(token);
        authResponseDTO.setMessage("Register succes!");
        authResponseDTO.setStatus(true);

        return new ResponseEntity<>(authResponseDTO, HttpStatus.OK);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {

        String username = loginRequestDTO.getEmail();
        String password = loginRequestDTO.getPassword();

        System.out.println(username + " ----- " + password);

        Authentication authentication = authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenUtil.generateToken(authentication);

        AuthResponseDTO authResponseDTO = new AuthResponseDTO();
        authResponseDTO.setMessage("Login succes!");
        authResponseDTO.setJwt(token);
        authResponseDTO.setStatus(true);

        return new ResponseEntity<>(authResponseDTO, HttpStatus.OK);
    }

    private Authentication authenticate(String username, String password) {
        UserDetails userDetails = customerUserService.loadUserByUsername(username);

        System.out.println("sign in userDetails - " + userDetails);

        if (userDetails == null) {
            System.out.println("sign in userDetails - null " + userDetails);
            throw new BadCredentialsException("Invalid username or password.");
        }
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            System.out.println("sign in userDetails - password not match " + userDetails);
            throw new BadCredentialsException("Invalid username or password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
