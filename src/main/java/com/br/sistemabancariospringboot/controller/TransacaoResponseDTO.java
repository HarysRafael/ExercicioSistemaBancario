package com.br.sistemabancariospringboot.controller;

import com.br.sistemabancariospringboot.model.Conta;
import com.br.sistemabancariospringboot.model.TipoTransacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransacaoResponseDTO {
    private Long id;
    private Long idTrans;
    private double valorTransacao;
    private Conta conta;
    private TipoTransacao tipoTransacao;
    private LocalDate dataTransacao;
}
