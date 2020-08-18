package com.br.sistemabancariospringboot.service;

import com.br.sistemabancariospringboot.exceptions.SaldoInsuficienteException;
import com.br.sistemabancariospringboot.exceptions.SaqueLimiteException;
import com.br.sistemabancariospringboot.model.Conta;
import com.br.sistemabancariospringboot.model.TipoTransacao;
import com.br.sistemabancariospringboot.model.Transacao;
import com.br.sistemabancariospringboot.repository.ContaRepository;
import com.br.sistemabancariospringboot.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;


@Service
public class TransacaoService {

    private final ContaRepository contaRepository;
    private final TransacaoRepository transacaoRepository;
    private final ContaService contaService;

    @Autowired
    public TransacaoService(ContaRepository contaRepository, TransacaoRepository transacaoRepository, ContaService contaService) {
        this.contaRepository = contaRepository;
        this.transacaoRepository = transacaoRepository;
        this.contaService = contaService;
    }

    public Transacao deposito(Long id, Transacao transacao) {
        Conta conta = contaService.buscarId(id);
        transacao.setTipoTransacao(TipoTransacao.DEPOSITO);
        transacao.setConta(conta);
        transacao.setDataTransacao(LocalDate.now());
        conta.acrescimoEmConta(transacao.getValorTransacao());
        return transacaoRepository.save(transacao);

    }

    public Transacao saque(Long id, Transacao transacao) {
        Conta conta = contaService.buscarId(id);
        transacao.setTipoTransacao(TipoTransacao.SAQUE);
        transacao.setConta(conta);
        transacao.setDataTransacao(LocalDate.now());
        verificarSaque(conta, transacao.getValorTransacao());
        conta.decrescimoEmConta(transacao.getValorTransacao());
        verificarSaldo(conta.getSaldo());
        return transacaoRepository.save(transacao);

    }

    public Transacao transferido(Long id, Transacao transacao) {
        this.recebido(transacao.getIdTrans(), transacao);
        Conta contaTransfere = contaService.buscarId(id);
        transacao.setTipoTransacao(TipoTransacao.TRANSFERIDO);
        transacao.setConta(contaTransfere);
        transacao.setDataTransacao(LocalDate.now());
        verificarTranferencia(contaTransfere, transacao.getValorTransacao());
        contaTransfere.decrescimoEmConta(transacao.getValorTransacao());
        verificarSaldo(contaTransfere.getSaldo());
        return transacaoRepository.save(transacao);

    }

    public Transacao recebido(Long id, Transacao transacao) {
        Conta contaRecebe = contaService.buscarId(id);
        transacao.setTipoTransacao(TipoTransacao.RECEBIDO);
        transacao.setConta(contaRecebe);
        transacao.setDataTransacao(LocalDate.now());
        contaRecebe.acrescimoEmConta(transacao.getValorTransacao());
        return transacaoRepository.save(transacao);

    }

    public List<Transacao> findByContaAndDataTransacaoBetween(Long id, LocalDate dataInicial, LocalDate dataFinal, Transacao transacao) {
        Conta conta = contaService.buscarId(id);
        transacao.setConta(conta);
        return transacaoRepository.findByContaAndDataTransacaoBetween(conta, dataInicial, dataFinal);

    }
    public List <Transacao> extrato(Long id,LocalDate dataInicial, LocalDate dataFinal, Transacao transacao ){
        findByContaAndDataTransacaoBetween(id, dataInicial, dataFinal, transacao);
        return transacaoRepository.findAll();
    }


    public void verificarTranferencia(Conta conta, Double valorTransacao) {
        switch (conta.getTipoConta()) {
            case NORMAL:
                conta.setSaldo(conta.getSaldo() - valorTransacao * 10 / 100);
                break;

            case ESPECIAL:
                conta.setSaldo(conta.getSaldo() - valorTransacao * 4 / 100);
                break;

        }
    }

    public void verificarSaque(Conta conta, Double valorTransacao) {
        switch (conta.getTipoConta()) {
            case NORMAL:
                if (valorTransacao > 300) throw new SaqueLimiteException("Valor ultrapassa limite de saque.");
                break;

            case ESPECIAL:
                if (valorTransacao > 1000) throw new SaqueLimiteException("Valor ultrapassa limite de saque.");
                break;

        }
    }

    public void verificarSaldo(Double saldo) {
        if (saldo < 0) throw new SaldoInsuficienteException("Saldo insuficiente.");

    }
}
