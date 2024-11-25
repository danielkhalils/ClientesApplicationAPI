package io.github.danielkhalils.rest.controller;


import io.github.danielkhalils.model.entity.Usuario;
import io.github.danielkhalils.model.repository.UsuarioRepository;
import io.github.danielkhalils.rest.exception.UsuarioCadastradoException;
import io.github.danielkhalils.rest.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private UsuarioService usuarioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void salvar(@RequestBody @Valid Usuario usuario){
        try {
            usuarioService.salvar(usuario);
        }catch (UsuarioCadastradoException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
