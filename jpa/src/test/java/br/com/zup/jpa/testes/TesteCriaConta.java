package br.com.zup.jpa.testes;

import br.com.zup.jpa.modelo.Conta;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TesteCriaConta {

    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("zup_jpa");
        EntityManager entityManager = factory.createEntityManager();

        Conta conta = new Conta();
        conta.setTitular("Rafael Rezende");
        conta.setNumero(1324);
        conta.setAgencia(3166);

        entityManager.getTransaction().begin();

        entityManager.persist(conta);

        entityManager.getTransaction().commit();
    }

}
