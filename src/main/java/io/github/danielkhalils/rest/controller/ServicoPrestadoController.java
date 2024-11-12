package io.github.danielkhalils.rest.controller;

import io.github.danielkhalils.model.entity.Cliente;
import io.github.danielkhalils.model.entity.ServicoPrestado;
import io.github.danielkhalils.model.repository.ClientesRepository;
import io.github.danielkhalils.model.repository.ServicoPrestadoRepository;
import io.github.danielkhalils.rest.dto.ServicoPrestadoDTO;
import io.github.danielkhalils.util.BigDecimalConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/servicos-prestados")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ServicoPrestadoController {

    @Autowired
    private ClientesRepository clientesRepository;

    @Autowired
    private ServicoPrestadoRepository servicoPrestadoRepository;

    @Autowired
    private BigDecimalConverter bigDecimalConverter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServicoPrestado salvar(@RequestBody @Valid ServicoPrestadoDTO dto) {

        ServicoPrestado servicoPrestado = new ServicoPrestado();

        Integer idCliente = dto.getIdCliente();

        Cliente cliente =
                clientesRepository
                        .findById(idCliente)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));

        LocalDate data = LocalDate.parse(dto.getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        servicoPrestado.setData(data);
        servicoPrestado.setCliente(cliente);
        servicoPrestado.setDescricao(dto.getDescricao());
        servicoPrestado.setValor(bigDecimalConverter.converter(dto.getValor()));

        return servicoPrestadoRepository.save(servicoPrestado);
    }

    @GetMapping
    public List<ServicoPrestado> pesquisar(
            @RequestParam(value = "nome", required = false, defaultValue = "") String nome,
            @RequestParam(value = "mes", required = false) Integer mes
    ) {
        return servicoPrestadoRepository.findByNomeClienteAndMes("%" + nome + "%", mes);
    }

}
