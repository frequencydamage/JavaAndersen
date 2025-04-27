package org.novak.java.controller.v1;


import lombok.extern.log4j.Log4j2;
import org.novak.java.dto.AuthResponseDto;
import org.novak.java.facade.AuthFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthFacade authFacade;

    @Autowired
    public AuthController(AuthFacade authFacade) {
        this.authFacade = authFacade;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestParam("username") String username,
                                                 @RequestParam("password") String password) {
        try {
            String token = authFacade.authenticate(username, password);
            AuthResponseDto authResponseDto = new AuthResponseDto();
            authResponseDto.setToken(token);
            return ResponseEntity.ok(authResponseDto);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(401).body(null);
        }
    }
}
