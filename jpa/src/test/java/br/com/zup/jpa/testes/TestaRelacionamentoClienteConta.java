package br.com.zup.jpa.testes;

import br.com.zup.jpa.modelo.Cliente;
import br.com.zup.jpa.modelo.Conta;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TestaRelacionamentoClienteConta {

    public static void main(String[] args) {

        Conta conta = new Conta();
        conta.setId(1L);

        Cliente cliente = new Cliente();
        cliente.setNome("Rafael");
        cliente.setProfissao("Develop");
        cliente.setConta(conta);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("zup_jpa");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.persist(cliente);
        em.getTransaction().commit();
        em.close();

    }

}
