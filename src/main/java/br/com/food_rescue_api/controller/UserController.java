package br.com.food_rescue_api.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.server.ResponseStatusException;

import br.com.food_rescue_api.model.User;

@RestController
@RequestMapping("/users")
public class UserController {

    private Logger log = LoggerFactory.getLogger(getClass());
    private List<User> repository = new ArrayList<>();

    @GetMapping
    public List<User> index() {
        return repository;
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        log.info("Cadastrando usuário: " + user.getName());
        repository.add(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping("/{id}")
    public User get(@PathVariable Long id) {
        log.info("Buscando usuário " + id);
        return getUser(id);
    }

    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody User user) {
        log.info("Atualizando usuário " + id + " com " + user);

        repository.remove(getUser(id));
        user.setId(id);
        repository.add(user);

        return user;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable Long id) {
        log.info("Removendo usuário " + id);
        repository.remove(getUser(id));
    }

    private User getUser(Long id) {
        return repository
                .stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
    }
}
