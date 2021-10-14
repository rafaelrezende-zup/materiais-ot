package br.com.zup.jpa.testes;

import br.com.zup.jpa.modelo.Categoria;
import br.com.zup.jpa.modelo.Conta;
import br.com.zup.jpa.modelo.Movimentacao;
import br.com.zup.jpa.modelo.TipoMovimentacao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestaRealcionamentoMovimentacaoCategoria {

    public static void main(String[] args) {

        Categoria categoria = new Categoria("Viagem");
        Categoria categoria1 = new Categoria("Negócios");
        Categoria categoria2 = new Categoria("Férias");

        Conta conta = new Conta();
        conta.setId(1L);

        Movimentacao movimentacao = new Movimentacao();
        movimentacao.setDescricao("Viagem a Brasília");
        movimentacao.setTipoMovimentacao(TipoMovimentacao.SAIDA);
        movimentacao.setData(LocalDateTime.now());
        movimentacao.setValor(new BigDecimal("150.00"));
        movimentacao.setCategorias(Arrays.asList(categoria, categoria1));
        movimentacao.setConta(conta);

        Movimentacao movimentacao1 = new Movimentacao();
        movimentacao1.setDescricao("Viagem a NY");
        movimentacao1.setTipoMovimentacao(TipoMovimentacao.SAIDA);
        movimentacao1.setData(LocalDateTime.now());
        movimentacao1.setValor(new BigDecimal("15000.00"));
        movimentacao1.setCategorias(Arrays.asList(categoria, categoria2));
        movimentacao1.setConta(conta);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("zup_jpa");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.persist(categoria);
        em.persist(categoria1);
        em.persist(categoria2);
        em.persist(movimentacao);
        em.persist(movimentacao1);
        em.getTransaction().commit();
        em.close();

    }

}
