package br.com.zup.jpa.testes;

import br.com.zup.jpa.modelo.Conta;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TesteAlteraSaldoConta {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("zup_jpa");
        EntityManager em = emf.createEntityManager();

        Conta conta = new Conta();
        conta.setTitular("Márcia");
        conta.setNumero(12345);
        conta.setAgencia(54321);
        conta.setSaldo(100.0);

        em.getTransaction().begin();

        em.persist(conta);

        em.getTransaction().commit();
        em.close();

        EntityManager em2 = emf.createEntityManager();
        System.out.println("ID da Conta da Márcia:" + conta.getId());
        Conta conta2 = new Conta();
        conta2.setTitular("Teste");
        conta2.setNumero(3452);
        conta2.setAgencia(5678);
        conta2.setSaldo(10000.0);

        em2.getTransaction().begin();

        em2.merge(conta2);

        em2.getTransaction().commit();

    }

}
