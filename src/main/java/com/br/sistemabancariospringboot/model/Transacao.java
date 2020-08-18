package com.br.sistemabancariospringboot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_conta")
    private Conta conta;
    private double valorTransacao;
    @Enumerated(EnumType.STRING)
    private TipoTransacao tipoTransacao;
    private LocalDate dataTransacao;
    private Long idTrans;

}
