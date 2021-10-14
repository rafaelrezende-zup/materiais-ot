package br.com.zup.jpa.testes;

import br.com.zup.jpa.modelo.Conta;
import br.com.zup.jpa.modelo.Movimentacao;
import br.com.zup.jpa.modelo.TipoMovimentacao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TesteRelacionamento {

    public static void main(String[] args) {

        Conta conta = new Conta();
        conta.setSaldo(300.00);
        conta.setAgencia(132);
        conta.setTitular("Rafael");
        conta.setNumero(987);

        Movimentacao movimentacao = new Movimentacao();
        movimentacao.setData(LocalDateTime.now());
        movimentacao.setTipoMovimentacao(TipoMovimentacao.SAIDA);
        movimentacao.setDescricao("Pagamento Churrascaria");
        movimentacao.setValor(new BigDecimal("100.00"));
        movimentacao.setConta(conta);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("zup_jpa");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.persist(conta);
        em.persist(movimentacao);
        em.getTransaction().commit();
        em.close();

    }

}
