package com.br.sistemabancariospringboot.controller.dto;

import com.br.sistemabancariospringboot.model.TipoConta;
import lombok.Data;

@Data
public class ContaResponseDTO {

    private Long id;
    private String nomeTitular;
    private String cpfTitular;
    private String numeroConta;
    private Double saldo;
    private TipoConta tipoConta;
    private String senha;
}
