package com.br.sistemabancariospringboot.controller;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransacaoDTO {

    @NotNull
    private double valorTransacao;
    private Long idTrans;
}
