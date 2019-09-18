package com.achrefdkhailia.code.controller;

import com.achrefdkhailia.code.domain.User;
import com.achrefdkhailia.code.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Api;

import java.util.List;

@RestController
@RequestMapping("/users")
@Api(tags = {"users"})
public class UserController {

    @Autowired
    private UserService userService;

    //http://localhost:8080/swagger-ui.html#/users/saveUsingPOST
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "enregistrer un nouvel utilisateur ")
    public User save(@RequestBody User user) {
        return userService.save(user);
    }

   //http://localhost:8080/users/1
   // http://localhost:8080/swagger-ui.html#/users/findByIdUsingGET
   @GetMapping("/{id}")
    @ApiOperation(value = "Obtenir l'utilisateur par ID")
    public User findById(@PathVariable("id") final Long id) {
        return userService.findById(id);
    }

    //http://localhost:8080/users
    //http://localhost:8080/swagger-ui.html#/users/findAllUsingGET
    @GetMapping
    @ApiOperation(value = "obtenir la liste des utilisateurs ")
    public List<User> findAll() {
        return userService.findAll();
    }

    //http://localhost:8080/swagger-ui.html#/users/updateUsingPUT
    @PutMapping("/{id}")
    @ApiOperation(value = "Màj des infos d'un utilisateur identifié par ID")
    public User update(@PathVariable("id") Long id, @RequestBody User user) {
        return userService.update(id, user);
    }

    //http://localhost:8080/swagger-ui.html#/users/deleteByIdUsingDELETE
    @DeleteMapping("/{id}")
    @ApiOperation(value = "supprimer un utilisateur identifié par ID")
    public void deleteById(@PathVariable("id") final Long id) {
        userService.deleteById(id);
    }
}