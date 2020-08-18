package com.br.sistemabancariospringboot.controller;

import com.br.sistemabancariospringboot.model.Transacao;
import com.br.sistemabancariospringboot.service.TransacaoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    private final TransacaoService transacaoService;
    private final ModelMapper modelMapper;

    @Autowired
    public TransacaoController(TransacaoService transacaoService, ModelMapper modelMapper) {
        this.transacaoService = transacaoService;
        this.modelMapper = modelMapper;

    }

    @PostMapping("/{id}/deposito")
    @ResponseStatus(HttpStatus.CREATED)
    public TransacaoResponseDTO deposito(@PathVariable long id, @Valid @RequestBody TransacaoDTO transacaoDTO){
        Transacao transacao = modelMapper.map(transacaoDTO, Transacao.class);
        Transacao deposito = transacaoService.deposito(id, transacao);
        return modelMapper.map(deposito, TransacaoResponseDTO.class);

    }

    @PostMapping("/{id}/saque")
    @ResponseStatus(HttpStatus.CREATED)
    public TransacaoResponseDTO saque(@PathVariable long id, @Valid @RequestBody TransacaoDTO transacaoDTO){
        Transacao transacao = modelMapper.map(transacaoDTO, Transacao.class);
        Transacao saque = transacaoService.saque(id, transacao);
        return modelMapper.map(saque, TransacaoResponseDTO.class);

    }

    @PostMapping("/{id}/transferencia")
    @ResponseStatus(HttpStatus.CREATED)
    public TransacaoResponseDTO transferencia(@PathVariable long id, @RequestBody TransacaoDTO transacaoDTO){
        Transacao transacao = modelMapper.map(transacaoDTO, Transacao.class);
        Transacao transfere = transacaoService.transferido(id, transacao);
        TransacaoResponseDTO transferido = modelMapper.map(transfere, TransacaoResponseDTO.class);
        return transferido;

    }

    @GetMapping("/{id}/extrato")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public List<TransacaoResponseDTO> extrato(@PathVariable long id, @RequestParam(required = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
                                              @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal, Transacao transacao) {
        return transacaoService.extrato(id, dataInicial, dataFinal, transacao).stream()
                .map(transacaoList -> {
                    return modelMapper.map(transacaoList, TransacaoResponseDTO.class);
                })
                .collect(Collectors.toList());
    }

}