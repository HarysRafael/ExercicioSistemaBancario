package com.br.sistemabancariospringboot.repository;

import com.br.sistemabancariospringboot.model.Conta;
import com.br.sistemabancariospringboot.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Conta> {
    List<Transacao> findByContaAndDataTransacaoBetween(Conta conta, LocalDate dataInicial, LocalDate dataFinal);

}
