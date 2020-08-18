
package com.br.sistemabancariospringboot.service;

import com.br.sistemabancariospringboot.model.Conta;
import com.br.sistemabancariospringboot.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Base64;
import java.util.List;

@Service
public class ContaService {

    private final ContaRepository contaRepository;

    @Autowired
    public ContaService(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    public Conta cadastro(Conta conta) {
        conta.setNumeroConta(this.gerarNumeroConta());
        conta.setSenha(this.criptografarSenha(conta));
        contaRepository.findByCpfTitular(conta.getCpfTitular()).ifPresent(conta1 -> {
            throw new RuntimeException();
        })


        ;
        return contaRepository.save(conta);

    }

    public Conta buscarId(Long id) {
        return contaRepository.findById(id).orElseThrow(RuntimeException::new);

    }

    public Conta atualizar(Long id, Conta conta) {
        Conta buscarConta = this.buscarId(id);
        buscarConta.setNomeTitular(conta.getNomeTitular());
        buscarConta.setSaldo(conta.getSaldo());
        return contaRepository.save(buscarConta);

    }

    public List<Conta> listarTodos() {
        return contaRepository.findAll();
    }

    public void delete(Long id) {
        this.buscarId(id);
        contaRepository.deleteById(id);

    }

    public String gerarNumeroConta() {
        Double numero = Math.floor(Math.random() * (12345) + 11111);
        return numero.toString().replace(".", "-");

    }

    public String criptografarSenha(Conta conta) {
        String senhaConta = Base64.getEncoder().encodeToString(conta.getSenha().getBytes());
        return senhaConta;

    }

}
