package com.capstone.warehouse.controller;

import com.capstone.warehouse.dto.UserDTO;
import com.capstone.warehouse.entity.User;
import com.capstone.warehouse.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDTO> getAll() {
        return userService.getAllUsers()
                .stream()
                .map(UserDTO::from)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(UserDTO.from(userService.getUserById(id)));
    }

    @GetMapping("/by-email")
    public ResponseEntity<UserDTO> getByEmail(@RequestParam String email) {
        return ResponseEntity.ok(UserDTO.from(userService.getUserByEmail(email)));
    }

    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody User user) {
        return ResponseEntity.ok(UserDTO.from(userService.createUser(user)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable Integer id, @RequestBody User user) {
        return ResponseEntity.ok(UserDTO.from(userService.updateUser(id, user)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}