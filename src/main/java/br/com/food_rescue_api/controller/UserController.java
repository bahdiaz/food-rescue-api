package br.com.food_rescue_api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.food_rescue_api.model.User;

@RestController
public class UserController {

    private List<User> repository = new ArrayList<>();

    @GetMapping("/users")
    public List<User> index() {
        return repository;
    }

    @PostMapping("/users")
    public ResponseEntity<User> create(@RequestBody User user) {
        System.out.println("Cadastrando..." + user);
        repository.add(user);
        return ResponseEntity.status(201).body(user);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> get(@PathVariable Long id) {
        System.out.println("Buscando usuário " + id);
        var user = repository
                .stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();

        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(user.get());
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<User> delete(@PathVariable Long id) {
        System.out.println("Deletando usuário " + id);
        var user = repository
                .stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();

        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        repository.remove(user.get());
        return ResponseEntity.ok(user.get());
    }

}
