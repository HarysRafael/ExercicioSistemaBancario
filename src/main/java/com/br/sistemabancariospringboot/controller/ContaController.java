package com.br.sistemabancariospringboot.controller;

import com.br.sistemabancariospringboot.controller.dto.ContaDTO;
import com.br.sistemabancariospringboot.controller.dto.ContaResponseDTO;
import com.br.sistemabancariospringboot.model.Conta;
import com.br.sistemabancariospringboot.service.ContaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/conta")
public class ContaController {

    private final ContaService contaService;
    private final ModelMapper modelMapper;

    @Autowired
    public ContaController(ContaService contaService, ModelMapper modelMapper) {
        this.contaService = contaService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContaResponseDTO cadastrar(@Valid @RequestBody ContaDTO contaDTO) {
        Conta conta = modelMapper.map(contaDTO, Conta.class);
        Conta contaCriada = contaService.cadastro(conta);
        return modelMapper.map(contaCriada, ContaResponseDTO.class);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public List<ContaResponseDTO> listarTodos() {
        return contaService.listarTodos().stream()
                .map(conta -> {
                    return modelMapper.map(conta, ContaResponseDTO.class);
                })
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ContaResponseDTO buscarPorId(@PathVariable Long id) {
        Conta conta = contaService.buscarId(id);
        return modelMapper.map(conta, ContaResponseDTO.class);

    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ContaResponseDTO atualizar(@PathVariable long id, @RequestBody ContaDTO contaDTO) {
        Conta conta = modelMapper.map(contaDTO, Conta.class);
        Conta contaAtuaizada = contaService.atualizar(id, conta);
        return modelMapper.map(conta, ContaResponseDTO.class);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        contaService.delete(id);
    }

}
