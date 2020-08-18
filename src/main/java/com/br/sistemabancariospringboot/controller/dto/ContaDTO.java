package com.br.sistemabancariospringboot.controller.dto;

import com.br.sistemabancariospringboot.model.TipoConta;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContaDTO {

    @NotEmpty
    private String nomeTitular;
    @NotEmpty
    @CPF
    private String cpfTitular;
    @NotNull
    private Double saldo;
    @NotNull
    private TipoConta tipoConta;
    @NotEmpty
    private String numeroConta;
    @NotEmpty
    private String senha;

}
