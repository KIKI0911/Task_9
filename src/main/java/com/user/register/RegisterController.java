package com.user.register;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.ZonedDateTime;
import java.util.Map;

@RequestMapping("/users")
@RestController
public class RegisterController {

    private final RegisterService registerService;

    @Autowired
    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") Integer id) {
        return registerService.findUser(id);
    }

    @GetMapping("/{id}/address/{addressId}")
    public User getUserFromAddress(@PathVariable("id") Integer id, @PathVariable("addressId") Integer addressId) {
        return registerService.findUserFromAddress(id, addressId);
    }

    @PostMapping
    public ResponseEntity<RegisterResponse> insert(@RequestBody RegisterRequest registerRequest, UriComponentsBuilder uriBuilder) {
        User user = registerService.insert(registerRequest.getName(), registerRequest.getEmail(), registerRequest.getAddressId(), registerRequest.getAge());
        URI location = uriBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri();
        RegisterResponse body = new RegisterResponse("user created");
        return ResponseEntity.created(location).body(body);
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleUserNotFoundException(
            UserNotFoundException e, HttpServletRequest request) {
        Map<String, String> body = Map.of(
                "timestamp", ZonedDateTime.now().toString(),
                "status", String.valueOf(HttpStatus.NOT_FOUND.value()),
                "error", HttpStatus.NOT_FOUND.getReasonPhrase(),
                "message", e.getMessage(),
                "path", request.getRequestURI());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = UserAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleUserAlreadyExistsException(
            UserAlreadyExistsException e, HttpServletRequest request) {
        Map<String, String> body = Map.of(
                "timestamp", ZonedDateTime.now().toString(),
                "status", String.valueOf(HttpStatus.BAD_REQUEST.value()),
                "error", HttpStatus.BAD_REQUEST.getReasonPhrase(),
                "message", e.getMessage(),
                "path", request.getRequestURI());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
