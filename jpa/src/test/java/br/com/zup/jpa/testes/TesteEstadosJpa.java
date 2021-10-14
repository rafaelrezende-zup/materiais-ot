package br.com.zup.jpa.testes;

import br.com.zup.jpa.modelo.Conta;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TesteEstadosJpa {

    public static void main(String[] args) {

        //Transient
        Conta conta = new Conta();
        conta.setTitular("Almiro");
        conta.setNumero(321321);
        conta.setAgencia(123123);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("zup_jpa");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        //Transient -> Managed
        em.persist(conta);

        //Managed -> Removed
        em.remove(conta);

        em.getTransaction().commit();
    }

}
