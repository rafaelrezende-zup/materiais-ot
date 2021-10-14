package br.com.zup.jpa.testes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TesteCriaTabela {

    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("zup_jpa");
        EntityManager entityManager = factory.createEntityManager();
        factory.close();

    }

}
