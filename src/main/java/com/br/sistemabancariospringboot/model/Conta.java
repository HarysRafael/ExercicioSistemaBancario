package com.br.sistemabancariospringboot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;



@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeTitular;
    private String cpfTitular;
    private String numeroConta;
    private Double saldo;
    @Enumerated(EnumType.STRING)
    private TipoConta tipoConta;
    private String senha;

    public void decrescimoEmConta(Double valorTransacao){
        this.setSaldo(this.getSaldo()-valorTransacao);

    }

    public void acrescimoEmConta(Double valorTransacao){
        this.setSaldo(this.getSaldo()+valorTransacao);

    }


}
